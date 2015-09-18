package com.hivemq.spi.services.configuration;

/**
 * A Configuration service which allows to get information about the current General configuration
 * and allows to change the global General configuration of HiveMQ at runtime.
 *
 * @author Christoph Schäbel
 * @since 3.0
 */
public interface GeneralConfigurationService {

    /**
     * @return <code>true</code> if the update check is enabled, <code>false</code> otherwise
     */
    boolean updateCheckEnabled();

    void setUpdateCheckEnabled(boolean updateCheckEnabled);
}
