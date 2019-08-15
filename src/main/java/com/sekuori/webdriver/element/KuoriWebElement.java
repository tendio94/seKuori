package com.sekuori.webdriver.element;

import com.sekuori.webdriver.KuoriWebDriver;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

import java.util.List;

public class KuoriWebElement implements WebElementContainer, Locatable {
    protected KuoriWebDriver driver;
    protected WebElement element;

    //default constructor explicitly defined - required for reflection calls
    public KuoriWebElement() {
        this.driver = new KuoriWebDriver();
    }

    //default constructor explicitly defined - required for reflection calls
    public KuoriWebElement(WebDriver driver) {
        this.driver = new KuoriWebDriver(driver);
    }

    public <T extends WebElementContainer> T get(Class<T> clazz, @Nullable SearchContext parent) {
        return driver.get(clazz, parent);
    }

    public <T extends WebElementContainer> T get(Class<T> clazz, @Nullable SearchContext parent, int number) {
        return driver.get(clazz, parent, number);
    }

    public <T extends WebElementContainer> T get(Class<T> clazz, @Nullable SearchContext parent, String name) {
        return driver.get(clazz, parent, name);
    }

    public <T extends WebElementContainer> T get(Class<T> clazz, @Nullable SearchContext parent, String name, int number) {
        return driver.get(clazz, parent, name, number);
    }

    public <T extends WebElementContainer> List<T> getAll(Class<T> clazz, @Nullable SearchContext parent) {
        return driver.getAll(clazz, parent);
    }

    public KuoriWebDriver getDriver() {
        return driver;
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) element).getCoordinates();
    }

    @Override
    public void setWebElement(WebElement element) {
        this.element = element;
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
    public String getAttribute(String attribute) {
        return element.getAttribute(attribute);
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
    public String getCssValue(String cssLocator) {
        return element.getCssValue(cssLocator);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return element.getScreenshotAs(outputType);
    }

    @Override
    public WebElement getWrappedElement() {
        return this.element;
    }
}
