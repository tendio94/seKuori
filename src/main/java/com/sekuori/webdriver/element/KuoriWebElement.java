package com.sekuori.webdriver.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class KuoriWebElement extends ProxyWebElement {
    private static final Logger LOGGER = LogManager.getLogger();

    //default constructor implicitly defined - required for reflection calls
    public KuoriWebElement() {
    }

    @Override
    public <T extends KuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent) {
        WebElementBuilder<T> builder = new WebElementBuilder<>(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).getElement();
        return Constructor.construct(clazz, driver, element);
    }

    @Override
    public <T extends KuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, int number) {
        WebElementBuilder<T> builder = new WebElementBuilder<>(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNumberLocator(number).getElement();
        return Constructor.construct(clazz, driver, element);
    }

    @Override
    public <T extends KuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, String name) {
        WebElementBuilder<T> builder = new WebElementBuilder<>(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNameLocator(name).getElement();
        return Constructor.construct(clazz, driver, element);
    }

    @Override
    public <T extends KuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, String name, int number) {
        WebElementBuilder<T> builder = new WebElementBuilder<>(driver);
        WebElement element = builder.ofClass(clazz)
                .withContext(parent).withNumberLocator(number).withNameLocator(name).getElement();
        return Constructor.construct(clazz, driver, element);
    }

    @Override
    public <T extends KuoriWebElement> List<T> getAll(Class<T> clazz, @Nullable SearchContext parent) {
        WebElementBuilder<T> builder = new WebElementBuilder<>(driver);
        List<WebElement> elements = builder.ofClass(clazz)
                .withContext(parent).getElements();
        return (elements != null) ? elements.stream()
                .map(e -> Constructor.construct(clazz, driver, element))
                .collect(Collectors.toList()) : null;
    }

    private static class Constructor {
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
                String message = String.format("Couldn`t instantiate web element of %s. Cause : %s", clazz, e.getMessage());
                throw new RuntimeException(message);
            }
        }
    }

    private class WebElementBuilder<T extends WebElement> {
        int numberLocator;
        private WebDriver driver;
        private SearchContext context;
        private Class<T> clazz;
        private String nameLocator;

        private WebElementBuilder(WebDriver driver) {
            this.driver = driver;
        }

        private WebElementBuilder<T> ofClass(Class<T> clazz) {
            this.clazz = clazz;
            return this;
        }

        private WebElementBuilder<T> withContext(@Nullable SearchContext context) {
            this.context = context;
            return this;
        }

        private WebElementBuilder<T> withNameLocator(@Nullable String nameLocator) {
            this.nameLocator = nameLocator;
            return this;
        }

        private WebElementBuilder<T> withNumberLocator(int numberLocator) {
            this.numberLocator = numberLocator;
            return this;
        }

        private List<WebElement> getElements() {
            prepareSearchContext();
            String xpath = resolveXpathLocator(clazz, nameLocator);
            return findWebElements(xpath);
        }

        private WebElement getElement() {
            prepareSearchContext();
            String xpath = resolveXpathLocator(clazz, nameLocator);
            return findWebElementByXpathAndNumber(xpath);
        }

        private List<WebElement> findWebElements(String xpath) {
            try {
                return context.findElements(By.xpath(xpath));
            } catch (NoSuchElementException e) {
                throw new WebElementNotFoundException(e.getLocalizedMessage());
            }
        }

        private WebElement findWebElementByXpathAndNumber(String xpath) {
            final int indexNumber = numberLocator - 1;
            try {
                return ((indexNumber >= 0) ? context.findElements(By.xpath(xpath)).get(indexNumber)
                        : context.findElement(By.xpath(xpath)));
            } catch (NoSuchElementException e) {
                throw new WebElementNotFoundException(e.getLocalizedMessage());
            }
        }

        private String resolveXpathLocator(Class clazz, @Nullable String name) {
            String xpath = getFindContainerXpath(clazz);
            if (name != null) {
                xpath = String.format(getFindByNameXpath(clazz), name);
            }
            return xpath;
        }

        private void prepareSearchContext() {
            checkSearchContext();
            setWebDriverFromContext();
            SearchContext definedContext = (context != null) ? context : driver;

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Defined and set search context for the element {}: {}", this, definedContext);
            }
        }

        private void checkSearchContext() {
            if ((this.driver == null) && (context == null)) {
                LOGGER.fatal("Couldn`t resolve search context for the web element {}: both " +
                        "element`s existing context and provided to method get() are null", this);
                throw new SearchContextNotSetException();
            }
        }

        private void setWebDriverFromContext() {
            if (context == null) {
                return;
            }

            if (context instanceof WebDriver) {
                this.driver = (WebDriver) context;
            } else if (context instanceof IKuoriWebElement) {
                this.driver = ((IKuoriWebElement) context).getWebDriver();
            } else {
                LOGGER.warn("Couldn`t resolve web driver instance from the parent search context {}", context);
            }
        }
    }
}