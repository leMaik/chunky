package se.llbit.chunky.model;

import org.apache.commons.math3.util.FastMath;
import se.llbit.chunky.plugin.PluginApi;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.resources.Texture;
import se.llbit.chunky.resources.pbr.NormalMap;
import se.llbit.math.AABB;
import se.llbit.math.Matrix3;
import se.llbit.math.Ray;
import se.llbit.math.Vector3;

/**
 * A block model that is made out of textured AABBs.
 */
@PluginApi
public abstract class AABBModel implements BlockModel {

  /**
   * Different UV mapping methods.
   *  - None: No change in mapping
   *  - ROTATE_90: Rotate 90 degrees clockwise
   *  - ROTATE_180: Rotate 180 degrees
   *  - ROTATE_270: Rotate 270 degrees clockwise (90 degrees counterclockwise)
   *  - FLIP_U: Flip along the X axis (u = 1 - u)
   *  - FLIP_V: Flip along the Y axis (v = 1 - v)
   *
   * Note: a value of {@code null} is equivalent to {@code NONE}
   */
  public enum UVMapping {
    NONE,
    ROTATE_90,
    ROTATE_180,
    ROTATE_270,
    FLIP_U,
    FLIP_V
  }

  protected final static Matrix3[] tbnMatrices = {
      NormalMap.tbnCubeNorth,
      NormalMap.tbnCubeEast,
      NormalMap.tbnCubeSouth,
      NormalMap.tbnCubeWest,
      NormalMap.tbnCubeTop,
      NormalMap.tbnCubeBottom
  };

  @PluginApi
  public abstract AABB[] getBoxes();

  @PluginApi
  public abstract Texture[][] getTextures();

  @PluginApi
  public Tint[][] getTints() {
    return null;
  }

  @PluginApi
  public UVMapping[][] getUVMapping() {
    return null;
  }

  @Override
  public boolean intersect(Ray ray, Scene scene) {
    AABB[] boxes = getBoxes();
    Texture[][] textures = getTextures();
    UVMapping[][] mapping = getUVMapping();
    Tint[][] tintedFaces = getTints();

    Texture hitTexture = null;
    int hitSide = 0;

    ray.t = Double.POSITIVE_INFINITY;
    for (int i = 0; i < boxes.length; ++i) {
      if (boxes[i].intersect(ray)) {
        Tint[] tintedFacesBox = tintedFaces != null ? tintedFaces[i] : null;
        Vector3 n = ray.getNormal();

        int side = -1;
        if (n.y > 0) { // top
          ray.v = 1 - ray.v;
          side = 4;
        } else if (n.y < 0) { // bottom
          side = 5;
        } else if (n.z < 0) { // north
          side = 0;
        } else if (n.z > 0) { // south
          side = 2;
        } else if (n.x < 0) { // west
          side = 3;
        } else if (n.x > 0) { // east
          side = 1;
        }

        if (side != -1) {
          boolean hit = intersectFace(ray, scene, textures[i][side],
              mapping != null ? mapping[i][side] : null,
              tintedFacesBox != null ? tintedFacesBox[side] : Tint.NONE);
          hitTexture = hit ? textures[i][side] : hitTexture;
          hitSide = hit ? side : hitSide;
          ray.t = hit ? ray.tNext: ray.t;
        }
      }
    }

    if (hitTexture != null) {
      if (ray.getCurrentMaterial().opaque) {
        ray.color.w = 1;
      }
      NormalMap.apply(ray, tbnMatrices[hitSide], hitTexture);
      ray.emittanceValue = hitTexture.getEmittanceAt(ray.u, ray.v);
      ray.reflectanceValue = hitTexture.getReflectanceAt(ray.u, ray.v);
      ray.roughnessValue = hitTexture.getRoughnessAt(ray.u, ray.v);
      ray.metalnessValue = hitTexture.getMetalnessAt(ray.u, ray.v);
      ray.distance += ray.t;
      ray.o.scaleAdd(ray.t, ray.d);
    }

    return hitTexture != null;
  }

  private boolean intersectFace(Ray ray, Scene scene, Texture texture, UVMapping mapping,
      Tint tintType) {
    // This is the method that handles intersecting faces of all AABB-based models.
    // Do normal mapping, parallax occlusion mapping, specular maps and all the good stuff here!

    if (texture == null) {
      return false;
    }

    double tmp;
    if (mapping != null) {
      switch (mapping) {
        case ROTATE_90:
          tmp = ray.u;
          ray.u = 1 - ray.v;
          ray.v = tmp;
          break;
        case ROTATE_180:
          ray.u = 1 - ray.u;
          ray.v = 1 - ray.v;
          break;
        case ROTATE_270:
          tmp = ray.v;
          ray.v = 1 - ray.u;
          ray.u = tmp;
          break;
        case FLIP_U:
          ray.u = 1 - ray.u;
          break;
        case FLIP_V:
          ray.v = 1 - ray.v;
          break;
      }
    }

    float[] color = texture.getColor(ray.u, ray.v);
    if (color[3] > Ray.EPSILON) {
      tintType.tint(color, ray, scene);
      ray.color.set(color);
      return true;
    }
    return false;
  }
}
