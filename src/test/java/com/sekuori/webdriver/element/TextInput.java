package com.sekuori.webdriver.element;

public class TextInput extends KuoriWebElement {

    public void setText(String text) {
        clear();
        sendKeys(text);
    }
}
