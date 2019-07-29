package com.sekuori.webdriver.element;

import com.sekuori.webdriver.KuoriWebDriver;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.SearchContext;

import java.util.List;

public class KuoriWebElement extends IKuoriWebElementImpl {
    //default constructor implicitly defined - required for reflection calls
    public KuoriWebElement() {
        this.driver = new KuoriWebDriver(driver);
    }

    public KuoriWebElement(KuoriWebDriver driver) {
        this.driver = driver;
    }

    public <T extends IKuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent) {
        return driver.get(clazz, parent);
    }

    public <T extends IKuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, int number) {
        return driver.get(clazz, parent, number);
    }

    public <T extends IKuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, String name) {
        return driver.get(clazz, parent, name);
    }

    public <T extends IKuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, String name, int number) {
        return driver.get(clazz, parent, name, number);
    }

    public <T extends IKuoriWebElement> List<T> getAll(Class<T> clazz, @Nullable SearchContext parent) {
        return driver.getAll(clazz, parent);
    }
}