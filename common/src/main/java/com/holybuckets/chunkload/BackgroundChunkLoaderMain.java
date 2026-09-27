package com.holybuckets.chunkload;


import com.holybuckets.chunkload.config.BackgroundChunkLoaderConfig;
import com.holybuckets.foundation.GeneralConfig;
import com.holybuckets.foundation.config.PerformanceImpactConfig;
import com.holybuckets.foundation.event.EventRegistrar;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.event.server.ServerStartingEvent;

/**
 * Main instance of the mod, initialize this class statically via commonClass
 * This class will init all major Manager instances and events for the mod
 */
public class BackgroundChunkLoaderMain {
    private static boolean DEV_MODE = false;;
    private static BackgroundChunkLoaderConfig CONFIG;
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
        CONFIG = Balm.getConfig().getActiveConfig(BackgroundChunkLoaderConfig.class);
        this.DEV_MODE = false;
        apply();
    }

    public static void apply() {
        BackgroundChunkLoaderConfig.CBackgroundLoading cfg = CONFIG.backgroundLoading;
        PerformanceImpactConfig foundation = GeneralConfig.getInstance().getPerformanceImpactConfig();

        boolean doLoad = cfg.loadChunksInBackground;

        if (!doLoad) {
            doLoad = false;
            LoggerProject.logWarning("001000", "Background chunk loading disabled");
        }

        foundation.setEnableChunkExplorer(doLoad);
        foundation.setChunkExploreRate(ChunkLoadFrequency.valueOf(cfg.chunkLoadFreqeuncy.toUpperCase()).getValue());
        foundation.setChunkExploreMaximumDiskSize(cfg.stopAtWorldSizeGigabytes);
        foundation.setChunkExploreMaxAllowedTickSize(cfg.skipChunkLoadingOnSlowServerTick);
    }

    public static enum ChunkLoadFrequency {
        LOW(400),
        MEDIUM(100),
        HIGH(50),
        EXTREME(10);

        private final int value;

        ChunkLoadFrequency(int value) {this.value = value; }
        public int getValue() { return value; }
    }


}
