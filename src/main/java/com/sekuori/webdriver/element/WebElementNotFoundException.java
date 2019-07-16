package com.sekuori.webdriver.element;

import org.openqa.selenium.NoSuchElementException;

public class WebElementNotFoundException extends NoSuchElementException {
    private static final long serialVersionUID = 7306095173277807195L;

    public WebElementNotFoundException(String reason) {
        super(reason);
    }

    public WebElementNotFoundException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
