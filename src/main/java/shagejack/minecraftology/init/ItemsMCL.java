package shagejack.minecraftology.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import shagejack.minecraftology.items.*;
import shagejack.minecraftology.util.LogMCL;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber

public class ItemsMCL {
    public static List<Item> items = new ArrayList<>();

    //	Tools
    public Multimeter multimeter;

    private int registeredCount = 0;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        items.forEach(i -> event.getRegistry().register(i));
    }

    public void init() {

        LogMCL.info("Registering items");

        //		Tools
        multimeter = register(new Multimeter("omni_multimeter"));

        LogMCL.info("Finished registering items");
        LogMCL.info("Registered %d items", registeredCount);

    }

    protected <T extends Item> T register(T item) {
        items.add(item);
        registeredCount++;
        return item;
    }
}