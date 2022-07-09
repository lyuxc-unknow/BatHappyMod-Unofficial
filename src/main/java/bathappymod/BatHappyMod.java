package bathappymod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("bathappymod_unofficial")
public class BatHappyMod {
    public static final Logger LOGGER = LogManager.getLogger("bathappymod_unofficial");

    public static final String[] banModId = new String[] {
            "torcherino", "xijun", "bacteria"
    };

    public BatHappyMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON,BatHappyModConfig.CommonConfig);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(BatHappyModConfig::init);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
