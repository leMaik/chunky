/* Copyright (c) 2014 Jesper Öqvist <jesper@llbit.se>
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
package se.llbit.chunky.renderer.projection;

import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

import se.llbit.math.QuickMath;
import se.llbit.math.Vector3;

/**
 * Panoramic equirectangular projector. x is mapped to yaw, y is mapped to
 * pitch.
 */
public class PanoramicProjector implements Projector {
  protected final double fov;

  public PanoramicProjector(double fov) {
    this.fov = fov;
  }

  @Override public void apply(double x, double y, RandomGenerator random, Vector3 o, Vector3 d) {
    apply(x, y, o, d);
  }

  @Override public void apply(double x, double y, Vector3 o, Vector3 d) {
    double ay = QuickMath.degToRad(y * fov);
    double ax = QuickMath.degToRad(x * fov);

    double vv = FastMath.cos(ay);

    o.set(0, 0, 0);
    d.set(vv * FastMath.sin(ax), FastMath.sin(ay), vv * FastMath.cos(ax));
  }

  @Override public double getMinRecommendedFoV() {
    return 1;
  }

  @Override public double getMaxRecommendedFoV() {
    return 180;
  }

  @Override public double getDefaultFoV() {
    return 120;
  }
}
