package com.hivemq.spi.config;

import java.io.File;

/**
 * Useful information about HiveMQ and the underlying system
 *
 * @author Christoph Schäbel
 * @since 3.0
 */
public interface SystemInformation {

    /**
     * @return the version string of HiveMQ
     */
    String getHiveMQVersion();

    /**
     * @return the home folder of HiveMQ
     */
    File getHiveMQHomeFolder();

    /**
     * @return the plugin folder of HiveMQ
     */
    File getPluginFolder();

    /**
     * @return the config folder of HiveMQ
     */
    File getConfigFolder();

    /**
     * @return the log folder of HiveMQ
     */
    File getLogFolder();

    /**
     * @return the license folder of HiveMQ
     */
    File getLicenseFolder();

    /**
     * @return the data folder of HiveMQ
     */
    File getDataFolder();

}
