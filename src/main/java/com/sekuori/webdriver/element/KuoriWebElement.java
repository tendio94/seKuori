package com.sekuori.webdriver.element;

import com.sekuori.webdriver.element.config.model.Locator;
import org.openqa.selenium.SearchContext;

public class KuoriWebElement extends ProxyWebElement {
    //default constructor implicitly defined - required for reflection calls
    public KuoriWebElement() {
    }

    @Override
    public <T extends KuoriWebElement> T get(Class<? extends KuoriWebElement> clazz, SearchContext context) {
        checkSearchContext(context);
        return null;
    }

    @Override
    public Locator getLocators() {
        return null;
    }

    private static void checkSearchContext(SearchContext context) {
        if (context == null) {
            throw new SearchContextNotSetException();
        }
    }
}
