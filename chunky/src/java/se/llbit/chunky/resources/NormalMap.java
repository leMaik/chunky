package se.llbit.chunky.resources;

import org.apache.commons.math3.util.FastMath;
import se.llbit.math.ColorUtil;
import se.llbit.math.Matrix3;
import se.llbit.math.Ray;
import se.llbit.math.Vector2;
import se.llbit.math.Vector3;

public class NormalMap extends Texture {

  public static void apply(Ray ray, Texture texture) {
    if (texture.normalMap != null) {
      // texture.normalMap.apply(ray);
    }
  }

  public void apply(Vector3 vec, double u, double v) {
    float[] color = getColor(u, v);
    Vector3 t = new Vector3();
    t.cross(vec, new Vector3(0, 1, 0));
    if (t.lengthSquared() < Ray.EPSILON) {
      t.cross(vec, new Vector3(0, 0, 1));
    }
    t.normalize();
    Vector3 b = new Vector3();
    b.cross(vec, t);
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

    Vector3 n = new Vector3(color[0] * 2 - 1, color[1] * 2 - 1, color[2] * 2 - 1);
    vec.set(n);
    tbn.transform(vec);
    vec.normalize();
  }

  public void apply(Ray ray) {
    apply(ray.n, ray.u, ray.v);
  }

  @Override
  public void setTexture(BitmapImage newImage) {
    Vector3 color = new Vector3();
    Vector2 xy = new Vector2();
    for (int x = 0; x < newImage.width; x++) {
      for (int y = 0; y < newImage.height; y++) {
        ColorUtil.getRGBAComponents(newImage.getPixel(x, y), color);
        xy.set(color.x, color.y);
        color.z = (FastMath.sqrt(1.0 - xy.dot(xy)) + 1) / 2;
        newImage.setPixel(x, y, ColorUtil.getRGB(color));
      }
    }
    super.setTexture(newImage);
  }
}
