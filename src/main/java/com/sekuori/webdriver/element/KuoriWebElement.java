package com.sekuori.webdriver.element;

import com.sekuori.webdriver.element.config.model.Locators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.*;

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
        T instance = Constructor.getNewInstanceViaDefaultConstructor(clazz);
        String xpath = instance.getConfiguredLocators().getFindContainerXpath();

        try {
            WebElement element = searchContext.findElement(By.xpath(xpath));
            instance.setWebElement(element);
            instance.setWebDriver(driver);
            return instance;
        } catch (NoSuchElementException e) {
            throw new WebElementNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public Locators getConfiguredLocators() {
        return new Locators(Strings.EMPTY, Strings.EMPTY);
    }

    private SearchContext prepareSearchContext(SearchContext parent) {
        checkSearchContext(parent);
        setWebDriverFromContext(parent);
        SearchContext definedContext = (parent != null) ? parent : driver;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Defined and set search context for the element {}: {}", this, definedContext);
        }
        return definedContext;
    }

    private void setWebDriverFromContext(SearchContext parent) {
        if (parent == null) {
            return;
        }

        if (parent instanceof WebDriver) {
            this.driver = (WebDriver) parent;
        } else if (parent instanceof SKWebElement) {
            this.driver = ((SKWebElement) parent).getWebDriver();
        } else {
            LOGGER.warn("Couldn`t resolve web driver instance from the parent search context {}", parent);
        }
    }

    private void checkSearchContext(SearchContext parent) {
        if ((this.driver == null) && (parent == null)) {
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

        static <T extends KuoriWebElement> T getNewInstanceViaDefaultConstructor(Class<T> clazz) {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                String message = String.format("Couldn`t instantiate web element of %s. Cause : %s", clazz, e.getMessage());
                throw new RuntimeException(message);
            }
        }
    }

}
