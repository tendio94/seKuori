package com.sekuori.webdriver;

import com.sekuori.webdriver.element.WebElementBuilder;
import com.sekuori.webdriver.element.WebElementConstructor;
import com.sekuori.webdriver.element.WebElementContainer;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class KuoriWebDriver extends RemoteWebDriver
        implements FindsByVisibleLabel, FindsByNumber, FindsByWebElementClass, FindsByVisibleLabelAndNumber {
    private WebDriver driver;

    // WebDriver instance can be null - it means that to find an element
    // context should be provided to get() method
    // completely equal to new KuoriWebDriver(null)
    public KuoriWebDriver() {
    }

    public KuoriWebDriver(@Nullable WebDriver driver) {
        super();
        this.driver = driver;
    }

    public WebDriver getWrappedDriver() {
        return driver;
    }

    @Override
    public <T extends WebElementContainer> T get(Class<T> clazz, @Nullable SearchContext parent) {
        WebElementBuilder builder = new WebElementBuilder(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).getElement();
        return WebElementConstructor.construct(clazz, element);
    }

    @Override
    public <T extends WebElementContainer> T get(Class<T> clazz, @Nullable SearchContext parent, int number) {
        WebElementBuilder builder = new WebElementBuilder(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNumberLocator(number).getElement();
        return WebElementConstructor.construct(clazz, element);
    }

    @Override
    public <T extends WebElementContainer> T get(Class<T> clazz, @Nullable SearchContext parent, String name) {
        WebElementBuilder builder = new WebElementBuilder(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNameLocator(name).getElement();
        return WebElementConstructor.construct(clazz, element);
    }

    @Override
    public <T extends WebElementContainer> T get(Class<T> clazz, @Nullable SearchContext parent, String name, int number) {
        WebElementBuilder builder = new WebElementBuilder(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNumberLocator(number).withNameLocator(name).getElement();
        return WebElementConstructor.construct(clazz, element);
    }

    @Override
    public <T extends WebElementContainer> List<T> getAll(Class<T> clazz, @Nullable SearchContext parent) {
        WebElementBuilder builder = new WebElementBuilder(driver);
        List<WebElement> elements = builder.ofClass(clazz)
                .withContext(parent).getElements();
        return (elements != null) ? elements.stream()
                .map(e -> WebElementConstructor.construct(clazz, e))
                .collect(Collectors.toList()) : Collections.emptyList();
    }
}
