package se.llbit.chunky.resources;

import org.apache.commons.math3.util.FastMath;
import se.llbit.math.ColorUtil;
import se.llbit.math.Matrix3;
import se.llbit.math.Quad;
import se.llbit.math.Ray;
import se.llbit.math.Vector2;
import se.llbit.math.Vector3;

public class NormalMap extends Texture {
  private Vector3[] normals;

  public static void apply(Ray ray, Vector3 xv, Vector3 yv, Texture texture) {
    if (texture.normalMap != null) {
      texture.normalMap.apply(ray, xv, yv);
    }
  }

  public static void apply(Ray ray, Quad quad, Texture texture) {
    if (texture.normalMap != null) {
      texture.normalMap.apply(ray, quad.xv, quad.yv);
    }
  }

  public void apply(Vector3 vec, Vector3 t, Vector3 b, double u, double v) {
    t.normalize();
    b.normalize();

    Matrix3 tbn = new Matrix3();
    tbn.m11 = t.x;
    tbn.m21 = t.y;
    tbn.m31 = t.z;
    tbn.m12 = b.x;
    tbn.m22 = b.y;
    tbn.m32 = b.z;
    tbn.m13 = vec.x;
    tbn.m23 = vec.y;
    tbn.m33 = vec.z;

    Vector3 n =
        this.normals[
            (int) ((1 - v) * height - Ray.EPSILON) * width + (int) (u * width - Ray.EPSILON)];
    if (n.lengthSquared() > 0) {
      vec.set(n);
      tbn.transform(vec);
      vec.normalize();
    }
  }

  public void apply(Ray ray, Vector3 xv, Vector3 yv) {
    apply(ray.n, xv, yv, ray.u, ray.v);
  }

  @Override
  public void setTexture(BitmapImage newImage) {
    super.setTexture(newImage);
    normals = new Vector3[width * height];
    float[] color = new float[4];
    for (int x = 0; x < newImage.width; x++) {
      for (int y = 0; y < newImage.height; y++) {
        ColorUtil.getRGBAComponents(newImage.getPixel(x, y), color);
        Vector2 xy = new Vector2(color[0] * 2 - 1, color[1] * 2 - 1);
        // xy.normalize();
        normals[width * y + x] =
            new Vector3(color[0] * 2 - 1, color[1] * 2 - 1, FastMath.sqrt(1.0 - xy.dot(xy)));
        // normals[width * y + x].normalize();
      }
    }
  }
}
