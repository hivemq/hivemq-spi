package com.hivemq.spi.services;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;

import java.util.List;

/**
 * A shared thread pool executor which is a {@link ListeningScheduledExecutorService}.
 * It is recommended to use this instead of creating your own thread(-pool) in your plugin.
 * <p/>
 * Use this class for all concurrent code.
 *
 * @author Christoph Schäbel
 */
public interface PluginExecutorService extends ListeningScheduledExecutorService {

    /**
     * DO NOT CALL THIS METHOD!
     * <p/>
     * The Plugin Executor Service is automatically shut down when HiveMQ is shut down.
     * <p/>
     * Manual calls to this method from the plugin system are not supported.
     *
     * @throws {@link UnsupportedOperationException} always
     */
    @Override
    void shutdown();

    /**
     * DO NOT CALL THIS METHOD!
     * <p/>
     * The Plugin Executor Service is automatically shut down when HiveMQ shuts down.
     * <p/>
     * Manual calls to this method from the plugin system are not supported.
     *
     * @throws {@link UnsupportedOperationException} always
     */
    @Override
    List<Runnable> shutdownNow();

}
