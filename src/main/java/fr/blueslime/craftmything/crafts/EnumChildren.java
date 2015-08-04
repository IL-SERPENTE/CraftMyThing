package fr.blueslime.craftmything.crafts;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum EnumChildren
{
    // Environment
    LEAVES(Material.LEAVES, Material.SAPLING),
    LEAVES_2(Material.LEAVES_2, Material.SAPLING),
    SAND(Material.SAND, Material.GLASS),
    GRAVEL(Material.GRAVEL, Material.FLINT),
    WEB(Material.WEB, Material.STRING),
    SNOW_BLOCK(Material.SNOW_BLOCK, Material.SNOW_BALL),
    SUGAR_CANE(Material.SUGAR_CANE_BLOCK, Material.PAPER),
    GLOWSTONE(Material.GLOWSTONE, Material.GLOWSTONE_DUST, 0, 2),
    FENCE(Material.FENCE, Material.STICK, 0, 4),
    ACACIA_FENCE(Material.ACACIA_FENCE, Material.STICK, 0, 4),
    BIRCH_FENCE(Material.BIRCH_FENCE, Material.STICK, 0, 4),
    DARK_OAK_FENCE(Material.DARK_OAK_FENCE, Material.STICK, 0, 4),
    JUNGLE_FENCE(Material.JUNGLE_FENCE, Material.STICK, 0, 4),
    SPRUCE_FENCE(Material.SPRUCE_FENCE, Material.STICK, 0, 4),
    IRON_FENCE(Material.IRON_FENCE, Material.IRON_INGOT, 0, 4),
    CLAY(Material.CLAY, Material.CLAY_BALL),
    PRISMARINE(Material.PRISMARINE, Material.PRISMARINE_SHARD, 0, 2),
    SEA_LANTERN(Material.SEA_LANTERN, Material.PRISMARINE_SHARD, 0, 4),
    HAY(Material.HAY_BLOCK, Material.WHEAT, 0, 2),

    // Food
    WHEAT(Material.CROPS, Material.WHEAT),
    CARROT(Material.CARROT, Material.CARROT_ITEM),
    POTATO(Material.POTATO, Material.POTATO_ITEM),
    MELON(Material.MELON_BLOCK, Material.MELON),
    COCOA(Material.COCOA, Material.INK_SACK, 3, 1),

    // Ores
    COAL_ORE(Material.COAL_ORE, Material.COAL),
    IRON_ORE(Material.IRON_ORE, Material.IRON_INGOT),
    GOLD_ORE(Material.GOLD_ORE, Material.GOLD_INGOT),
    LAPIS_ORE(Material.LAPIS_ORE, Material.INK_SACK, 4, 1),
    REDSTONE_ORE(Material.REDSTONE_ORE, Material.REDSTONE),
    GLOWING_REDSTONE_ORE(Material.GLOWING_REDSTONE_ORE, Material.REDSTONE),
    DIAMOND_ORE(Material.DIAMOND_ORE, Material.DIAMOND),
    EMERALD_ORE(Material.EMERALD_ORE, Material.EMERALD),
    QUARTZ_ORE(Material.QUARTZ_ORE, Material.QUARTZ),

    // Misc
    WOOD_DOOR(Material.WOOD_DOOR, Material.WOOD, 0, 4),
    ACACIA_DOOR(Material.ACACIA_DOOR, Material.WOOD, 0, 4),
    BIRCH_DOOR(Material.BIRCH_DOOR, Material.WOOD, 0, 4),
    DARK_OAK_DOOR(Material.DARK_OAK_DOOR, Material.WOOD, 0, 4),
    JUNGLE_DOOR(Material.JUNGLE_DOOR, Material.WOOD, 0, 4),
    SPRUCE_DOOR(Material.SPRUCE_DOOR, Material.WOOD, 0, 4),
    IRON_DOOR(Material.IRON_DOOR, Material.IRON_INGOT, 0, 4),
    IRON_TRAPDOOR(Material.IRON_TRAPDOOR, Material.IRON_INGOT, 0, 4),
    SIGN(Material.SIGN, Material.WOOD, 0, 4),
    WALL_SIGN(Material.WALL_SIGN, Material.WOOD, 0, 4),
    SIGN_POST(Material.SIGN_POST, Material.WOOD, 0, 4),

    // Possible crafts
    ENCHANTMENT_TABLE(Material.ENCHANTMENT_TABLE, Material.OBSIDIAN),
    BREWING_STAND(Material.BREWING_STAND, Material.BLAZE_ROD),
    CAULDRON(Material.CAULDRON, Material.IRON_INGOT, 0, 3),
    ENDER_CHEST(Material.ENDER_CHEST, Material.EYE_OF_ENDER),
    BEACON(Material.BEACON, Material.NETHER_STAR),
    ANVIL(Material.ANVIL, Material.IRON_INGOT, 0, 3),
    SLIME_BLOCK(Material.SLIME_BLOCK, Material.SLIME_BALL, 0, 2),
    ;

    private final Material parent;
    private final Material child;
    private final int childData;
    private final int amount;

    EnumChildren(Material parent, Material child)
    {
        this(parent, child, 0, 1);
    }

    EnumChildren(Material parent, Material child, int childData, int amount)
    {
        this.parent = parent;
        this.child = child;
        this.childData = childData;
        this.amount = amount;
    }

    public Material getParent()
    {
        return this.parent;
    }

    public Material getChild()
    {
        return this.child;
    }

    public int getChildData()
    {
        return this.childData;
    }

    public int getAmount()
    {
        return this.amount;
    }

    public static ItemStack getChild(ItemStack parent)
    {
        for(EnumChildren couple : EnumChildren.values())
            if(couple.getParent() == parent.getType())
                return new ItemStack(couple.getChild(), couple.getAmount(), (short) couple.getChildData());

        return parent;
    }

    public static ItemStack getChild(Material parent)
    {
        return getChild(new ItemStack(parent));
    }

    public static boolean hasChild(ItemStack parent)
    {
        for(EnumChildren couple : EnumChildren.values())
            if(couple.getParent() == parent.getType())
                return true;

        return false;
    }

    public static boolean hasChild(Material parent)
    {
        return hasChild(new ItemStack(parent));
    }
}
