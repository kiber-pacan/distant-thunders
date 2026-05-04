package com.distantthunders.fabric;

import net.fabricmc.api.ModInitializer;

import com.distantthunders.DistantThunders;

public final class DistantThunders_Fabric implements ModInitializer {

    @Override
    public void onInitialize() {
        DistantThunders.initializeServer();
    }
}
