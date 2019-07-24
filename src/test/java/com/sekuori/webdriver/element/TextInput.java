package com.sekuori.webdriver.element;

public class Checkbox extends KuoriWebElement<Checkbox> {
    private static final String CHECKED_ATTRIBUTE = "checked";
    public boolean isChecked() {
        return getWebElement().getAttribute(CHECKED_ATTRIBUTE)
    }
}
