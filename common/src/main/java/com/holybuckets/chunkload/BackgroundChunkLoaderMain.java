package com.holybuckets.chunkload;


import com.holybuckets.foundation.event.EventRegistrar;
import com.holybuckets.chunkload.config.TemplateConfig;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.event.EventPriority;
import net.blay09.mods.balm.api.event.server.ServerStartingEvent;

/**
 * Main instance of the mod, initialize this class statically via commonClass
 * This class will init all major Manager instances and events for the mod
 */
public class BackgroundChunkLoaderMain {
    private static boolean DEV_MODE = false;;
    private static TemplateConfig CONFIG;
    public static BackgroundChunkLoaderMain INSTANCE;

    public BackgroundChunkLoaderMain()
    {
        super();
        INSTANCE = this;
        init();
        // LoggerProject.logInit( "001000", this.getClass().getName() ); // Uncomment if you have a logging system in place
    }

    private void init()
    {
        //Events
        EventRegistrar registrar = EventRegistrar.getInstance();
        registrar.registerOnBeforeServerStarted(this::onServerStarting);

    }

    private void onServerStarting(ServerStartingEvent e) {
        CONFIG = Balm.getConfig().getActiveConfig(TemplateConfig.class);
        this.DEV_MODE = false;
        apply();
    }

    public static void apply() {
        CBackgroundLoading cfg = CONFIG.backgroundLoading;
        PerformanceImpactConfig foundation = GeneralConfig.getInstance().getPerformanceImpactConfig();

        foundation.setEnableChunkExplorer(cfg.loadChunksInBackground);
        foundation.setChunkExploreRate(cfg.chunksLoadedPerMinute);
        foundation.setChunkExploreMaximumDiskSize(cfg.stopAtWorldSizeGigabytes);
        foundation.setChunkExploreMaxAllowedTickSize(cfg.skipChunkLoadingOnSlowServerTick);
    }


}
