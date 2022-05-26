package se.llbit.chunky.resources.pbr;

import se.llbit.chunky.resources.Texture;
import se.llbit.math.Matrix3;
import se.llbit.math.Quad;
import se.llbit.math.Ray;
import se.llbit.math.Vector3;

public interface NormalMap {

  Matrix3 tbnCubeTop =
    getTbn(new Vector3(1, 0, 0), new Vector3(0, 0, 1), new Vector3(0, 1, 0));
  Matrix3 tbnCubeBottom =
    getTbn(new Vector3(1, 0, 0), new Vector3(0, -1, 0), new Vector3(0, -1, 0));
  Matrix3 tbnCubeNorth =
    getTbn(new Vector3(1, 0, 0), new Vector3(0, -1, 0), new Vector3(0, 0, -1));
  Matrix3 tbnCubeEast =
    getTbn(new Vector3(0, 0, 1), new Vector3(0, -1, 0), new Vector3(1, 0, 0));
  Matrix3 tbnCubeSouth =
    getTbn(new Vector3(-1, 0, 0), new Vector3(0, -1, 0), new Vector3(0, 0, 1));
  Matrix3 tbnCubeWest =
    getTbn(new Vector3(0, 0, -1), new Vector3(0, -1, 0), new Vector3(-1, 0, 0));


  static void apply(Ray ray, Quad quad, Texture texture) {
    if (texture.getNormalMap() != null) {
      if (texture.getNormalMap().applyNormal(ray)) {
        Vector3 rayNormal = ray.getNormal();
        quad.tbn.transform(rayNormal);
        // rayNormal.normalize();
        ray.setNormal(rayNormal);
      }
    }
  }

  static void apply(Ray ray, Matrix3 tbn, Texture texture) {
    if (texture.getNormalMap() != null) {
      if (texture.getNormalMap().applyNormal(ray)) {
        Vector3 rayNormal = ray.getNormal();
        tbn.transform(rayNormal);
        // rayNormal.normalize();
        ray.setNormal(rayNormal);
      }
    }
  }

  static Matrix3 getTbn(Vector3 t, Vector3 b, Vector3 n) {
    Matrix3 tbn = new Matrix3();
    tbn.m11 = t.x;
    tbn.m21 = t.y;
    tbn.m31 = t.z;
    tbn.m12 = b.x;
    tbn.m22 = b.y;
    tbn.m32 = b.z;
    tbn.m13 = n.x;
    tbn.m23 = n.y;
    tbn.m33 = n.z;
    return tbn;
  }

  boolean applyNormal(Ray ray);
}