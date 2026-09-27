package com.holybuckets.chunkload.client;

import com.holybuckets.foundation.client.ClientBalmEventRegister;
import com.holybuckets.foundation.client.ClientEventRegistrar;


public class CommonClassClient {

    public static void initClient() {
        ClientEventRegistrar registrar = ClientEventRegistrar.getInstance();
        ClientBalmEventRegister.registerEvents();
    }

    /**
     * Description: Run sample tests methods
     */
    public static void sample()
    {

    }


}