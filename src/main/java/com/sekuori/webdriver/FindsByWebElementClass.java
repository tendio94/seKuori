package com.sekuori.webdriver;

import com.sekuori.webdriver.element.IKuoriWebElement;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.SearchContext;

import java.util.List;

interface FindsByWebElementClass {
    <T extends IKuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent);

    <T extends IKuoriWebElement> List<T> getAll(Class<T> clazz, @Nullable SearchContext parent);
}
