package com.sekuori.webdriver.element.config.model;

import org.jetbrains.annotations.NonNls;

public class LocatorNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5969551030409165615L;

    public LocatorNotFoundException(@NonNls String message) {
        super(message);
    }
}
