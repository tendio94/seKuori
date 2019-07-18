package com.sekuori.webdriver.element;

import com.sekuori.webdriver.element.config.WebElementsXmlConfigProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

import java.util.List;

abstract class ProxyWebElement<T extends IKuoriWebElement> implements IKuoriWebElement<T> {
    static final WebElementsXmlConfigProvider CONFIG_PROVIDER = new WebElementsXmlConfigProvider();
    protected WebElement element;
    protected WebDriver driver;

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) element).getCoordinates();
    }

    @Override
    public WebElement getWebElement() {
        return element;
    }

    @Override
    public void setWebElement(WebElement element) {
        this.element = element;
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    @Override
    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void click() {
        element.click();
    }

    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        element.sendKeys(charSequences);
    }

    @Override
    public void clear() {
        element.clear();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public String getAttribute(String s) {
        return element.getAttribute(s);
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public String getCssValue(String s) {
        return element.getCssValue(s);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return element.getScreenshotAs(outputType);
    }

    @Override
    public WebElement getWrappedElement() {
        return getWebElement();
    }
}
