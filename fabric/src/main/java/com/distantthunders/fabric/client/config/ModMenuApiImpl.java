package com.distantthunders.fabric.client.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuApiImpl implements ModMenuApi {
    /*
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        #if MC_VER >= V1_20_4
            return ColorScreenNew::new;
        #elif MC_VER < V1_20_4 && MC_VER > V1_18_2
        return ColorScreenNew::new;
        #elif  MC_VER <= V1_18_2
            return screen -> AutoConfig.getConfigScreen(CUI_Config.class, screen).get();
        #endif

    }
    */
}
