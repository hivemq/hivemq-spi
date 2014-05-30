/*
 * Copyright 2014 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dcsquare.hivemq.spi.config;

import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.google.common.io.Closeables.closeQuietly;
import static com.netflix.config.PollResult.createFull;

/**
 * This is used in {@link Configurations} to provide an easy to use property-based configuration.
 *
 * @author Dominik Obermaier
 * @since 1.4
 */
public class PropertiesFileDatasource implements PolledConfigurationSource {

    private final static Logger log = LoggerFactory.getLogger(PropertiesFileDatasource.class);

    private final Iterable<String> propertiesFileNames;
    private boolean optional;
    private final File baseFolder;

    public PropertiesFileDatasource(final File baseFolder, final String... propertiesFileNames) {

        this(baseFolder, Arrays.asList(propertiesFileNames));
    }

    public PropertiesFileDatasource(final File baseFolder, final Iterable<String> propertiesFileNames) {
        this(baseFolder, propertiesFileNames, false);
    }

    public PropertiesFileDatasource(final File baseFolder, final Iterable<String> propertiesFileNames, final boolean optional) {
        this.propertiesFileNames = propertiesFileNames;
        this.optional = optional;
        this.baseFolder = baseFolder;
    }

    @Override
    public PollResult poll(final boolean initial, final Object checkPoint) throws Exception {

        final Map<String, Object> propertiesMap = new HashMap<String, Object>();
        for (final String fileName : propertiesFileNames) {

            final File configFromFolder = new File(baseFolder, fileName);

            if (configFromFolder.canRead() && configFromFolder.isFile()) {

                final Properties properties = loadProperties(configFromFolder);
                for (final Map.Entry<Object, Object> property : properties.entrySet()) {
                    propertiesMap.put((String) property.getKey(), property.getValue());
                }
            } else {
                if (!optional) {
                    log.warn("Properties File {} does not exist in {} ", fileName, baseFolder);
                } else {
                    log.trace("Properties File {} does not exist in {} ", fileName, baseFolder);
                }
            }
        }
        return createFull(propertiesMap);
    }


    private Properties loadProperties(final File file) throws Exception {
        final Properties properties = new Properties();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            properties.load(fis);
        } finally {
            closeQuietly(fis);
        }
        return properties;
    }
}
