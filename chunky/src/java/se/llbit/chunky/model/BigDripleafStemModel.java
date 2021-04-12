package se.llbit.chunky.model;

import se.llbit.chunky.resources.Texture;
import se.llbit.math.Quad;
import se.llbit.math.Vector3;
import se.llbit.math.Vector4;

import java.util.Arrays;

public class BigDripleafStemModel extends QuadModel {

  private static final Quad[] quadsNorth =
      Model.join(
          Model.rotateY(new Quad[]{
                  new Quad(
                      new Vector3(5 / 16.0, 16 / 16.0, 12 / 16.0),
                      new Vector3(11 / 16.0, 16 / 16.0, 12 / 16.0),
                      new Vector3(5 / 16.0, 0 / 16.0, 12 / 16.0),
                      new Vector4(14 / 16.0, 3 / 16.0, 16 / 16.0, 0 / 16.0)
                  ),
                  new Quad(
                      new Vector3(11 / 16.0, 16 / 16.0, 12 / 16.0),
                      new Vector3(5 / 16.0, 16 / 16.0, 12 / 16.0),
                      new Vector3(11 / 16.0, 0 / 16.0, 12 / 16.0),
                      new Vector4(14 / 16.0, 3 / 16.0, 16 / 16.0, 0 / 16.0)
                  )
              },
              Math.toRadians(45), new Vector3(0.5, 0, 12 / 16.0)), // TODO rescale
          Model.rotateY(new Quad[]{
                  new Quad(
                      new Vector3(5 / 16.0, 16 / 16.0, 12 / 16.0),
                      new Vector3(11 / 16.0, 16 / 16.0, 12 / 16.0),
                      new Vector3(5 / 16.0, 0 / 16.0, 12 / 16.0),
                      new Vector4(14 / 16.0, 3 / 16.0, 16 / 16.0, 0 / 16.0)
                  ),
                  new Quad(
                      new Vector3(11 / 16.0, 16 / 16.0, 12 / 16.0),
                      new Vector3(5 / 16.0, 16 / 16.0, 12 / 16.0),
                      new Vector3(11 / 16.0, 0 / 16.0, 12 / 16.0),
                      new Vector4(14 / 16.0, 3 / 16.0, 16 / 16.0, 0 / 16.0)
                  )
              },
              Math.toRadians(-45), new Vector3(0.5, 0, 12 / 16.0)) // TODO rescale
      );

  private static final Texture[] textures = new Texture[quadsNorth.length];
  static {
    Arrays.fill(textures, Texture.bigDripleafStem);
  }

  private static final Quad[][] orientedQuads = new Quad[4][];

  static {
    orientedQuads[0] = quadsNorth;
    orientedQuads[1] = Model.rotateY(orientedQuads[0]);
    orientedQuads[2] = Model.rotateY(orientedQuads[1]);
    orientedQuads[3] = Model.rotateY(orientedQuads[2]);
  }

  private final Quad[] quads;

  public BigDripleafStemModel(String facing) {
    int orientation;
    switch (facing) {
      case "east":
        orientation = 1;
        break;
      case "south":
        orientation = 2;
        break;
      case "west":
        orientation = 3;
        break;
      case "north":
      default:
        orientation = 0;
    }
    quads = orientedQuads[orientation];
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
