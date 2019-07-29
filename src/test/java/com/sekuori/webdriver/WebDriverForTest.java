package com.sekuori.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverForTest {
    private static final String CHROME_DRIVER_PATH = "./drivers/chromedriver.exe";
    private static WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
    }

    public static boolean isQuit() {
        return ((RemoteWebDriver) driver).getSessionId() == null;
    }

    public static void destroy() {
        if (driver != null) {
            if (!isQuit()) {
                driver.quit();
            }
        }
        driver = null;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }

}
