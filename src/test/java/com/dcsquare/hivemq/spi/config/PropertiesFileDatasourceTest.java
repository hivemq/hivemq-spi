package com.dcsquare.hivemq.spi.config;

import com.google.common.io.Closeables;
import com.netflix.config.PollResult;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Dominik Obermaier
 */
public class PropertiesFileDatasourceTest {

    public static final String PROPERTIES_FILE = "properties.file";
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void test_read_properties_file_successful() throws Exception {
        final File file = temporaryFolder.newFile(PROPERTIES_FILE);
        final PropertiesFileDatasource datasource = new PropertiesFileDatasource(file.getParentFile().getAbsoluteFile(), PROPERTIES_FILE);

        writeProperties(file);
        final PollResult poll = datasource.poll(false, null);
        final Map<String, Object> result = poll.getComplete();

        assertEquals(1, result.size());
        assertEquals("value", result.get("key"));
    }


    private void writeProperties(final File file) throws Exception {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream(file);

            prop.setProperty("key", "value");
            prop.store(output, null);

        } finally {
            Closeables.close(output, false);
        }
    }


}
