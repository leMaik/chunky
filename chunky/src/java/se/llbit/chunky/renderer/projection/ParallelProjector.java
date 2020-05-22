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

import se.llbit.math.Vector3;

/**
 * Casts parallel rays from different origin points on a plane
 */
public class ParallelProjector implements Projector {
  protected final double worldWidth;
  protected final double fov;

  public ParallelProjector(double worldWidth, double fov) {
    this.worldWidth = worldWidth;
    this.fov = fov;
  }

  @Override public void apply(double x, double y, RandomGenerator random, Vector3 o, Vector3 d) {
    apply(x, y, o, d);
  }

  @Override public void apply(double x, double y, Vector3 o, Vector3 d) {
    o.set(fov * x, fov * y, 0);
    d.set(0, 0, 1);
  }

  @Override public double getMinRecommendedFoV() {
    return 0.01;
  }

  @Override public double getMaxRecommendedFoV() {
    return worldWidth;
  }

  @Override public double getDefaultFoV() {
    return worldWidth / 2;
  }
}
