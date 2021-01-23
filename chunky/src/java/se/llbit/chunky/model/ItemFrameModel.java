package se.llbit.chunky.model;

import se.llbit.chunky.resources.Texture;
import se.llbit.math.Quad;
import se.llbit.math.Ray;
import se.llbit.math.Vector3;
import se.llbit.math.Vector4;

public class ItemFrameModel {

  public static final Quad[] itemFrameQuads = new Quad[]{
      new Quad( // back
          new Vector3(3 / 16.0, 13 / 16.0, 15.5 / 16.0),
          new Vector3(13 / 16.0, 13 / 16.0, 15.5 / 16.0),
          new Vector3(3 / 16.0, 3 / 16.0, 15.5 / 16.0),
          new Vector4(13 / 16.0, 3 / 16.0, 13 / 16.0, 3 / 16.0)
      ),
      new Quad( // back
          new Vector3(13 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector3(3 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector3(13 / 16.0, 3 / 16.0, 16 / 16.0),
          new Vector4(13 / 16.0, 3 / 16.0, 13 / 16.0, 3 / 16.0)
      ),
      new Quad( // frame
          new Vector3(2 / 16.0, 3 / 16.0, 16 / 16.0),
          new Vector3(14 / 16.0, 3 / 16.0, 16 / 16.0),
          new Vector3(2 / 16.0, 3 / 16.0, 15 / 16.0),
          new Vector4(2 / 16.0, 14 / 16.0, 0 / 16.0, 1 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 2 / 16.0, 15 / 16.0),
          new Vector3(14 / 16.0, 2 / 16.0, 15 / 16.0),
          new Vector3(2 / 16.0, 2 / 16.0, 16 / 16.0),
          new Vector4(2 / 16.0, 14 / 16.0, 15 / 16.0, 16 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 3 / 16.0, 16 / 16.0),
          new Vector3(2 / 16.0, 3 / 16.0, 15 / 16.0),
          new Vector3(2 / 16.0, 2 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 15 / 16.0, 3 / 16.0, 2 / 16.0)
      ),
      new Quad(
          new Vector3(14 / 16.0, 3 / 16.0, 15 / 16.0),
          new Vector3(14 / 16.0, 3 / 16.0, 16 / 16.0),
          new Vector3(14 / 16.0, 2 / 16.0, 15 / 16.0),
          new Vector4(1 / 16.0, 0 / 16.0, 3 / 16.0, 2 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 3 / 16.0, 15 / 16.0),
          new Vector3(14 / 16.0, 3 / 16.0, 15 / 16.0),
          new Vector3(2 / 16.0, 2 / 16.0, 15 / 16.0),
          new Vector4(14 / 16.0, 2 / 16.0, 3 / 16.0, 2 / 16.0)
      ),
      new Quad(
          new Vector3(14 / 16.0, 3 / 16.0, 16 / 16.0),
          new Vector3(2 / 16.0, 3 / 16.0, 16 / 16.0),
          new Vector3(14 / 16.0, 2 / 16.0, 16 / 16.0),
          new Vector4(14 / 16.0, 2 / 16.0, 3 / 16.0, 2 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 14 / 16.0, 16 / 16.0),
          new Vector3(14 / 16.0, 14 / 16.0, 16 / 16.0),
          new Vector3(2 / 16.0, 14 / 16.0, 15 / 16.0),
          new Vector4(2 / 16.0, 14 / 16.0, 0 / 16.0, 1 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector3(14 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector3(2 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector4(2 / 16.0, 14 / 16.0, 15 / 16.0, 16 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 14 / 16.0, 16 / 16.0),
          new Vector3(2 / 16.0, 14 / 16.0, 15 / 16.0),
          new Vector3(2 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 15 / 16.0, 14 / 16.0, 13 / 16.0)
      ),
      new Quad(
          new Vector3(14 / 16.0, 14 / 16.0, 15 / 16.0),
          new Vector3(14 / 16.0, 14 / 16.0, 16 / 16.0),
          new Vector3(14 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector4(1 / 16.0, 0 / 16.0, 14 / 16.0, 13 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 14 / 16.0, 15 / 16.0),
          new Vector3(14 / 16.0, 14 / 16.0, 15 / 16.0),
          new Vector3(2 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector4(14 / 16.0, 2 / 16.0, 14 / 16.0, 13 / 16.0)
      ),
      new Quad(
          new Vector3(14 / 16.0, 14 / 16.0, 16 / 16.0),
          new Vector3(2 / 16.0, 14 / 16.0, 16 / 16.0),
          new Vector3(14 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector4(14 / 16.0, 2 / 16.0, 14 / 16.0, 13 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector3(2 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector3(2 / 16.0, 3 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 15 / 16.0, 13 / 16.0, 3 / 16.0)
      ),
      new Quad(
          new Vector3(3 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector3(3 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector3(3 / 16.0, 3 / 16.0, 15 / 16.0),
          new Vector4(1 / 16.0, 0 / 16.0, 13 / 16.0, 3 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector3(3 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector3(2 / 16.0, 3 / 16.0, 15 / 16.0),
          new Vector4(14 / 16.0, 13 / 16.0, 13 / 16.0, 3 / 16.0)
      ),
      new Quad(
          new Vector3(3 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector3(2 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector3(3 / 16.0, 3 / 16.0, 16 / 16.0),
          new Vector4(3 / 16.0, 2 / 16.0, 13 / 16.0, 3 / 16.0)
      ),
      new Quad(
          new Vector3(13 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector3(13 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector3(13 / 16.0, 3 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 15 / 16.0, 13 / 16.0, 3 / 16.0)
      ),
      new Quad(
          new Vector3(14 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector3(14 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector3(14 / 16.0, 3 / 16.0, 15 / 16.0),
          new Vector4(1 / 16.0, 0 / 16.0, 13 / 16.0, 3 / 16.0)
      ),
      new Quad(
          new Vector3(13 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector3(14 / 16.0, 13 / 16.0, 15 / 16.0),
          new Vector3(13 / 16.0, 3 / 16.0, 15 / 16.0),
          new Vector4(3 / 16.0, 2 / 16.0, 13 / 16.0, 3 / 16.0)
      ),
      new Quad(
          new Vector3(14 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector3(13 / 16.0, 13 / 16.0, 16 / 16.0),
          new Vector3(14 / 16.0, 3 / 16.0, 16 / 16.0),
          new Vector4(14 / 16.0, 13 / 16.0, 13 / 16.0, 3 / 16.0)
      )
  };

  private static final Quad[] mapFrameQuads = new Quad[]{
      new Quad( // back
          new Vector3(1 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(15 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(1 / 16.0, 1 / 16.0, 15.001 / 16.0),
          new Vector4(15 / 16.0, 1 / 16.0, 15 / 16.0, 1 / 16.0)
      ),
      new Quad( // back
          new Vector3(15 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector3(1 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector3(15 / 16.0, 1 / 16.0, 16 / 16.0),
          new Vector4(15 / 16.0, 1 / 16.0, 15 / 16.0, 1 / 16.0)
      ),
      new Quad( // frame
          new Vector3(0 / 16.0, 1 / 16.0, 16 / 16.0),
          new Vector3(16 / 16.0, 1 / 16.0, 16 / 16.0),
          new Vector3(0 / 16.0, 1 / 16.0, 15.001 / 16.0),
          new Vector4(0 / 16.0, 16 / 16.0, 0 / 16.0, 1 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 0 / 16.0, 15.001 / 16.0),
          new Vector3(16 / 16.0, 0 / 16.0, 15.001 / 16.0),
          new Vector3(0 / 16.0, 0 / 16.0, 16 / 16.0),
          new Vector4(0 / 16.0, 16 / 16.0, 15 / 16.0, 16 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 1 / 16.0, 16 / 16.0),
          new Vector3(0 / 16.0, 1 / 16.0, 15.001 / 16.0),
          new Vector3(0 / 16.0, 0 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 15 / 16.0, 1 / 16.0, 0 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 1 / 16.0, 15.001 / 16.0),
          new Vector3(16 / 16.0, 1 / 16.0, 16 / 16.0),
          new Vector3(16 / 16.0, 0 / 16.0, 15.001 / 16.0),
          new Vector4(1 / 16.0, 0 / 16.0, 1 / 16.0, 0 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 1 / 16.0, 15.001 / 16.0),
          new Vector3(16 / 16.0, 1 / 16.0, 15.001 / 16.0),
          new Vector3(0 / 16.0, 0 / 16.0, 15.001 / 16.0),
          new Vector4(16 / 16.0, 0 / 16.0, 1 / 16.0, 0 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 1 / 16.0, 16 / 16.0),
          new Vector3(0 / 16.0, 1 / 16.0, 16 / 16.0),
          new Vector3(16 / 16.0, 0 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 0 / 16.0, 1 / 16.0, 0 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 16 / 16.0, 16 / 16.0),
          new Vector3(16 / 16.0, 16 / 16.0, 16 / 16.0),
          new Vector3(0 / 16.0, 16 / 16.0, 15.001 / 16.0),
          new Vector4(0 / 16.0, 16 / 16.0, 0 / 16.0, 1 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(16 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(0 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector4(0 / 16.0, 16 / 16.0, 15 / 16.0, 16 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 16 / 16.0, 16 / 16.0),
          new Vector3(0 / 16.0, 16 / 16.0, 15.001 / 16.0),
          new Vector3(0 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 15 / 16.0, 16 / 16.0, 15 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 16 / 16.0, 15.001 / 16.0),
          new Vector3(16 / 16.0, 16 / 16.0, 16 / 16.0),
          new Vector3(16 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector4(1 / 16.0, 0 / 16.0, 16 / 16.0, 15 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 16 / 16.0, 15.001 / 16.0),
          new Vector3(16 / 16.0, 16 / 16.0, 15.001 / 16.0),
          new Vector3(0 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector4(16 / 16.0, 0 / 16.0, 16 / 16.0, 15 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 16 / 16.0, 16 / 16.0),
          new Vector3(0 / 16.0, 16 / 16.0, 16 / 16.0),
          new Vector3(16 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 0 / 16.0, 16 / 16.0, 15 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector3(0 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(0 / 16.0, 1 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 15 / 16.0, 15 / 16.0, 1 / 16.0)
      ),
      new Quad(
          new Vector3(1 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(1 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector3(1 / 16.0, 1 / 16.0, 15.001 / 16.0),
          new Vector4(1 / 16.0, 0 / 16.0, 15 / 16.0, 1 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(1 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(0 / 16.0, 1 / 16.0, 15.001 / 16.0),
          new Vector4(16 / 16.0, 15 / 16.0, 15 / 16.0, 1 / 16.0)
      ),
      new Quad(
          new Vector3(1 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector3(0 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector3(1 / 16.0, 1 / 16.0, 16 / 16.0),
          new Vector4(1 / 16.0, 0 / 16.0, 15 / 16.0, 1 / 16.0)
      ),
      new Quad(
          new Vector3(15 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector3(15 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(15 / 16.0, 1 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 15 / 16.0, 15 / 16.0, 1 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(16 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector3(16 / 16.0, 1 / 16.0, 15.001 / 16.0),
          new Vector4(1 / 16.0, 0 / 16.0, 15 / 16.0, 1 / 16.0)
      ),
      new Quad(
          new Vector3(15 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(16 / 16.0, 15 / 16.0, 15.001 / 16.0),
          new Vector3(15 / 16.0, 1 / 16.0, 15.001 / 16.0),
          new Vector4(1 / 16.0, 0 / 16.0, 15 / 16.0, 1 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector3(15 / 16.0, 15 / 16.0, 16 / 16.0),
          new Vector3(16 / 16.0, 1 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 15 / 16.0, 15 / 16.0, 1 / 16.0)
      )
  };

  public static boolean intersect(Ray ray, Texture backTexture) {
    System.out.println("IFRAMO");
    boolean hit = false;
    ray.t = Double.POSITIVE_INFINITY;

    Quad[] quads = itemFrameQuads;
    for (int i = 0; i < quads.length; ++i) {
      Quad quad = quads[i];
      if (quad.intersect(ray)) {
        float[] color = i < 2
            ? backTexture.getColor(ray.u, ray.v)
            : Texture.birchPlanks.getColor(ray.u, ray.v);
        if (color[3] > Ray.EPSILON) {
          ray.color.set(color);
          ray.t = ray.tNext;
          ray.n.set(quad.n);
          hit = true;
        }
      }
    }

    if (hit) {
      ray.distance += ray.t;
      ray.o.scaleAdd(ray.t, ray.d);
    }
    return hit;
  }
}
