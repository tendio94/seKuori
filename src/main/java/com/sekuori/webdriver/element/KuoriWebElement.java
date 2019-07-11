package com.sekuori.webdriver.element;

import com.sekuori.webdriver.element.config.model.Locator;
import org.jetbrains.annotations.NotNull;

public class KuoriWebElement extends ProxyWebElement {
    //default constructor implicitly defined - required for reflection calls
    public KuoriWebElement() {

    }

    @Override
    public <T extends KuoriWebElement> T get(Class<? extends KuoriWebElement> clazz, @NotNull NarrowedSearchContext context) {
        return null;
    }

    @Override
    public Locator getLocators() {
        return null;
    }
}
