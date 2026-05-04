package com.cui.neoforge;


import com.distantthunders.DistantThunders;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import static com.distantthunders.DistantThunders.MOD_ID;

@Mod(MOD_ID)
public final class DistantThunders_NeoForge {

    public DistantThunders_NeoForge() {
        // Initialization
        DistantThunders.initializeServer();

        /*
        ModLoadingContext.get().registerExtensionPoint(
                #if MC_VER >= V1_21
                IConfigScreenFactory.class,
                () -> (client, parent) -> new ColorScreenNew(parent)
                #else
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                (client, parent) -> new ColorScreenNew(parent))
                #endif
        );
        */

        if (#if MC_VER >= V1_21_9 FMLEnvironment.getDist().isClient() #else FMLEnvironment.dist.isClient() #endif) {
            DistantThunders.initializeClient();
        }
    }
}
