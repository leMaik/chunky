package se.llbit.chunky.block.jsonmodels;

import java.util.Collection;
import java.util.LinkedList;
import se.llbit.chunky.block.Block;
import se.llbit.chunky.block.ModelBlock;
import se.llbit.chunky.entity.Entity;
import se.llbit.chunky.model.BlockModel;
import se.llbit.chunky.model.QuadModel;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.resources.Texture;
import se.llbit.chunky.world.Material;
import se.llbit.chunky.world.material.TextureMaterial;
import se.llbit.json.JsonObject;
import se.llbit.json.JsonValue;
import se.llbit.math.Quad;
import se.llbit.math.Ray;
import se.llbit.math.Transform;
import se.llbit.math.Vector3;
import se.llbit.math.primitive.Primitive;
import se.llbit.nbt.CompoundTag;

public class QuadBlock extends Block implements QuadModel, ModelBlock {

  private final Quad[] quads;
  private final Texture[] textures;
  private final boolean isEntity;
  public boolean supportsOpacity = true; // some blocks only support full or zero opacity and round alpha values to 0 or 1

  public QuadBlock(String name, Texture texture, Quad[] quads, Texture[] textures,
      boolean isEntity) {
    super(name, texture);
    localIntersect = true;
    opaque = false;
    solid = false;
    invisible = isEntity;
    this.isEntity = isEntity;
    this.textures = textures;
    this.quads = quads;
  }

  @Override
  public BlockModel getModel() {
    return this;
  }

  @Override
  public Quad[] getQuads() {
    return quads;
  }

  @Override
  public Texture[] getTextures() {
    return textures;
  }

  @Override
  public boolean isBlockEntity() {
    // TODO we could further optimize this by only putting the out-of-bounds quads into the BVH and rendering the rest (if any) as block
    return isEntity;
  }

  @Override
  public Entity toBlockEntity(Vector3 position, CompoundTag entityTag) {
    return new Entity(position) {
      @Override
      public Collection<Primitive> primitives(Vector3 offset) {
        Collection<Primitive> faces = new LinkedList<>();
        Transform transform =
            Transform.NONE.translate(
                position.x + offset.x, position.y + offset.y, position.z + offset.z);
        for (int i = 0; i < quads.length; i++) {
          Quad quad = quads[i];
          Texture texture = textures[i];
          Material material = new TextureMaterial(texture);
          material.emittance = emittance;
          material.specular = specular;
          material.ior = ior;
          quad.addTriangles(faces, material, transform);
        }
        return faces;
      }

      @Override
      public JsonValue toJson() {
        // TODO
        return new JsonObject();
      }
    };
  }

  @Override
  public boolean intersect(Ray ray, Scene scene) {
    return !invisible && QuadModel.super.intersect(ray, scene);
  }
}
