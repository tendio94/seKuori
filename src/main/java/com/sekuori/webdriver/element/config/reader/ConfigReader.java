package com.sekuori.webdriver.element.config;

import java.io.File;

public interface ConfigReader {
    ConfigEntity readFromFile(File configFile);
}
