package se.llbit.chunky.model;

import se.llbit.chunky.plugin.PluginApi;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.resources.Texture;
import se.llbit.chunky.resources.pbr.NormalMap;
import se.llbit.log.Log;
import se.llbit.math.Quad;
import se.llbit.math.Ray;

public abstract class QuadModel implements BlockModel {

  @PluginApi
  public abstract Quad[] getQuads();

  @PluginApi
  public abstract Texture[] getTextures();

  @PluginApi
  public TintType[] getTintedQuads() {
    return null;
  }

  @Override
  public boolean intersect(Ray ray, Scene scene) {
    boolean hit = false;
    ray.t = Double.POSITIVE_INFINITY;

    Quad[] quads = getQuads();
    Texture[] textures = getTextures();
    TintType[] tintedQuads = getTintedQuads();

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
          TintType tintType = tintedQuads == null ? TintType.NONE : tintedQuads[i];
          if (tintType != TintType.NONE) {
            float[] biomeColor;
            switch (tintType) {
              case BIOME_FOLIAGE:
                biomeColor = ray.getBiomeFoliageColor(scene);
                break;
              case BIOME_GRASS:
                biomeColor = ray.getBiomeGrassColor(scene);
                break;
              case BIOME_WATER:
                biomeColor = ray.getBiomeWaterColor(scene);
                break;
              default:
                Log.warn("Unsupported TintType: " + tintType);
                biomeColor = new float[]{1, 1, 1};
                break;
            }
            c[0] *= biomeColor[0];
            c[1] *= biomeColor[1];
            c[2] *= biomeColor[2];
          }
          color = c;
          hitQuad = quad;
          hitTexture = textures[i];
          emittanceValue = hitTexture.getEmittanceAt(ray.u, ray.v);
          reflectanceValue = hitTexture.getReflectanceAt(ray.u, ray.v);
          roughnessValue = hitTexture.getRoughnessAt(ray.u, ray.v);
          metalnessValue = hitTexture.getMetalnessAt(ray.u, ray.v);
          ray.t = ray.tNext;
          ray.n.set(quad.n);
          hit = true;
        }
      }
    }

    if (hit) {
      double px = ray.o.x - Math.floor(ray.o.x + ray.d.x * Ray.OFFSET) + ray.d.x * ray.tNext;
      double py = ray.o.y - Math.floor(ray.o.y + ray.d.y * Ray.OFFSET) + ray.d.y * ray.tNext;
      double pz = ray.o.z - Math.floor(ray.o.z + ray.d.z * Ray.OFFSET) + ray.d.z * ray.tNext;
      if (px < 0 || px > 1 || py < 0 || py > 1 || pz < 0 || pz > 1) {
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
