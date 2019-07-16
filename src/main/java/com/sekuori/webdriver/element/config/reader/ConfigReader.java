package com.sekuori.webdriver.element.config.reader;

import com.sekuori.webdriver.element.config.model.ConfigEntity;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public interface FileConfigReader {
    ConfigEntity readConfig(@Nullable File config);

    class ConfigNotReadException extends RuntimeException {
        private static final long serialVersionUID = 7312210880832532593L;

        public ConfigNotReadException(String message) {
            super(message);
        }

        public ConfigNotReadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
