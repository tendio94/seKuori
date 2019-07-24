package com.sekuori.webdriver.element;

import com.sekuori.webdriver.WebDriverForTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public abstract class AbstractWebElementTest {
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

    @Test
    public abstract void testElementIsFound();

    @Test(expected = WebElementNotFoundException.class)
    public abstract void testElementIsNotFound();

    @Test
    public abstract void testDifferentSearchStrategies();

    @FunctionalInterface
    public interface AfterLoginSeleniumAction {
        void performAction();
    }

}
