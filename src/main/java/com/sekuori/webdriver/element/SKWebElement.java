package com.sekuori.webdriver.element;

import com.sekuori.webdriver.element.config.model.ConfigurableWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

interface SKWebElement extends NarrowedSearchContext, ConfigurableWebElement {
    void setWebElement(WebElement element);

    void setWebDriver(WebDriver driver);
}
