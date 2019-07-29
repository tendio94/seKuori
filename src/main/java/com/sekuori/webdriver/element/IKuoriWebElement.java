package com.sekuori.webdriver.element;

import com.sekuori.webdriver.KuoriWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;

public interface IKuoriWebElement extends Locatable, WebElement, WrapsElement {
    WebElement getWebElement();

    void setWebElement(WebElement element);

    KuoriWebDriver getWebDriver();

    void setWebDriver(KuoriWebDriver driver);
}
