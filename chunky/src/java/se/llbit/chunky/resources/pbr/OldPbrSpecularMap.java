package se.llbit.chunky.resources.pbr;

import se.llbit.chunky.resources.BitmapImage;
import se.llbit.math.Ray;

/**
 * Specular map that follows the old PBR format. In BSL this option is called "Old PBR + Emissive".
 * The emission map is stored in the blue channel, the reflectance map in the green channel and the
 * roughness uses the red channel.
 */
public class OldPbrSpecularMap implements EmissionMap, RoughnessMap, MetalnessMap {

  private final int width;
  private final int height;
  private byte[] emissionMap;
  private byte[] metalnessMap;
  private byte[] roughnessMap;

  public OldPbrSpecularMap(BitmapImage texture) {
    width = texture.width;
    height = texture.height;
    emissionMap = new byte[texture.width * texture.height];

    boolean hasEmission = false;
    for (int y = 0; y < texture.height; ++y) {
      for (int x = 0; x < texture.width; ++x) {
        // blue channel
        if ((emissionMap[y * texture.width + x] = (byte) (
            texture.data[y * texture.width + x] & 0xFF)) != (byte) 0x00) {
          hasEmission = true;
        }
      }
    }
    if (!hasEmission) {
      emissionMap = null;
    }

    metalnessMap = new byte[texture.width * texture.height];
    boolean hasMetalness = false;
    for (int y = 0; y < texture.height; ++y) {
      for (int x = 0; x < texture.width; ++x) {
        // green channel
        if ((metalnessMap[y * texture.width + x] = (byte) (
            texture.data[y * texture.width + x] >>> 8)) != (byte) 0x00) {
          hasMetalness = true;
        }
      }
    }
    if (!hasMetalness) {
      metalnessMap = null;
    }

    roughnessMap = new byte[texture.width * texture.height];
    boolean hasRoughness = false;
    for (int y = 0; y < texture.height; ++y) {
      for (int x = 0; x < texture.width; ++x) {
        // red channel stores smoothness
        int value = ((texture.data[y * texture.width + x] >> 16) & 0xFF);
        if (value < 255) {
          hasRoughness = true;
        }
        roughnessMap[y * texture.width + x] = (byte) (255 - value);
      }
    }
    if (!hasRoughness) {
      roughnessMap = null;
    }
  }

  @Override
  public double getEmittanceAt(double u, double v) {
    int x = (int) (u * width - Ray.EPSILON);
    int y = (int) ((1 - v) * height - Ray.EPSILON);
    int rawValue = emissionMap[y * width + x] & 0xFF;
    return rawValue / 255.0;
  }

  public boolean hasEmission() {
    return emissionMap != null;
  }

  @Override
  public float getMetalnessAt(double u, double v) {
    int x = (int) (u * width - Ray.EPSILON);
    int y = (int) ((1 - v) * height - Ray.EPSILON);
    int rawValue = metalnessMap[y * width + x] & 0xFF;
    return rawValue / 255f;
  }

  public boolean hasMetalness() {
    return metalnessMap != null;
  }

  @Override
  public double getRoughnessAt(double u, double v) {
    int x = (int) (u * width - Ray.EPSILON);
    int y = (int) ((1 - v) * height - Ray.EPSILON);
    int rawValue = roughnessMap[y * width + x] & 0xFF;
    return rawValue / 255.0;
  }

  public boolean hasRoughness() {
    return roughnessMap != null;
  }
}
