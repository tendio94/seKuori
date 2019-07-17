package com.sekuori.webdriver.element;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.SearchContext;

import java.util.List;

/**
 * Narrowing selenium`s {@link org.openqa.selenium.SearchContext}
 * by providing methods to find elements by names and numbers
 */
interface NarrowedSearchContext<T extends IKuoriWebElement> extends SearchContext {
    T get(Class<T> clazz, @Nullable SearchContext parent);

    T get(Class<T> clazz, @Nullable SearchContext parent, int number);

    T get(Class<T> clazz, @Nullable SearchContext parent, String name);

    T get(Class<T> clazz, @Nullable SearchContext parent, String name, int number);

    List<T> getAll(Class<T> clazz, @Nullable SearchContext parent);
}
