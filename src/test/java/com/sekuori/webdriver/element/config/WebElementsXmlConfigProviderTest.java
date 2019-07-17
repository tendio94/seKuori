package com.sekuori.webdriver.element.config;

import com.sekuori.webdriver.element.DummyElement;
import com.sekuori.webdriver.element.KuoriWebElement;
import com.sekuori.webdriver.element.config.model.LocatorNotFoundException;
import com.sekuori.webdriver.element.config.reader.LocatorsXmlConfigReader;
import org.junit.Assert;
import org.junit.Test;

public class WebElementsXmlConfigProviderTest {
    private static final WebElementsXmlConfigProvider CONFIG_PROVIDER = new WebElementsXmlConfigProvider();
    private static final Class<DummyElement> CLASS = DummyElement.class;

    @Test(expected = LocatorNotFoundException.class)
    public void testGetLocatorsForNotConfiguredClassThrowsNotFoundException() {
        CONFIG_PROVIDER.getLocatorsForClass(KuoriWebElement.class);
    }

    @Test
    public void getFindContainerXpath() {
        Assert.assertEquals(".//input", CONFIG_PROVIDER.getFindContainerXpath(CLASS));
    }

    @Test
    public void getFindByNameXpath() {
        Assert.assertEquals(".//*[@aria-label='%s']", CONFIG_PROVIDER.getFindByNameXpath(CLASS));
    }

    @Test
    public void getLocatorsForClass() {
        Assert.assertNotNull(CONFIG_PROVIDER.getLocatorsForClass(CLASS));

    }

    @Test
    public void getConfigReader() {
        Assert.assertTrue(CONFIG_PROVIDER.getConfigReader() instanceof LocatorsXmlConfigReader);
    }
}