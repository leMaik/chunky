package se.llbit.chunky.model;

import se.llbit.chunky.plugin.PluginApi;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.resources.Texture;
import se.llbit.math.Quad;
import se.llbit.math.QuickMath;
import se.llbit.math.Ray;
import se.llbit.math.Vector3;

/**
 * A block model that is made out of textured quads.
 */
@PluginApi
public abstract class QuadModel implements BlockModel {

  // Epsilons to clip ray intersections to the current block.
  protected static final double E0 = -Ray.EPSILON;
  protected static final double E1 = 1 + Ray.EPSILON;

  @PluginApi
  public abstract Quad[] getQuads();

  @PluginApi
  public abstract Texture[] getTextures();

  @PluginApi
  public Tint[] getTints() {
    return null;
  }

  @Override
  public boolean intersect(Ray ray, Scene scene) {
    boolean hit = false;
    ray.t = Double.POSITIVE_INFINITY;

    Quad[] quads = getQuads();
    Texture[] textures = getTextures();
    Tint[] tintedQuads = getTints();

    float[] color = null;
    double emittanceValue = 0;
    double reflectanceValue = 0;
    double roughnessValue = 0;
    float metalnessValue = 0;
    Quad hitQuad = null;
    Texture hitTexture = null;

    for (int i = 0; i < quads.length; ++i) {
      Quad quad = quads[i];
      if (quad.intersect(ray)) {
        float[] c = textures[i].getColor(ray.u, ray.v);
        if (c[3] > Ray.EPSILON) {
          Tint tint = tintedQuads == null ? Tint.NONE : tintedQuads[i];
          tint.tint(c, ray, scene);
          color = c;
          ray.t = ray.tNext;
          if (quad.doubleSided)
            ray.orientNormal(quad.n);
          else
            ray.setNormal(quad.n);
            
          hitQuad = quad;
          hitTexture = textures[i];
          emittanceValue = hitTexture.getEmittanceAt(ray.u, ray.v);
          reflectanceValue = hitTexture.getReflectanceAt(ray.u, ray.v);
          roughnessValue = hitTexture.getRoughnessAt(ray.u, ray.v);
          metalnessValue = hitTexture.getMetalnessAt(ray.u, ray.v);
          hit = true;
        }
      }
    }

    if (hit) {
      double px = ray.o.x - Math.floor(ray.o.x + ray.d.x * Ray.OFFSET) + ray.d.x * ray.tNext;
      double py = ray.o.y - Math.floor(ray.o.y + ray.d.y * Ray.OFFSET) + ray.d.y * ray.tNext;
      double pz = ray.o.z - Math.floor(ray.o.z + ray.d.z * Ray.OFFSET) + ray.d.z * ray.tNext;
      if (px < E0 || px > E1 || py < E0 || py > E1 || pz < E0 || pz > E1) {
        // TODO this check is only really needed for wall torches
        return false;
      }

      NormalMap.apply(ray, hitQuad, hitTexture);
      ray.color.set(color);
      ray.emittanceValue = emittanceValue;
      ray.reflectanceValue = reflectanceValue;
      ray.roughnessValue = roughnessValue;
      ray.metalnessValue = metalnessValue;
      ray.distance += ray.t;
      ray.o.scaleAdd(ray.t, ray.d);
    }
    return hit;
  }
}
