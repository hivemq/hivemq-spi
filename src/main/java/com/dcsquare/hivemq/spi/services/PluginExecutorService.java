package com.dcsquare.hivemq.spi.services;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;

/**
 * A shared thread pool executor which is a {@link ListeningScheduledExecutorService}.
 * It is recommended to use this instead of creating your own thread(-pool) in your plugin.
 *
 * @author Christoph Schäbel
 */
public interface PluginExecutorService extends ListeningScheduledExecutorService {

}
