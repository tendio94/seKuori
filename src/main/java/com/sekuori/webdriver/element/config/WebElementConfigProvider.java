package com.sekuori.webdriver.element.config;

import com.sekuori.webdriver.element.config.model.Locators;
import com.sekuori.webdriver.element.config.reader.ConfigReader;

public interface WebElementConfigProvider {
    /**
     * @return object containing locators for web element of particular class
     */
    Locators getLocatorsForClass(Class clazz);

    ConfigReader getConfigReader();
}
