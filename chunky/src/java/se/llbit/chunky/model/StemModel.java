package se.llbit.chunky.model;

import se.llbit.chunky.resources.Texture;
import se.llbit.math.DoubleSidedQuad;
import se.llbit.math.Quad;
import se.llbit.math.Vector3;
import se.llbit.math.Vector4;

public class StemModel extends QuadModel {
  private static final Quad[] growth = new Quad[2];
  private static final Quad[] ripe = {
      new DoubleSidedQuad(new Vector3(0, 0, .5), new Vector3(1, 0, .5), new Vector3(0, 1, .5),
          new Vector4(0, 1, 0, 1)),
      new DoubleSidedQuad(new Vector3(0, 0, .5), new Vector3(1, 0, .5),
          new Vector3(0, 1, .5), new Vector4(1, 0, 0, 1)),
      new DoubleSidedQuad(new Vector3(.5, 0, 0), new Vector3(.5, 0, 1),
          new Vector3(.5, 1, 0), new Vector4(0, 1, 0, 1)),
      new DoubleSidedQuad(new Vector3(.5, 0, 0), new Vector3(.5, 0, 1),
          new Vector3(.5, 1, 0), new Vector4(1, 0, 0, 1)),
  };

  static {
    int height = 3;
    growth[0] = new DoubleSidedQuad(new Vector3(0, 0, 0), new Vector3(1, 0, 1),
        new Vector3(0, (height + 1) / 8., 0), new Vector4(0, 1, (7 - height) / 8., 1));
    growth[1] = new DoubleSidedQuad(new Vector3(1, 0, 0), new Vector3(0, 0, 1),
        new Vector3(1, (height + 1) / 8., 0), new Vector4(0, 1, (7 - height) / 8., 1));
  }

  private static final Texture[] textures = {
      Texture.stemStraight, Texture.stemStraight, Texture.stemBent
  };

  private final Quad[] quads;

  public StemModel(int facing) {
    quads = new Quad[3];
    quads[0] = growth[0];
    quads[1] = growth[1];
    quads[2] = ripe[facing];
  }

  @Override
  public Quad[] getQuads() {
    return quads;
  }

  @Override
  public Texture[] getTextures() {
    return textures;
  }
}
