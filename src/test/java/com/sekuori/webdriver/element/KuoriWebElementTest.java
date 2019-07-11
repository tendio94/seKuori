package com.sekuori.webdriver.element;

import org.junit.Test;

public class KuoriWebElementTest {
    @Test
    public void testWrappedWebElementCreation() {
        KuoriWebElement element = new KuoriWebElement();
        element.get(KuoriWebElement.class, element);
    }
}