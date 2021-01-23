package se.llbit.chunky.entity;

import java.util.Collection;
import java.util.LinkedList;
import se.llbit.chunky.model.ItemFrameModel;
import se.llbit.chunky.resources.Texture;
import se.llbit.chunky.world.material.TextureMaterial;
import se.llbit.json.JsonValue;
import se.llbit.math.Transform;
import se.llbit.math.Vector3;
import se.llbit.math.primitive.Primitive;

public class ItemFrameEntity extends Entity {

  public ItemFrameEntity(Vector3 vector3) {
    super(vector3);
  }

  @Override
  public Collection<Primitive> primitives(Vector3 offset) {
    Collection<Primitive> faces = new LinkedList<>();
    Transform transform = Transform.NONE
        .translate(position.x + offset.x, position.y + offset.y, position.z + offset.z);
    for (int i = 0; i < ItemFrameModel.itemFrameQuads.length; i++) {
      ItemFrameModel.itemFrameQuads[i]
          .addTriangles(faces,
              new TextureMaterial(i > 1 ? Texture.birchPlanks : Texture.glowItemFrame),
              transform);
    }

    return faces;
  }

  @Override
  public JsonValue toJson() {
    return null;
  }
}
