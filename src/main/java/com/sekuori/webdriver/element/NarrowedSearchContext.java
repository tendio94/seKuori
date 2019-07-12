package com.sekuori.webdriver.element;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.SearchContext;

import java.util.List;

/**
 * Narrowing selenium`s {@link org.openqa.selenium.SearchContext}
 * by providing methods to find elements by names and numbers
 */
interface NarrowedSearchContext extends SearchContext {
    <T extends KuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent);

    <T extends KuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, int number);

    <T extends KuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, String name);

    <T extends KuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, String name, int number);

    <T extends KuoriWebElement> List<T> getAll(Class<T> clazz, @Nullable SearchContext parent);

    <T extends KuoriWebElement> List<T> getAll(Class<T> clazz, @Nullable SearchContext parent, String name);
}
