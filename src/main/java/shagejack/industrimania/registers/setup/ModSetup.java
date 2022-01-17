package shagejack.industrimania.registers.setup;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import shagejack.industrimania.Industrimania;
import shagejack.industrimania.commands.CommandRemoveRocks;
import shagejack.industrimania.content.worldGen.GenerationRegistry;
import shagejack.industrimania.registers.AllCommands;

@Mod.EventBusSubscriber(modid = Industrimania.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(GenerationRegistry::onBiomesLoaded);
        bus.addListener(AllCommands::registerCommand);

    }

}
