package com.sekuori.webdriver.element;

import com.sun.istack.internal.Nullable;
import org.openqa.selenium.SearchContext;

/**
 * Narrowing selenium`s {@link org.openqa.selenium.SearchContext}
 * by providing methods to find elements by XPaths only
 */
interface NarrowedSearchContext extends SearchContext {
    <T extends SekuoriWebElement> T get(T clazz, @Nullable NarrowedSearchContext context);
}
