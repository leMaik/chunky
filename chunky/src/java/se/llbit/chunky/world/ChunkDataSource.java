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
package se.llbit.chunky.world;

import it.unimi.dsi.fastutil.io.FastBufferedInputStream;

import java.io.DataInputStream;
import java.io.InputStream;

/**
 * Container representing a chunk data source with a timestamp and an input stream.
 *
 * @author Jesper Öqvist <jesper@llbit.se>
 */
public class ChunkDataSource {
  public final int timestamp;
  public final DataInputStream inputStream;
  public DataInputStream entityInputStream;

  public ChunkDataSource(int timestamp, InputStream in, InputStream entityIn) {
    this.timestamp = timestamp;
    if (in != null) {
      this.inputStream = new DataInputStream(new FastBufferedInputStream(in));
    } else {
      this.inputStream = null;
    }
    if (entityIn != null) {
      this.entityInputStream = new DataInputStream(new FastBufferedInputStream(entityIn));
    } else {
      this.entityInputStream = null;
    }
  }
}
