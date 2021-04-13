package se.llbit.chunky.block;

import se.llbit.chunky.model.BlockModel;
import se.llbit.chunky.model.SnowModel;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.resources.Texture;
import se.llbit.math.Ray;

public class Snow extends MinecraftBlockTranslucent implements ModelBlock {
  private final SnowModel model;
  private final int layers;

  public Snow(int layers) {
    super("snow", Texture.snowBlock);
    localIntersect = true;
    this.layers = layers;
    this.model = new SnowModel(layers);
  }

  @Override
  public boolean intersect(Ray ray, Scene scene) {
    return model.intersect(ray, scene);
  }

  @Override
  public String description() {
    return "layers=" + layers;
  }

  @Override
  public BlockModel getModel() {
    return model;
  }
}
