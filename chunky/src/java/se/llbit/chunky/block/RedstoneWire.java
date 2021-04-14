package se.llbit.chunky.block;

import se.llbit.chunky.model.BlockModel;
import se.llbit.chunky.model.RedstoneWireModel;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.resources.Texture;
import se.llbit.chunky.world.BlockData;
import se.llbit.math.Ray;

public class RedstoneWire extends MinecraftBlock implements ModelBlock {
  private final RedstoneWireModel model;
  private final String description;

  public RedstoneWire(int power, String north, String south, String east, String west) {
    super("redstone_wire", Texture.redstoneWireCross);
    description = String.format("power=%d, north=%s, south=%s, east=%s, west=%s",
        power, north, south, east, west);
    localIntersect = true;
    opaque = false;
    solid = false;
    int state = 0;
    if (!east.equals("none")) {
      state |= 1 << BlockData.RSW_EAST_CONNECTION;
      if (east.equals("up")) {
        state |= 1 << BlockData.RSW_EAST_UP;
      }
    }
    if (!west.equals("none")) {
      state |= 1 << BlockData.RSW_WEST_CONNECTION;
      if (west.equals("up")) {
        state |= 1 << BlockData.RSW_WEST_UP;
      }
    }
    if (!north.equals("none")) {
      state |= 1 << BlockData.RSW_NORTH_CONNECTION;
      if (south.equals("up")) {
        state |= 1 << BlockData.RSW_NORTH_UP;
      }
    }
    if (!south.equals("none")) {
      state |= 1 << BlockData.RSW_SOUTH_CONNECTION;
      if (north.equals("up")) {
        state |= 1 << BlockData.RSW_SOUTH_UP;
      }
    }
    this.model = new RedstoneWireModel(power, state);
  }

  @Override
  public boolean intersect(Ray ray, Scene scene) {
    return model.intersect(ray, scene);
  }

  @Override
  public String description() {
    return description;
  }

  @Override
  public BlockModel getModel() {
    return model;
  }
}
