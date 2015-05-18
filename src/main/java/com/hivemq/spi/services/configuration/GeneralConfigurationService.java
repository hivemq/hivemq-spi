package com.hivemq.spi.services.configuration;

/**
 * @author Christoph Schäbel
 */
public interface GeneralConfigurationService {

    boolean updateCheckEnabled();

    void setUpdateCheckEnabled(boolean updateCheckEnabled);
}
