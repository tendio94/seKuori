package com.sekuori.webdriver.element;

import org.junit.Test;

public class KuoriWebElementTest {
    @Test(expected = SearchContextNotSetException.class)
    public void testGetElementThrowsExceptionForNullSearchContext() {
        KuoriWebElement element = new KuoriWebElement();
        element.get(KuoriWebElement.class, element.getWebDriver());
    }
}