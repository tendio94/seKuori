package com.sekuori.webdriver;

import com.sekuori.webdriver.element.WebElementContainer;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.SearchContext;

import java.util.List;

interface FindsByWebElementClass {
    <T extends WebElementContainer> T get(Class<T> clazz, @Nullable SearchContext parent);

    <T extends WebElementContainer> List<T> getAll(Class<T> clazz, @Nullable SearchContext parent);
}
