package com.sekuori.webdriver.element;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class KuoriWebElement<T extends IKuoriWebElement> extends ProxyWebElement<T> {
    //default constructor implicitly defined - required for reflection calls
    public KuoriWebElement() {
    }

    @Override
    public T get(Class<T> clazz, @Nullable SearchContext parent) {
        WebElementBuilder<T> builder = new WebElementBuilder<>(this, driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).getElement();
        return Constructor.construct(clazz, driver, element);
    }


    @Override
    public T get(Class<T> clazz, @Nullable SearchContext parent, int number) {
        WebElementBuilder<T> builder = new WebElementBuilder<>(this, driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNumberLocator(number).getElement();
        return Constructor.construct(clazz, driver, element);
    }

    @Override
    public T get(Class<T> clazz, @Nullable SearchContext parent, String name) {
        WebElementBuilder<T> builder = new WebElementBuilder<>(this, driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNameLocator(name).getElement();
        return Constructor.construct(clazz, driver, element);
    }

    @Override
    public T get(Class<T> clazz, @Nullable SearchContext parent, String name, int number) {
        WebElementBuilder<T> builder = new WebElementBuilder<>(this, driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNumberLocator(number).withNameLocator(name).getElement();
        return Constructor.construct(clazz, driver, element);
    }

    @Override
    public List<T> getAll(Class<T> clazz, @Nullable SearchContext parent) {
        WebElementBuilder<T> builder = new WebElementBuilder<>(this, driver);
        List<WebElement> elements = builder.ofClass(clazz)
                .withContext(parent).getElements();
        return (elements != null) ? elements.stream()
                .map(e -> Constructor.construct(clazz, driver, element))
                .collect(Collectors.toList()) : null;
    }

    private static class Constructor {
        static <T extends IKuoriWebElement> T construct(Class<T> clazz, WebDriver driver, WebElement element) {
            T instance = getNewInstanceViaDefaultConstructor(clazz);
            instance.setWebElement(element);
            instance.setWebDriver(driver);
            return instance;
        }

        private static <T extends IKuoriWebElement> T getNewInstanceViaDefaultConstructor(Class<T> clazz) {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                String message = String.format("Couldn`t instantiate web element of %s. Cause : %s", clazz, e.getMessage());
                throw new RuntimeException(message);
            }
        }
    }

}