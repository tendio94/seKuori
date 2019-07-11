package com.sekuori.webdriver.element.config.reader;

import com.sekuori.webdriver.element.config.model.ConfigEntity;
import org.jetbrains.annotations.NonNls;

import java.io.File;

public interface ConfigReader {
    ConfigEntity readFromFile(File configFile);

    class ConfigNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 7312210880832532593L;

        public ConfigNotFoundException(@NonNls String message) {
            super(message);
        }

        public ConfigNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
