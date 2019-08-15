package com.sekuori.webdriver.element;

import com.sekuori.webdriver.element.config.WebElementsXmlConfigProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;

import java.util.List;

public final class WebElementBuilder {
    private static final WebElementsXmlConfigProvider CONFIG_PROVIDER = new WebElementsXmlConfigProvider();
    private static final Logger LOGGER = LogManager.getLogger();
    private int numberLocator;
    private WebDriver driver;
    private SearchContext context;
    private Class<? extends WebElement> clazz;
    private String nameLocator;

    public WebElementBuilder(WebDriver driver) {
        this.driver = driver;
    }

    public WebElementBuilder ofClass(Class<? extends WebElement> clazz) {
        this.clazz = clazz;
        return this;
    }

    public WebElementBuilder withContext(@Nullable SearchContext context) {
        this.context = (context != null) ? context : this.driver;
        return this;
    }

    public WebElementBuilder withNameLocator(@Nullable String nameLocator) {
        this.nameLocator = nameLocator;
        return this;
    }

    public WebElementBuilder withNumberLocator(int numberLocator) {
        this.numberLocator = numberLocator;
        return this;
    }

    public List<WebElement> getElements() {
        prepareSearchContext();
        String xpath = resolveXpathLocator(clazz, nameLocator);
        return findWebElements(xpath);
    }

    public WebElement getElement() {
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
        String xpath = CONFIG_PROVIDER.getFindContainerXpath(clazz);
        if (name != null) {
            xpath = String.format(CONFIG_PROVIDER.getFindByNameXpath(clazz), name);
        }
        return xpath;
    }

    private void prepareSearchContext() {
        SearchContext definedContext = checkSearchContext();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Defined and set search context for the element {}: {}", this, definedContext);
        }
    }

    private SearchContext checkSearchContext() {
        if ((driver == null) && (context == null)) {
            LOGGER.fatal("Couldn`t resolve search context for the web element {}:" +
                    " both driver and parent contexts are null", this);
            throw new SearchContextNotSetException();
        }
        // context has higher priority than driver because
        // it explicitly narrows down searching area
        return (context != null) ? context : driver;
    }

}
