package com.sekuori.webdriver.element;

import org.openqa.selenium.NoSuchElementException;

public class WebElementNotFoundException extends NoSuchElementException {

    public WebElementNotFoundException(String reason) {
        super(reason);
    }

    public WebElementNotFoundException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
