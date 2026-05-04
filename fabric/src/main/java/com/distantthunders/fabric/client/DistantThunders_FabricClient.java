package com.distantthunders.fabric.client;

import com.distantthunders.DistantThunders;
import net.fabricmc.api.ClientModInitializer;

public final class DistantThunders_FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        DistantThunders.initializeClient();

    }
}
