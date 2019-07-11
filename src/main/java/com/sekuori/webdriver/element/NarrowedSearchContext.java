package com.sekuori.webdriver.element;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.SearchContext;

/**
 * Narrowing selenium`s {@link org.openqa.selenium.SearchContext}
 * by providing methods to find elements by XPaths only
 */
interface NarrowedSearchContext extends SearchContext {
    <T extends KuoriWebElement> T get(Class <? extends KuoriWebElement> clazz, @NotNull NarrowedSearchContext context);
}
