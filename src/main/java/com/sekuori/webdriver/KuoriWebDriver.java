package com.sekuori.webdriver;

import com.sekuori.webdriver.element.Constructor;
import com.sekuori.webdriver.element.IKuoriWebElement;
import com.sekuori.webdriver.element.WebElementBuilder;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.stream.Collectors;

public final class KuoriWebDriver extends RemoteWebDriver
        implements FindsByVisibleLabel, FindsByNumber, FindsByWebElementClass, FindsByVisibleLabelAndNumber {
    private WebDriver driver;

    public KuoriWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public <T extends IKuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent) {
        WebElementBuilder builder = new WebElementBuilder(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).getElement();
        return Constructor.construct(clazz, this, element);
    }

    @Override
    public <T extends IKuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, int number) {
        WebElementBuilder builder = new WebElementBuilder(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNumberLocator(number).getElement();
        return Constructor.construct(clazz, this, element);
    }

    @Override
    public <T extends IKuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, String name) {
        WebElementBuilder builder = new WebElementBuilder(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNameLocator(name).getElement();
        return Constructor.construct(clazz, this, element);
    }

    @Override
    public <T extends IKuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, String name, int number) {
        WebElementBuilder builder = new WebElementBuilder(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNumberLocator(number).withNameLocator(name).getElement();
        return Constructor.construct(clazz, this, element);
    }

    @Override
    public <T extends IKuoriWebElement> List<T> getAll(Class<T> clazz, @Nullable SearchContext parent) {
        WebElementBuilder builder = new WebElementBuilder(driver);
        List<WebElement> elements = builder.ofClass(clazz)
                .withContext(parent).getElements();
        return (elements != null) ? elements.stream()
                .map(e -> Constructor.construct(clazz, this, e))
                .collect(Collectors.toList()) :null;
    }
}
