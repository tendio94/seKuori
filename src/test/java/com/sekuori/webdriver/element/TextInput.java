package com.sekuori.webdriver.element;

public class TextInput extends KuoriWebElement<TextInput> {

    public void setText(String text) {
        clear();
        sendKeys(text);
    }
}
