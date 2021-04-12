package se.llbit.chunky.model;

import se.llbit.chunky.resources.Texture;
import se.llbit.math.Quad;
import se.llbit.math.Vector3;
import se.llbit.math.Vector4;

import java.util.Arrays;

public class BellModel extends QuadModel {
  private static final Texture bell = Texture.bellBody;
  private static final Texture bar = Texture.darkOakPlanks;
  private static final Texture post = Texture.stone;

  private static final Texture[] texBellFloor = new Texture[]{
      bar, bar, bar, bar, post, post, post, post, post, post, post, post, post, post, post, post
  };

  private static final Texture[] texBellWall = new Texture[]{
      bar, bar, bar, bar, bar, bar
  };

  private static final Texture[] texBellDoubleWall = new Texture[]{
      bar, bar, bar, bar, bar, bar
  };

  private static final Texture[] texBellCeiling = new Texture[]{
      bar, bar, bar, bar, bar
  };

  private static final Quad[][] quadsBell = rotateQuadsNESW(new Quad[]{
      new Quad( // up
          new Vector3(4 / 16.0, 6 / 16.0, 12 / 16.0),
          new Vector3(12 / 16.0, 6 / 16.0, 12 / 16.0),
          new Vector3(4 / 16.0, 6 / 16.0, 4 / 16.0),
          new Vector4(8 / 32.0, 16 / 32.0, 1 - 13 / 32.0, 1 - 21 / 32.0)
      ),
      new Quad( // down
          new Vector3(12 / 16.0, 4 / 16.0, 12 / 16.0),
          new Vector3(4 / 16.0, 4 / 16.0, 12 / 16.0),
          new Vector3(12 / 16.0, 4 / 16.0, 4 / 16.0),
          new Vector4(16 / 32.0, 24 / 32.0, 1 - 13 / 32.0, 1 - 21 / 32.0)
      ),
      new Quad( // west
          new Vector3(4 / 16.0, 6 / 16.0, 12 / 16.0),
          new Vector3(4 / 16.0, 6 / 16.0, 4 / 16.0),
          new Vector3(4 / 16.0, 4 / 16.0, 12 / 16.0),
          new Vector4(0 / 32.0, 8 / 32.0, 1 - 23 / 32.0, 1 - 21 / 32.0)
      ),
      new Quad( // east
          new Vector3(12 / 16.0, 6 / 16.0, 4 / 16.0),
          new Vector3(12 / 16.0, 6 / 16.0, 12 / 16.0),
          new Vector3(12 / 16.0, 4 / 16.0, 4 / 16.0),
          new Vector4(16 / 32.0, 24 / 32.0, 1 - 23 / 32.0, 1 - 21 / 32.0)
      ),
      new Quad( // north
          new Vector3(4 / 16.0, 6 / 16.0, 4 / 16.0),
          new Vector3(12 / 16.0, 6 / 16.0, 4 / 16.0),
          new Vector3(4 / 16.0, 4 / 16.0, 4 / 16.0),
          new Vector4(8 / 32.0, 16 / 32.0, 1 - 23 / 32.0, 1 - 21 / 32.0)
      ),
      new Quad( // south
          new Vector3(12 / 16.0, 6 / 16.0, 12 / 16.0),
          new Vector3(4 / 16.0, 6 / 16.0, 12 / 16.0),
          new Vector3(12 / 16.0, 4 / 16.0, 12 / 16.0),
          new Vector4(24 / 32.0, 32 / 32.0, 1 - 23 / 32.0, 1 - 21 / 32.0)
      ),
      new Quad( // up
          new Vector3(5 / 16.0, 13 / 16.0, 11 / 16.0),
          new Vector3(11 / 16.0, 13 / 16.0, 11 / 16.0),
          new Vector3(5 / 16.0, 13 / 16.0, 5 / 16.0),
          new Vector4(6 / 32.0, 12 / 32.0, 1 - 6 / 32.0, 1 - 0 / 32.0)
      ),
      new Quad( // west
          new Vector3(5 / 16.0, 13 / 16.0, 11 / 16.0),
          new Vector3(5 / 16.0, 13 / 16.0, 5 / 16.0),
          new Vector3(5 / 16.0, 6 / 16.0, 11 / 16.0),
          new Vector4(0 / 32.0, 6 / 32.0, 1 - 13 / 32.0, 1 - 6 / 32.0)
      ),
      new Quad( // east
          new Vector3(11 / 16.0, 13 / 16.0, 5 / 16.0),
          new Vector3(11 / 16.0, 13 / 16.0, 11 / 16.0),
          new Vector3(11 / 16.0, 6 / 16.0, 5 / 16.0),
          new Vector4(12 / 32.0, 18 / 32.0, 1 - 13 / 32.0, 1 - 6 / 32.0)
      ),
      new Quad( // north
          new Vector3(5 / 16.0, 13 / 16.0, 5 / 16.0),
          new Vector3(11 / 16.0, 13 / 16.0, 5 / 16.0),
          new Vector3(5 / 16.0, 6 / 16.0, 5 / 16.0),
          new Vector4(6 / 32.0, 12 / 32.0, 1 - 13 / 32.0, 1 - 6 / 32.0)
      ),
      new Quad( // south
          new Vector3(11 / 16.0, 13 / 16.0, 11 / 16.0),
          new Vector3(5 / 16.0, 13 / 16.0, 11 / 16.0),
          new Vector3(11 / 16.0, 6 / 16.0, 11 / 16.0),
          new Vector4(18 / 32.0, 24 / 32.0, 1 - 13 / 32.0, 1 - 6 / 32.0)
      )
  }, 0, 0, 0, 0);

