package com.sekuori.webdriver.element;

import com.sekuori.webdriver.WebDriverForTest;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class KuoriWebElementTest {
    private static final WebDriver DRIVER = WebDriverForTest.getDriver();

    @Test(expected = SearchContextNotSetException.class)
    public void testGetElementThrowsExceptionForNullSearchContext() {
        KuoriWebElement element = new KuoriWebElement();
        element.get(KuoriWebElement.class, null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetElementThrowsNotFoundException() {
        KuoriWebElement element = new KuoriWebElement();
        element.get(KuoriWebElement.class, DRIVER);
    }
}