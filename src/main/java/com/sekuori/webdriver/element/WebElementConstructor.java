package com.sekuori.webdriver.element;

import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;

public final class WebElementConstructor {
    private WebElementConstructor() {
    }

    public static <T extends WebElementContainer> T construct(Class<T> clazz, WebElement element) {
        T instance = getNewInstanceViaDefaultConstructor(clazz);
        instance.setWebElement(element);
        return instance;
    }

    private static <T extends WebElementContainer> T getNewInstanceViaDefaultConstructor(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            String message = String.format("Couldn`t instantiate web element of %s. Cause : %s", clazz, e.getMessage());
            throw new RuntimeException(message);
        }
    }
}
