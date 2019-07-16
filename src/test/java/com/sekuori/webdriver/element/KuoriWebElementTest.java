package com.sekuori.webdriver.element;

import com.sekuori.webdriver.element.config.model.LocatorNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import static com.sekuori.webdriver.WebDriverForTest.getDriver;

public class KuoriWebElementTest {

    @Test(expected = SearchContextNotSetException.class)
    public void testGetElementThrowsExceptionForNullSearchContext() {
        KuoriWebElement element = new KuoriWebElement();
        element.get(KuoriWebElement.class, null);
    }

    @Test(expected = LocatorNotFoundException.class)
    public void testGetElementThrowsNotFoundException() {
        KuoriWebElement element = new KuoriWebElement();
        element.get(KuoriWebElement.class, getDriver());
    }

    @Test
    public void testDummyElementConfigIsResolved() {
        DummyElement element = new DummyElement();
        Assert.assertTrue(element.getConfiguredLocators()
                .getFindByNameXpath().equals(".//*[@aria-label='%s']"));
        Assert.assertTrue(element.getConfiguredLocators()
                .getFindContainerXpath().equals(".//input"));
    }

    @Test
    public void testGetElementFindsCustomElement() {
        KuoriWebElement element = new KuoriWebElement();
        getDriver().get("https://www.google.com/");

        String expectedTagName = "input";
        String actualTagName = element.get(DummyElement.class, getDriver(), "Мне повезёт!").getTagName();
        Assert.assertEquals(expectedTagName, actualTagName);
    }

}