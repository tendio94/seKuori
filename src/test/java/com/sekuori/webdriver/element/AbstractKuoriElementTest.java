package com.sekuori.webdriver.element;

import com.sekuori.webdriver.WebDriverForTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractKuoriElementTest {
    protected static final AfterLoginSeleniumAction DEFAULT_SLEEP_ACTION =
            () -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            };

    /**
     * Method used to check if web-elements are the same by comparing their size, position and text
     *
     * @param first Ethalon web-element
     * @param rest  tested web-elements that are found via different search strategies
     */
    protected static void assertWebElementsAreSame(WebElement first, WebElement... rest) {
        for (WebElement element : rest) {
            Assert.assertEquals(first.getTagName(), element.getTagName());
            Assert.assertEquals(first.getSize(), element.getSize());
            Assert.assertEquals(first.getLocation(), element.getLocation());
            Assert.assertEquals(first.getRect(), element.getRect());
        }
    }

    protected static void performInitialSetup(final String baseUrl, AfterLoginSeleniumAction... actions) {
        WebDriverForTest.getDriver().get(baseUrl);
        for (AfterLoginSeleniumAction action : actions) {
            action.performAction();
        }
    }

    protected static SearchContext getRootContext(WebDriver driver) {
        return driver.findElement(By.xpath(".//html"));
    }

    @Test(expected = WebElementNotFoundException.class)
    public abstract void testElementIsNotFound();

    @Test
    public abstract void testDifferentSearchStrategiesFindSameElement();

    @Test
    public abstract void testGetAllElements();

    @Test
    public abstract void testElementIsFoundByName();

    @Test
    public abstract void testElementIsFoundByNumber();

    @Test
    public abstract void testElementIsFoundByNameAndNumber();

    @Test
    public abstract void testElementIsFoundByParentContext();

    @FunctionalInterface
    public interface AfterLoginSeleniumAction {
        void performAction();
    }

}
