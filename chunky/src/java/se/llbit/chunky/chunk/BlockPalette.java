package se.llbit.chunky.chunk;

import se.llbit.chunky.block.Block;
import se.llbit.nbt.CompoundTag;
import se.llbit.nbt.StringTag;
import se.llbit.nbt.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BlockPalette {
  public final int airId, stoneId;

  /** Stone blocks are used for filling invisible regions in the Octree. */
  public final Block stone;

  private final Map<BlockSpec, Integer> blockMap = new HashMap<>();
  private static final Map<String, Consumer<Block>> materialProperties = new HashMap<>();
  private final List<Block> palette = new ArrayList<>();

  public BlockPalette() {
    CompoundTag airTag = new CompoundTag();
    airTag.add("Name", new StringTag("minecraft:air"));
    CompoundTag stoneTag = new CompoundTag();
    stoneTag.add("Name", new StringTag("minecraft:stone"));
    airId = put(airTag);
    stoneId = put(stoneTag);
    stone = get(stoneId);
  }

  /**
   * Adds a new block to the palette and returns the palette index.
   * @param tag NBT tag for the block.
   * @return the palette index of the block in this palette.
   */
  public int put(Tag tag) {
    return put(new TagBlockSpec(tag));
  }

  public int put(BlockSpec spec) {
    Integer id = blockMap.get(spec);
    if (id != null) {
      return id;
    }
    id = palette.size();
    blockMap.put(spec, id);
    Block block = spec.toBlock();
    applyMaterial(block);
    palette.add(block);
    return id;
  }

  private void applyMaterial(Block block) {
    Consumer<Block> properties = materialProperties.get(block.name);
    if (properties != null) {
      properties.accept(block);
    }
  }

  public Block get(int id) {
    return palette.get(id);
  }

  static {
    materialProperties.put("minecraft:water", block -> {
      block.specular = 0.12f;
      block.ior = 1.333f;
    });
    materialProperties.put("minecraft:lava", block -> {
      block.emittance = 1.0f;
    });
    materialProperties.put("minecraft:glass", block -> {
      block.emittance = 1.52f;
    });
    materialProperties.put("minecraft:gold_block", block -> {
      block.emittance = 0.04f;
    });
    materialProperties.put("minecraft:diamond_block", block -> {
      block.emittance = 0.04f;
    });
    materialProperties.put("minecraft:iron_block", block -> {
      block.emittance = 0.04f;
    });
    materialProperties.put("minecraft:redstone_torch", block -> {
      block.emittance = 1.0f;
    });
    materialProperties.put("minecraft:torch", block -> {
      block.emittance = 50.0f;
    });
    materialProperties.put("minecraft:wall_torch", block -> {
      block.emittance = 50.0f;
    });
    materialProperties.put("minecraft:fire", block -> {
      block.emittance = 1.0f;
    });
    materialProperties.put("minecraft:ice", block -> {
      block.ior = 1.31f;
    });
    materialProperties.put("minecraft:glowstone", block -> {
      block.emittance = 1.0f;
    });
    materialProperties.put("minecraft:portal", block -> {
      block.emittance = 0.4f;
    });
    materialProperties.put("minecraft:jackolantern", block -> {
      block.emittance = 1.0f;
    });
    materialProperties.put("minecraft:beacon", block -> {
      block.emittance = 1.0f;
      block.ior = 1.52f;
    });
    /*STAINED_GLASS.ior = 1.52f;
    GLASSPANE.ior = 1.52f;
    REDSTONELAMPON.emittance = 1.0f;
    EMERALDBLOCK.specular = 0.04f;
    STAINED_GLASSPANE.ior = 1.52f;
    SEALANTERN.emittance = 0.5f;
    ENDROD.emittance = 1.0f;
    FROSTEDICE.ior = 1.31f;
    MAGMA.emittance = 0.6f;*/
  }
}