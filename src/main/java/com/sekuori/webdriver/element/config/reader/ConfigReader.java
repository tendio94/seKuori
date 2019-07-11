package com.sekuori.webdriver.element.config.reader;

import com.sekuori.webdriver.element.config.model.ConfigEntity;

import java.io.File;

public interface ConfigReader {
    ConfigEntity readFromFile(File configFile);
}
