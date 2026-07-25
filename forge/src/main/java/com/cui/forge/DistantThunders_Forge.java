package com.cui.forge;


import com.distantthunders.DistantThunders;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraftforge.fml.loading.FMLLoader;

@Mod(DistantThunders.MOD_ID)
public final class DistantThunders_Forge {
    public DistantThunders_Forge(FMLJavaModLoadingContext context) {
        EventBuses.registerModEventBus(DistantThunders.MOD_ID, MinecraftForge.EVENT_BUS);
        DistantThunders.initializeServer();

        if (FMLLoader.getDist() == Dist.CLIENT) {
            DistantThunders.initializeClient();
        }
    }
}
