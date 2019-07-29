package com.sekuori.webdriver;

import com.sekuori.webdriver.element.IKuoriWebElement;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.SearchContext;

interface FindsByVisibleLabelAndNumber {
    <T extends IKuoriWebElement> T get(Class<T> clazz, @Nullable SearchContext parent, String name, int number);
}
