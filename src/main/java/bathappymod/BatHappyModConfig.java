package bathappymod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Arrays;
import java.util.List;

public class BatHappyModConfig {
    public static ForgeConfigSpec CommonConfig;
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> banMods;
    private static final ForgeConfigSpec.ConfigValue<? extends String> crashTip;
    private static final ForgeConfigSpec.BooleanValue showModid;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("ban mods list").push("Blacklist");
        banMods = builder.defineList("List", Arrays.asList(BatHappyMod.banModId), obj -> true);
        crashTip = builder.define("tip","你的MC看起来崩溃了,是因为:");
        showModid = builder.define("Whether to display the mod ID?",true);
        builder.pop();
        CommonConfig = builder.build();
    }

    public static void init(FMLCommonSetupEvent event) {
        BatHappyMod.LOGGER.info("hello world");
        if(BatHappyModConfig.banMods.get().contains(BatHappyMod.MOD_ID) || banMods.get().isEmpty()) {
            banMods.get().remove(BatHappyMod.MOD_ID);
            banMods.set(Arrays.asList(BatHappyMod.banModId));
        }
        for(String modIds : BatHappyModConfig.banMods.get()) {
            if(ModList.get().isLoaded(modIds)) {
                if(BatHappyModConfig.showModid.get())
                    throw new RuntimeException(BatHappyModConfig.crashTip.get() + modIds);
                else
                    throw new RuntimeException(BatHappyModConfig.crashTip.get());
            }
        }
    }
}
