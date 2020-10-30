package se.llbit.chunky.block.jsonmodels;

import java.util.Collection;
import java.util.LinkedList;
import se.llbit.chunky.block.Block;
import se.llbit.chunky.entity.Entity;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.resources.Texture;
import se.llbit.chunky.world.Material;
import se.llbit.chunky.world.material.TextureMaterial;
import se.llbit.json.JsonObject;
import se.llbit.json.JsonValue;
import se.llbit.math.Quad;
import se.llbit.math.Ray;
import se.llbit.math.Transform;
import se.llbit.math.Vector3;
import se.llbit.math.primitive.Primitive;

public class QuadBlock extends Block {

  private final Quad[] quads;
  private final Texture[] textures;
  private final boolean isEntity;

  public QuadBlock(String name, Texture texture, Quad[] quads, Texture[] textures,
      boolean isEntity) {
    super(name, texture);
    localIntersect = true;
    opaque = false;
    invisible = isEntity;
    this.isEntity = isEntity;
    this.textures = textures;
    this.quads = quads;
  }

  @Override
  public boolean intersect(Ray ray, Scene scene) {
    if (invisible) {
      return false;
    }
    boolean hit = false;
    ray.t = Double.POSITIVE_INFINITY;

    for (int i = 0; i < quads.length; i++) {
      if (quads[i].intersect(ray)) {
        if (textures[i] == null) {
          continue;
        }
        float[] color = textures[i].getColor(ray.u, ray.v);
        if (color[3] > Ray.EPSILON) {
          if (quads[i] instanceof TintedQuad) {
            float[] biomeColor = ray.getBiomeGrassColor(scene);
            color[0] *= biomeColor[0];
            color[1] *= biomeColor[1];
            color[2] *= biomeColor[2];
          }
          ray.color.set(color);
          ray.n.set(quads[i].n);
          ray.t = ray.tNext;
          hit = true;
        }
      }
    }

    if (hit) {
      ray.color.w = 1;
      ray.distance += ray.t;
      ray.o.scaleAdd(ray.t, ray.d);
    }
    return hit;
  }

  @Override
  public boolean isEntity() {
    return isEntity;
  }

  @Override
  public Entity toEntity(Vector3 position) {
    return new Entity(position) {
      @Override
      public Collection<Primitive> primitives(Vector3 offset) {
        Collection<Primitive> faces = new LinkedList<>();
        Transform transform =
            Transform.NONE.translate(
                position.x + offset.x, position.y + offset.y, position.z + offset.z);
        for (int i = 0; i < quads.length; i++) {
          Quad quad = quads[i];
          Texture texture = textures[i];
          Material material = new TextureMaterial(texture);
          material.emittance = emittance;
          material.specular = specular;
          material.ior = ior;
          quad.addTriangles(faces, material, transform);
        }
        return faces;
      }

      @Override
      public JsonValue toJson() {
        // TODO
        return new JsonObject();
      }
    };
  }
}
