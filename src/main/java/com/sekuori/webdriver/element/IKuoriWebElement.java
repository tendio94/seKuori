package com.sekuori.webdriver.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;

interface SKWebElement extends NarrowedSearchContext, Locatable, WebElement, WrapsElement {
    WebElement getWebElement();

    void setWebElement(WebElement element);

    WebDriver getWebDriver();

    void setWebDriver(WebDriver driver);
}
