package com.sekuori.webdriver;

import com.sekuori.webdriver.element.WebElementContainer;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.SearchContext;

interface FindsByVisibleLabel {
    <T extends WebElementContainer> T get(Class<T> clazz, @Nullable SearchContext parent, String name);
}
