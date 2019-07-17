package com.sekuori.webdriver.element;

import org.junit.Assert;
import org.junit.Test;

import static com.sekuori.webdriver.WebDriverForTest.getDriver;

public class KuoriWebElementTest {

    @Test(expected = SearchContextNotSetException.class)
    public void testGetElementThrowsExceptionForNullSearchContext() {
        KuoriWebElement element = new KuoriWebElement();
        element.get(KuoriWebElement.class, null);
    }

    @Test
    public void testGetElementFindsWebElementByCustomConfiguration() {
        KuoriWebElement element = new KuoriWebElement();
        getDriver().get("https://www.google.com/");

        String expectedTagName = "input";
        String actualTagName = element.get(DummyElement.class, getDriver(), "Мне повезёт!").getTagName();
        Assert.assertEquals(expectedTagName, actualTagName);
    }

}