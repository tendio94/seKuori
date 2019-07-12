package com.sekuori.webdriver.element;

import com.sekuori.webdriver.element.config.model.Locators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;

public class KuoriWebElement extends ProxyWebElement {
    private static final Logger LOGGER = LogManager.getLogger();

    //default constructor implicitly defined - required for reflection calls
    public KuoriWebElement() {
    }

    @Override
    public <T extends KuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent) {
        SearchContext searchContext = prepareSearchContext(parent);
        String xpath = getConfiguredLocators().getFindContainerXpath();
        WebElement element = searchContext.findElement(By.xpath(xpath));

        return Constructor.construct(clazz, driver, element);
    }

    @Override
    public Locators getConfiguredLocators() {
        return null;
    }

    private SearchContext prepareSearchContext(SearchContext parent) {
        checkSearchContext(parent);

        SearchContext definedContext = (parent != null) ? parent : driver;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Defined and set search context for the element {}: {}", this, definedContext);
        }
        return definedContext;
    }

    private void checkSearchContext(SearchContext parent) {
        if ((driver == null) && (parent == null)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.fatal("Couldn`t resolve search context for the web element {}: both " +
                        "element`s existing context and provided to method get() are null", this);
            }
            throw new SearchContextNotSetException();
        }
    }

    static class Constructor {
        static <T extends KuoriWebElement> T construct(Class<T> clazz, WebDriver driver, WebElement element) {
            T instance = getNewInstanceViaDefaultConstructor(clazz);
            instance.setWebElement(element);
            instance.setWebDriver(driver);
            return instance;
        }

        private static <T extends KuoriWebElement> T getNewInstanceViaDefaultConstructor(Class<T> clazz) {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                String message = String.format("Couldn`t instantiate web element of class %s. Cause : %s", clazz, e.getCause());
                throw new RuntimeException(message);
            }
        }
    }

}
