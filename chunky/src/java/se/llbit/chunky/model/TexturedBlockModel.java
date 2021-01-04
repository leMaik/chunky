/* Copyright (c) 2012 Jesper Öqvist <jesper@llbit.se>
 *
 * This file is part of Chunky.
 *
 * Chunky is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Chunky is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with Chunky.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.llbit.chunky.model;

import se.llbit.chunky.block.Air;
import se.llbit.chunky.resources.Texture;
import se.llbit.math.AABB;
import se.llbit.math.QuickMath;
import se.llbit.math.Ray;

/**
 * A textured block.
 *
 * @author Jesper Öqvist <jesper@llbit.se>
 */
public class TexturedBlockModel {
  private static final AABB block = new AABB(0, 1, 0, 1, 0, 1);

  /**
   * Find intersection between a ray and a block.
   * The ray origin is updated to the intersection point.
   *
   * @param texture array of textures for each side of the block.
   * Texture 0 is north, 1 is south, 2 is west,
   * 3 is east, 4 is top, 5 is bottom.
   * @return <code>true</code> if the ray intersects the block
   */
  public static boolean intersect(Ray ray, Texture[] texture) {
    ray.t = Double.POSITIVE_INFINITY;
    if (block.intersect(ray)) {
      float[] color;
      double emittance = 0;
      double reflectance = 0;
      boolean hasEmittance = ray.getCurrentMaterial().emittance > Ray.EPSILON;
      boolean hasSpecular = ray.getCurrentMaterial().specular > Ray.EPSILON;
      if (ray.n.z < 0) {
        color = texture[0].getColor(ray.u, ray.v);
        if (hasEmittance) {
          emittance = texture[0].getEmittanceAt(ray.u, ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[0].getReflectanceAt(ray.u, ray.v);
        }
      } else if (ray.n.z > 0) {
        color = texture[1].getColor(ray.u, ray.v);
        if (hasEmittance) {
          emittance = texture[1].getEmittanceAt(ray.u, ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[1].getReflectanceAt(ray.u, ray.v);
        }
      } else if (ray.n.x > 0) {
        color = texture[2].getColor(ray.u, ray.v);
        if (hasEmittance) {
          emittance = texture[2].getEmittanceAt(ray.u, ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[2].getReflectanceAt(ray.u, ray.v);
        }
      } else if (ray.n.x < 0) {
        color = texture[3].getColor(ray.u, ray.v);
        if (hasEmittance) {
          emittance = texture[3].getEmittanceAt(ray.u, ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[3].getReflectanceAt(ray.u, ray.v);
        }
      } else if (ray.n.y > 0) {
        color = texture[4].getColor(1 - ray.u, 1 - ray.v);
        if (hasEmittance) {
          emittance = texture[4].getEmittanceAt(1 - ray.u, 1 - ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[4].getReflectanceAt(1 - ray.u, 1 - ray.v);
        }
      } else {
        color = texture[5].getColor(ray.u, ray.v);
        if (hasEmittance) {
          emittance = texture[5].getEmittanceAt(ray.u, ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[5].getReflectanceAt(ray.u, ray.v);
        }
      }

      if (color[3] > Ray.EPSILON) {
        ray.color.set(color);
        ray.emittanceValue = emittance;
        ray.reflectanceValue = reflectance;
        ray.distance += ray.tNext;
        ray.o.scaleAdd(ray.tNext, ray.d);
        return true;
      }
    }
    return false;
  }

  /**
   * Find intersection between ray and block.
   *
   * @param ray     ray to test
   * @param texture Texture array
   * @param index   An index array used to index the texture array
   * @return <code>true</code> if the ray intersected the block
   */
  public static boolean intersect(Ray ray, Texture[] texture, int[] index) {
    ray.t = Double.POSITIVE_INFINITY;
    if (block.intersect(ray)) {
      float[] color;
      double emittance = 0;
      double reflectance = 0;
      boolean hasEmittance = ray.getCurrentMaterial().emittance > Ray.EPSILON;
      boolean hasSpecular = ray.getCurrentMaterial().specular > Ray.EPSILON;
      if (ray.n.z < 0) {
        color = texture[index[0]].getColor(ray.u, ray.v);
        if (hasEmittance) {
          emittance = texture[index[0]].getEmittanceAt(ray.u, ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[index[0]].getReflectanceAt(ray.u, ray.v);
        }
      } else if (ray.n.z > 0) {
        color = texture[index[1]].getColor(ray.u, ray.v);
        if (hasEmittance) {
          emittance = texture[index[1]].getEmittanceAt(ray.u, ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[index[1]].getReflectanceAt(ray.u, ray.v);
        }
      } else if (ray.n.x > 0) {
        color = texture[index[2]].getColor(ray.u, ray.v);
        if (hasEmittance) {
          emittance = texture[index[2]].getEmittanceAt(ray.u, ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[index[2]].getReflectanceAt(ray.u, ray.v);
        }
      } else if (ray.n.x < 0) {
        color = texture[index[3]].getColor(ray.u, ray.v);
        if (hasEmittance) {
          emittance = texture[index[3]].getEmittanceAt(ray.u, ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[index[3]].getReflectanceAt(ray.u, ray.v);
        }
      } else if (ray.n.y > 0) {
        color = texture[index[4]].getColor(1 - ray.u, 1 - ray.v);
        if (hasEmittance) {
          emittance = texture[index[4]].getEmittanceAt(1 - ray.u, 1 - ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[index[4]].getReflectanceAt(1 - ray.u, 1 - ray.v);
        }
      } else {
        color = texture[index[5]].getColor(ray.u, ray.v);
        if (hasEmittance) {
          emittance = texture[index[5]].getEmittanceAt(ray.u, ray.v);
        }
        if (hasSpecular) {
          reflectance = texture[index[5]].getReflectanceAt(ray.u, ray.v);
        }
      }

      if (color[3] > Ray.EPSILON) {
        ray.color.set(color);
        ray.emittanceValue = emittance;
        ray.reflectanceValue = reflectance;
        ray.distance += ray.tNext;
        ray.o.scaleAdd(ray.tNext, ray.d);
        return true;
      }
    }
    return false;
  }

  /**
   * Find intersection between ray and block.
   *
   * @param ray     ray to test
   * @param texture Block texture
   * @return <code>true</code> if the ray intersected the block
   */
  public static boolean intersect(Ray ray, Texture texture) {
    ray.t = Double.POSITIVE_INFINITY;
    if (block.intersect(ray)) {
      float[] color = texture.getColor(ray.u, ray.v);
      if (color[3] > Ray.EPSILON) {
        ray.color.set(color);
        ray.emittanceValue = texture.getEmittanceAt(ray.u, ray.v);
        ray.reflectanceValue = texture.getReflectanceAt(ray.u, ray.v);
        ray.distance += ray.tNext;
        ray.o.scaleAdd(ray.tNext, ray.d);
        return true;
      }
    }
    return false;
  }

  /**
   * Find the color of the object at the intersection point.
   *
   * @param ray ray to test
   */
  public static void getIntersectionColor(Ray ray) {
    if (ray.getCurrentMaterial() == Air.INSTANCE) {
      ray.color.x = 1;
      ray.color.y = 1;
      ray.color.z = 1;
      ray.color.w = 0;
      return;
    }
    getTextureCoordinates(ray);
    ray.getCurrentMaterial().getTexture(ray.getBlockData()).getColor(ray);
  }

  /**
   * Calculate the UV coordinates for the ray on the intersected block.
   *
   * @param ray ray to test
   */
  private static void getTextureCoordinates(Ray ray) {
    int bx = (int) QuickMath.floor(ray.o.x);
    int by = (int) QuickMath.floor(ray.o.y);
    int bz = (int) QuickMath.floor(ray.o.z);
    if (ray.n.y != 0) {
      ray.u = ray.o.x - bx;
      ray.v = ray.o.z - bz;
    } else if (ray.n.x != 0) {
      ray.u = ray.o.z - bz;
      ray.v = ray.o.y - by;
    } else {
      ray.u = ray.o.x - bx;
      ray.v = ray.o.y - by;
    }
    if (ray.n.x > 0 || ray.n.z < 0) {
      ray.u = 1 - ray.u;
    }
    if (ray.n.y > 0) {
      ray.v = 1 - ray.v;
    }
  }
}
