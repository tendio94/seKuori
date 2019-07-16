package com.sekuori.webdriver.element;

import com.sekuori.webdriver.element.config.model.LocatorNotFoundException;
import com.sekuori.webdriver.element.config.model.Locators;
import com.sekuori.webdriver.element.config.model.WebElementsLocatorsConfig;
import com.sekuori.webdriver.element.config.reader.LocatorsXmlConfigReader;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

//TODO: implement provider-interface to be able to assign it for each
//TODO: particular custom implementation of KuoriWebElement successors
final class WebElementsXmlConfigProvider {
    private static final String CLASS_STRING = "class ";
    //temporary static as it can`t changed in runtime and is initialized once
    private static final LocatorsXmlConfigReader READER = new LocatorsXmlConfigReader();
    private static final WebElementsLocatorsConfig ELEMENTS_CONFIG = READER.readConfig(null);

    String getFindContainerXpath(Class clazz) {
        return (getLocatorsForClass(clazz) != null) ?
                getLocatorsForClass(clazz).getFindContainerXpath() : Strings.EMPTY;
    }

    String getFindByNameXpath(Class clazz) {
        return (getLocatorsForClass(clazz) != null) ?
                getLocatorsForClass(clazz).getFindByNameXpath() : Strings.EMPTY;
    }

    Locators getLocatorsForClass(Class clazz) {
        final List<Locators> locators = ELEMENTS_CONFIG.getLocators();
        final String message = String.format("Locators config for element %s isn`t found", clazz.toGenericString());
        RuntimeException exception = new LocatorNotFoundException(message);

        if (locators == null) {
            throw exception;
        }

        final String className = clazz.toString().replace(CLASS_STRING, "");
        return locators.stream()
                .filter(l -> className.equals(l.getClassName()))
                .findFirst().orElseThrow(() -> exception);
    }

}
