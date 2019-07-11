package com.sekuori.webdriver.element.config.model;

import java.util.List;

public class LocatorsConfig implements ConfigEntity {
    public void setLocators(List<Locator> locators) {
        this.locators = locators;
    }

    public List<Locator> getLocators() {
        return locators;
    }

    private List<Locator> locators;

}
