package com.holybuckets.chunkload.config;

import com.holybuckets.chunkload.Constants;
import net.blay09.mods.balm.api.config.reflection.Comment;
import net.blay09.mods.balm.api.config.reflection.Config;


@Config(Constants.MOD_ID)
public class BackgroundChunkLoaderConfig {
    public CBackgroundLoading backgroundLoading = new CBackgroundLoading();

        public static class CBackgroundLoading {

            @Comment("Quietly generates the world ahead of you while you play, so exploring feels smoother. Turn this off to stop all background work.")
            public boolean loadChunksInBackground = true;

            @Comment("How many chunks to generate each minute. Higher means the world is ready sooner but the server works harder. Valid range 1 - 100.")
            public int chunksLoadedPerMinute = 10;

            @Comment("Stop generating once the world folder reaches this size, in gigabytes. Protects you from filling your disk.")
            public int stopAtWorldSizeGigabytes = 8;

            @Comment("If the server average tick speed exceeds the below value, chunk preloads will be skipped as the server tries to catchup. Takes an integer value in  milliseconds. Default: 50.")
            public int skipChunkLoadingOnSlowServerTick = 50;
        }
    }