  private static final Quad[][] quadsBellFloor = rotateQuadsNESW(new Quad[]{
      new Quad(
          new Vector3(2 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(14 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(2 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector4(2 / 16.0, 14 / 16.0, 11 / 16.0, 13 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector3(14 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector3(2 / 16.0, 13 / 16.0, 9 / 16.0),
          new Vector4(2 / 16.0, 14 / 16.0, 11 / 16.0, 13 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector3(14 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector3(2 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector4(14 / 16.0, 2 / 16.0, 14 / 16.0, 12 / 16.0)
      ),
      new Quad(
          new Vector3(14 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(2 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(14 / 16.0, 13 / 16.0, 9 / 16.0),
          new Vector4(14 / 16.0, 2 / 16.0, 13 / 16.0, 11 / 16.0)
      ),
      new Quad(
          new Vector3(14 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(16 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(14 / 16.0, 16 / 16.0, 6 / 16.0),
          new Vector4(0 / 16.0, 2 / 16.0, 12 / 16.0, 16 / 16.0)
      ),
      new Quad(
          new Vector3(14 / 16.0, 0 / 16.0, 6 / 16.0),
          new Vector3(16 / 16.0, 0 / 16.0, 6 / 16.0),
          new Vector3(14 / 16.0, 0 / 16.0, 10 / 16.0),
          new Vector4(0 / 16.0, 2 / 16.0, 12 / 16.0, 16 / 16.0)
      ),
      new Quad(
          new Vector3(14 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(14 / 16.0, 16 / 16.0, 6 / 16.0),
          new Vector3(14 / 16.0, 0 / 16.0, 10 / 16.0),
          new Vector4(4 / 16.0, 0 / 16.0, 15 / 16.0, 0 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 16 / 16.0, 6 / 16.0),
          new Vector3(16 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(16 / 16.0, 0 / 16.0, 6 / 16.0),
          new Vector4(4 / 16.0, 0 / 16.0, 15 / 16.0, 0 / 16.0)
      ),
      new Quad(
          new Vector3(14 / 16.0, 16 / 16.0, 6 / 16.0),
          new Vector3(16 / 16.0, 16 / 16.0, 6 / 16.0),
          new Vector3(14 / 16.0, 0 / 16.0, 6 / 16.0),
          new Vector4(2 / 16.0, 0 / 16.0, 15 / 16.0, 0 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(14 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(16 / 16.0, 0 / 16.0, 10 / 16.0),
          new Vector4(2 / 16.0, 0 / 16.0, 15 / 16.0, 0 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(2 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(0 / 16.0, 16 / 16.0, 6 / 16.0),
          new Vector4(0 / 16.0, 2 / 16.0, 12 / 16.0, 16 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 0 / 16.0, 6 / 16.0),
          new Vector3(2 / 16.0, 0 / 16.0, 6 / 16.0),
          new Vector3(0 / 16.0, 0 / 16.0, 10 / 16.0),
          new Vector4(0 / 16.0, 2 / 16.0, 12 / 16.0, 16 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(0 / 16.0, 16 / 16.0, 6 / 16.0),
          new Vector3(0 / 16.0, 0 / 16.0, 10 / 16.0),
          new Vector4(4 / 16.0, 0 / 16.0, 15 / 16.0, 0 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 16 / 16.0, 6 / 16.0),
          new Vector3(2 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(2 / 16.0, 0 / 16.0, 6 / 16.0),
          new Vector4(4 / 16.0, 0 / 16.0, 15 / 16.0, 0 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 16 / 16.0, 6 / 16.0),
          new Vector3(2 / 16.0, 16 / 16.0, 6 / 16.0),
          new Vector3(0 / 16.0, 0 / 16.0, 6 / 16.0),
          new Vector4(2 / 16.0, 0 / 16.0, 15 / 16.0, 0 / 16.0)
      ),
      new Quad(
          new Vector3(2 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(0 / 16.0, 16 / 16.0, 10 / 16.0),
          new Vector3(2 / 16.0, 0 / 16.0, 10 / 16.0),
          new Vector4(2 / 16.0, 0 / 16.0, 15 / 16.0, 0 / 16.0)
      )
  }, 0, 90, 180, 270);

  private static final Quad[][] quadsBellWall = rotateQuadsNESW(new Quad[]{
      new Quad(
          new Vector3(3 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(16 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(3 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector4(2 / 16.0, 14 / 16.0, 11 / 16.0, 13 / 16.0)
      ),
      new Quad(
          new Vector3(3 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector3(16 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector3(3 / 16.0, 13 / 16.0, 9 / 16.0),
          new Vector4(2 / 16.0, 14 / 16.0, 11 / 16.0, 13 / 16.0)
      ),
      new Quad(
          new Vector3(3 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(3 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector3(3 / 16.0, 13 / 16.0, 9 / 16.0),
          new Vector4(7 / 16.0, 5 / 16.0, 12 / 16.0, 10 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector3(16 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(16 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector4(7 / 16.0, 5 / 16.0, 12 / 16.0, 10 / 16.0)
      ),
      new Quad(
          new Vector3(3 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector3(16 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector3(3 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector4(14 / 16.0, 2 / 16.0, 14 / 16.0, 12 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(3 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(16 / 16.0, 13 / 16.0, 9 / 16.0),
          new Vector4(14 / 16.0, 2 / 16.0, 13 / 16.0, 11 / 16.0)
      )
  }, 270, 0, 90, 180);

  private static Quad[][] quadsBellDoubleWall = rotateQuadsNESW(new Quad[]{
      new Quad(
          new Vector3(0 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(16 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(0 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector4(2 / 16.0, 14 / 16.0, 11 / 16.0, 13 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector3(16 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector3(0 / 16.0, 13 / 16.0, 9 / 16.0),
          new Vector4(2 / 16.0, 14 / 16.0, 11 / 16.0, 13 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(0 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector3(0 / 16.0, 13 / 16.0, 9 / 16.0),
          new Vector4(7 / 16.0, 5 / 16.0, 12 / 16.0, 10 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector3(16 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(16 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector4(7 / 16.0, 5 / 16.0, 12 / 16.0, 10 / 16.0)
      ),
      new Quad(
          new Vector3(0 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector3(16 / 16.0, 15 / 16.0, 7 / 16.0),
          new Vector3(0 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector4(14 / 16.0, 2 / 16.0, 14 / 16.0, 12 / 16.0)
      ),
      new Quad(
          new Vector3(16 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(0 / 16.0, 15 / 16.0, 9 / 16.0),
          new Vector3(16 / 16.0, 13 / 16.0, 9 / 16.0),
          new Vector4(14 / 16.0, 2 / 16.0, 13 / 16.0, 11 / 16.0)
      )
  }, 90, 0, 270, 180);

  private static final Quad[][] quadsBellCeiling = rotateQuadsNESW(new Quad[]{
      new Quad(
          new Vector3(7 / 16.0, 16 / 16.0, 9 / 16.0),
          new Vector3(9 / 16.0, 16 / 16.0, 9 / 16.0),
          new Vector3(7 / 16.0, 16 / 16.0, 7 / 16.0),
          new Vector4(1 / 16.0, 3 / 16.0, 11 / 16.0, 13 / 16.0)
      ),
      new Quad(
          new Vector3(7 / 16.0, 16 / 16.0, 9 / 16.0),
          new Vector3(7 / 16.0, 16 / 16.0, 7 / 16.0),
          new Vector3(7 / 16.0, 13 / 16.0, 9 / 16.0),
          new Vector4(6 / 16.0, 4 / 16.0, 14 / 16.0, 11 / 16.0)
      ),
      new Quad(
          new Vector3(9 / 16.0, 16 / 16.0, 7 / 16.0),
          new Vector3(9 / 16.0, 16 / 16.0, 9 / 16.0),
          new Vector3(9 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector4(3 / 16.0, 1 / 16.0, 14 / 16.0, 11 / 16.0)
      ),
      new Quad(
          new Vector3(7 / 16.0, 16 / 16.0, 7 / 16.0),
          new Vector3(9 / 16.0, 16 / 16.0, 7 / 16.0),
          new Vector3(7 / 16.0, 13 / 16.0, 7 / 16.0),
          new Vector4(9 / 16.0, 7 / 16.0, 14 / 16.0, 11 / 16.0)
      ),
      new Quad(
          new Vector3(9 / 16.0, 16 / 16.0, 9 / 16.0),
          new Vector3(7 / 16.0, 16 / 16.0, 9 / 16.0),
          new Vector3(9 / 16.0, 13 / 16.0, 9 / 16.0),
          new Vector4(8 / 16.0, 6 / 16.0, 14 / 16.0, 11 / 16.0)
      )
  }, 0, 90, 180, 270);

  private final Quad[] quads;
  private final Texture[] textures;

  public BellModel(String facing, String attachment) {
    int orientation;

    switch (facing) {
      default:
      case "north":
        orientation = 0;
        break;
      case "east":
        orientation = 1;
        break;
      case "south":
        orientation = 2;
        break;
      case "west":
        orientation = 3;
        break;
    }

    Quad[] quads;
    Texture[] tex;
    switch (attachment) {
      default:
      case "floor":
        quads = quadsBellFloor[orientation];
        tex = texBellFloor;
        break;
      case "ceiling":
        quads = quadsBellCeiling[orientation];
        tex = texBellCeiling;
        break;
      case "single_wall":
        quads = quadsBellWall[orientation];
        tex = texBellWall;
        break;
      case "double_wall":
        quads = quadsBellDoubleWall[orientation];
        tex = texBellDoubleWall;
        break;
    }
    this.quads = Model.join(quads, quadsBell[orientation]);

    this.textures = new Texture[this.quads.length];
    Arrays.fill(this.textures, bell);
    System.arraycopy(tex, 0, this.textures, 0, tex.length);
  }

  @Override
  public Quad[] getQuads() {
    return quads;
  }

  @Override
  public Texture[] getTextures() {
    return textures;
  }

  private static Quad[][] rotateQuadsNESW(Quad[] quads, int angleNorth, int angleEast, int angleSouth, int angleWest) {
    Quad[][] orientedQuadsBell = new Quad[4][];
    orientedQuadsBell[0] = angleNorth == 0 ? quads : Model.rotateY(quads, -Math.toRadians(angleNorth));
    orientedQuadsBell[1] = angleEast == 0 ? quads : Model.rotateY(quads, -Math.toRadians(angleEast));
    orientedQuadsBell[2] = angleSouth == 0 ? quads : Model.rotateY(quads, -Math.toRadians(angleSouth));
    orientedQuadsBell[3] = angleWest == 0 ? quads : Model.rotateY(quads, -Math.toRadians(angleWest));
    return orientedQuadsBell;
  }
}
