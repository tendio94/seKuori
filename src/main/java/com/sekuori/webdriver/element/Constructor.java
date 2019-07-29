package com.sekuori.webdriver.element;

import com.sekuori.webdriver.KuoriWebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;

public class Constructor {
    public static <T extends IKuoriWebElement> T construct(Class<T> clazz, KuoriWebDriver driver, WebElement element) {
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
