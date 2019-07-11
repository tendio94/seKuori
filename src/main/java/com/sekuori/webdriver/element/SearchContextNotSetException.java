package com.sekuori.webdriver.element;

import org.jetbrains.annotations.NonNls;

public class SearchContextNotSetException extends RuntimeException {
    private static final long serialVersionUID = -4512905395583001098L;
    private static final String DEFAULT_EXCEPTION_CAUSE = "Search context of the element provided isn`t set";

    public SearchContextNotSetException(@NonNls String message) {
        super(message);
    }

    public SearchContextNotSetException() {
        super(DEFAULT_EXCEPTION_CAUSE);
    }
}
