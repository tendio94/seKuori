package com.sekuori.webdriver.element.config.reader;

import com.sekuori.webdriver.element.config.model.ConfigEntity;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class LocatorsXmlConfigReaderTest {
    private static final ConfigReader READER = new LocatorsXmlConfigReader();
    private static final String NON_EXISTING_FILENAME = "nonExistingFile.xml";

    @Test
    public void testReadConfigWithNullParameterReadsDefaultXmlConfig() {
        ConfigEntity config = READER.readConfig(null);

        Assert.assertNotNull(config);
    }

    @Test(expected = ConfigReader.ConfigNotReadException.class)
    public void testReadConfigWithNonExistingFileParameterThrowsException() {
        READER.readConfig(new File(NON_EXISTING_FILENAME));
    }
}