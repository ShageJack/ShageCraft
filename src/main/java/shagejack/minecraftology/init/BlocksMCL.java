package shagejack.minecraftology.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shagejack.minecraftology.api.internal.IItemBlockFactory;
import shagejack.minecraftology.api.internal.OreDictItem;
import shagejack.minecraftology.blocks.BlockBronzeTube;
import shagejack.minecraftology.blocks.BlockBuilding;
import shagejack.minecraftology.blocks.BlockClayFurnaceBottom;
import shagejack.minecraftology.blocks.BlockGravity;
import shagejack.minecraftology.util.LogMCL;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber

public class BlocksMCL {
        public static List<Block> blocks = new ArrayList<>();
        public static List<Item> items = new ArrayList<>();

        //Building
        public BlockBuilding building_fine_clay;

        //Gravity
        public BlockGravity gravity_iron_oxide;
        public BlockGravity gravity_charcoal;
        public BlockGravity gravity_calcite;

        //Mechanic
        public BlockClayFurnaceBottom mechanic_clay_furnace_bottom;
        public BlockBronzeTube mechanic_bronze_tube;

        private int registeredCount = 0;

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            blocks.forEach(b -> event.getRegistry().register(b));
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            items.forEach(i -> event.getRegistry().register(i));
            ItemsMCL.items.stream().filter(item -> item instanceof OreDictItem).forEach(item -> ((OreDictItem) item).registerOreDict());
            BlocksMCL.blocks.stream().filter(block -> block instanceof OreDictItem).forEach(block -> ((OreDictItem) block).registerOreDict());
        }

        public void init() {

            LogMCL.info("Registering blocks");

            building_fine_clay = register(new BlockBuilding(Material.CLAY, "building.fine_clay", 4, 0, 4, SoundType.GROUND));

            gravity_iron_oxide = register(new BlockGravity(Material.SAND, "gravity.iron_oxide", 4, 1, 4));
            gravity_charcoal = register(new BlockGravity(Material.SAND, "gravity.charcoal", 4, 0, 4));
            gravity_calcite = register(new BlockGravity(Material.SAND, "gravity.calcite", 4, 0, 4));

            mechanic_clay_furnace_bottom = register(new BlockClayFurnaceBottom(Material.CLAY, "mechanic.clay_furnace_bottom"));
            mechanic_bronze_tube = register(new BlockBronzeTube(Material.CLAY, "mechanic.bronze_tube"));

            LogMCL.info("Finished registering blocks");
            LogMCL.info("Registered %d blocks", registeredCount);

        }

        protected <T extends Block> T register(T block) {
            ItemBlock itemBlock;
            if (block instanceof IItemBlockFactory) {
                itemBlock = ((IItemBlockFactory) block).createItemBlock();
           //} else if (block instanceof MCLBlockTileEntity) {
           //     itemBlock = new MCLBlockTileEntityItem(block);
            } else {
                itemBlock = new ItemBlock(block);
                itemBlock.setRegistryName(block.getRegistryName());
            }
            return register(block, itemBlock);
        }

        protected <T extends Block> T register(T block, ItemBlock itemBlock) {
            //if (block instanceof IConfigSubscriber) {
            //    Minecraftology.CONFIG_HANDLER.subscribe((IConfigSubscriber) block);
            //}
            registeredCount++;
            blocks.add(block);
            items.add(itemBlock);
            return block;
        }

    }