package se.llbit.chunky.model;

import se.llbit.chunky.resources.Texture;
import se.llbit.math.Quad;
import se.llbit.math.Vector3;
import se.llbit.math.Vector4;

public class JigsawModel extends QuadModel {
  //region Jigsaw
  private static final Quad[] quadsNorthUp = {
      new Quad(
          new Vector3(0 / 16.0, 16 / 16.0, 16 / 16.0),
          new Vector3(16 / 16.0, 16 / 16.0, 16 / 16.0),
          new Vector3(0 / 16.0, 16 / 16.0, 0 / 16.0),
          new Vector4(0 / 16.0, 16 / 16.0, 1 - 16 / 16.0, 1 - 0 / 16.0)),
      new Quad(
          new Vector3(0 / 16.0, 0 / 16.0, 0 / 16.0),
          new Vector3(16 / 16.0, 0 / 16.0, 0 / 16.0),
          new Vector3(0 / 16.0, 0 / 16.0, 16 / 16.0),
          new Vector4(0 / 16.0, 16 / 16.0, 16 / 16.0, 0 / 16.0)),
      new Quad(
          new Vector3(0 / 16.0, 16 / 16.0, 0 / 16.0),
          new Vector3(0 / 16.0, 0 / 16.0, 0 / 16.0),
          new Vector3(0 / 16.0, 16 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 0 / 16.0, 16 / 16.0, 0 / 16.0)),
      new Quad(
          new Vector3(16 / 16.0, 0 / 16.0, 0 / 16.0),
          new Vector3(16 / 16.0, 16 / 16.0, 0 / 16.0),
          new Vector3(16 / 16.0, 0 / 16.0, 16 / 16.0),
          new Vector4(0 / 16.0, 16 / 16.0, 16 / 16.0, 0 / 16.0)),
      new Quad(
          new Vector3(0 / 16.0, 16 / 16.0, 0 / 16.0),
          new Vector3(16 / 16.0, 16 / 16.0, 0 / 16.0),
          new Vector3(0 / 16.0, 0 / 16.0, 0 / 16.0),
          new Vector4(16 / 16.0, 0 / 16.0, 16 / 16.0, 0 / 16.0)),
      new Quad(
          new Vector3(16 / 16.0, 16 / 16.0, 16 / 16.0),
          new Vector3(0 / 16.0, 16 / 16.0, 16 / 16.0),
          new Vector3(16 / 16.0, 0 / 16.0, 16 / 16.0),
          new Vector4(16 / 16.0, 0 / 16.0, 16 / 16.0, 0 / 16.0))
  };
  //endregion

  static final Quad[][] orientedQuads = new Quad[12][];

  private final static Texture[] textures = {
      Texture.jigsawLock,
      Texture.jigsawSide,
      Texture.jigsawSide,
      Texture.jigsawSide,
      Texture.jigsawTop,
      Texture.jigsawBottom
  };

  static {
    orientedQuads[1] = Model.rotateNegX(quadsNorthUp);
    orientedQuads[0] = Model.rotateY(orientedQuads[1]);
    orientedQuads[2] = Model.rotateY(orientedQuads[0]);
    orientedQuads[3] = Model.rotateY(orientedQuads[2]);
    orientedQuads[4] = Model.rotateY(quadsNorthUp);
    orientedQuads[5] = quadsNorthUp;
    orientedQuads[6] = Model.rotateY(orientedQuads[4]);
    orientedQuads[11] = Model.rotateY(orientedQuads[6]);
    orientedQuads[9] = Model.rotateNegX(Model.rotateNegX(orientedQuads[1]));
    orientedQuads[10] = Model.rotateY(orientedQuads[9]);
    orientedQuads[8] = Model.rotateY(orientedQuads[10]);
    orientedQuads[7] = Model.rotateY(orientedQuads[8]);
  }

  private final Quad[] quads;

  public JigsawModel(String orientation) {
    quads = orientedQuads[getOrientationIndex(orientation)];
  }

  @Override
  public Quad[] getQuads() {
    return quads;
  }

  @Override
  public Texture[] getTextures() {
    return textures;
  }

  private static int getOrientationIndex(String facing) {
    switch (facing) {
      case "down_east":
        return 0;
      case "down_north":
        return 1;
      case "down_south":
        return 2;
      case "down_west":
        return 3;
      case "east_up":
        return 4;
      case "north_up":
        return 5;
      case "south_up":
        return 6;
      case "up_east":
        return 7;
      case "up_north":
        return 8;
      case "up_south":
        return 9;
      case "up_west":
        return 10;
      case "west_up":
        return 11;
      default:
        return 5; // north_up
    }
  }
}
