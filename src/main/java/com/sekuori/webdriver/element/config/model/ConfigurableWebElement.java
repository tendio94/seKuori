package com.sekuori.webdriver.element.config.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;

public interface ConfigurableWebElement extends Locatable, WebElement, WrapsElement {
    /**
     * @return object containing locators for this web element to be found by
     */
    Locator getLocators();
}
