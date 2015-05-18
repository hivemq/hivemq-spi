package com.hivemq.spi.services;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;

import java.util.List;

/**
 * A shared thread pool executor which is a {@link ListeningScheduledExecutorService}.
 * It is recommended to use this instead of creating your own thread(-pool) in your plugin.
 *
 * @author Christoph Schäbel
 */
public interface PluginExecutorService extends ListeningScheduledExecutorService {

    /**
     * The Plugin Executor Service is automatically shut down when HiveMQ is shut down.
     *
     * Manual calls to this method from the plugin system are not supported.
     *
     * @throws {@link UnsupportedOperationException}
     */
    @Override
    void shutdown();

    /**
     * The Plugin Executor Service is automatically shut down when HiveMQ is shut down.
     *
     * Manual calls to this method from the plugin system are not supported.
     *
     * @throws {@link UnsupportedOperationException}
     */
    @Override
    List<Runnable> shutdownNow();

}
