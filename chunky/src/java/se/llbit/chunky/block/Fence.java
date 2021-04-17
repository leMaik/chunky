package se.llbit.chunky.block;

import se.llbit.chunky.model.BlockModel;
import se.llbit.chunky.model.FenceModel;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.resources.Texture;
import se.llbit.chunky.world.BlockData;
import se.llbit.math.Ray;

public class Fence extends MinecraftBlockTranslucent implements ModelBlock {
  private final String description;
  private final FenceModel model;

  public Fence(String name, Texture texture,
      boolean north, boolean south, boolean east, boolean west) {
    super(name, texture);
    solid = false;
    localIntersect = true;
    this.description = String.format("north=%s, south=%s, east=%s, west=%s",
        north, south, east, west);
    int connections = 0;
    if (north) {
      connections |= BlockData.CONNECTED_NORTH;
    }
    if (south) {
      connections |= BlockData.CONNECTED_SOUTH;
    }
    if (east) {
      connections |= BlockData.CONNECTED_EAST;
    }
    if (west) {
      connections |= BlockData.CONNECTED_WEST;
    }
    this.model = new FenceModel(texture, connections);
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
