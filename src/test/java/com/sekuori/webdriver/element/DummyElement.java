package com.sekuori.webdriver.element;

import com.sekuori.webdriver.element.config.model.Locators;
import org.apache.logging.log4j.util.Strings;

public class DummyElement extends KuoriWebElement {
    @Override
    public Locators getConfiguredLocators() {
        return new Locators(Strings.EMPTY, ".//input");
    }
}
