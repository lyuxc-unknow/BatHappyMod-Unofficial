package bathappymod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModLoadingException;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Mod(BatHappyMod.MOD_ID)
public class BatHappyMod {
    public static final String MOD_ID = "bathappymod_unofficial";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static ForgeConfigSpec CommonConfig;
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> banMods;

    public BatHappyMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,BatHappyMod.CommonConfig);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final String[] banModId = new String[] {
            "torcherino", "xijun", "bacteria"
    };

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("ban mods list").push("Blacklist");
        banMods = builder.defineList("ban mods list", List.of(banModId), obj -> true);
        builder.pop();
        CommonConfig = builder.build();
    }

    public void init(FMLCommonSetupEvent event) {
        if(BatHappyMod.banMods.get().contains(BatHappyMod.MOD_ID)
                || BatHappyMod.banMods.get().contains("ts_mod")
                || banMods.get().isEmpty()
                || BatHappyMod.banMods.get().contains("")) {
            banMods.get().remove(BatHappyMod.MOD_ID);
            banMods.set(List.of(banModId));
        }
        for(String modIds : BatHappyMod.banMods.get()) {
            if(ModList.get().isLoaded(modIds)) {
                throw new RuntimeException("null");
            }
        }
    }
}
