package com.sekuori.webdriver.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

public interface WebElementContainer extends WebElement, WrapsElement {
    default WebElement getWebElement() {
        return getWrappedElement();
    }

    void setWebElement(WebElement element);
}
