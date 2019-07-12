package com.sekuori.webdriver.element.config.model;

import java.util.List;

public class LocatorsConfig implements ConfigEntity {
    public void setLocators(List<Locators> locators) {
        this.locators = locators;
    }

    public List<Locators> getLocators() {
        return locators;
    }

    private List<Locators> locators;

}
