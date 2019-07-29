package com.sekuori.webdriver.element;

import com.sekuori.webdriver.KuoriWebDriver;
import com.sekuori.webdriver.Urls;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.util.Collection;

import static com.sekuori.webdriver.WebDriverForTest.getDriver;

public class SiblingDivTest extends AbstractKuoriElementTest {
    private static final String SIBLING_NAME = "1.3";
    private static final KuoriWebDriver DRIVER = new KuoriWebDriver(getDriver());

    @BeforeClass
    public static void init() {
        performInitialSetup(Urls.NESTED_ELEMENTS);
    }

    @Test
    @Override
    public void testElementIsFoundByName() {
        SiblingDiv div = DRIVER.get(SiblingDiv.class, null, SIBLING_NAME);

        Assert.assertNotNull(div.getWebElement());
    }

    @Test
    @Override
    public void testDifferentSearchStrategiesFindSameElement() {
        SiblingDiv foundByName = DRIVER.get(SiblingDiv.class, null, SIBLING_NAME);
        SiblingDiv foundByNumber = DRIVER.get(SiblingDiv.class, null, null, 5);
        SiblingDiv foundByNameAndNumber = DRIVER.get(SiblingDiv.class, null, SIBLING_NAME, 1);

        assertWebElementsAreSame(foundByName, foundByNumber, foundByNameAndNumber);
    }

    @Override
    public void testGetAllElements() {
        Collection<SiblingDiv> siblings = DRIVER.getAll(SiblingDiv.class, null);

        final int expectedSize = 152;
        final int actualSize = (siblings != null) ? siblings.size() : 0;
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Override
    @Test(expected = WebElementNotFoundException.class)
    public void testElementIsNotFound() {
        DRIVER.get(SiblingDiv.class, null, "Idon`texist");
    }

    @Override
    public void testElementIsFoundByNumber() {
        SiblingDiv div = DRIVER.get(SiblingDiv.class, null, 1);

        final String expectedText = "No siblings";
        Assert.assertEquals(expectedText, div.getText());
    }

    @Override
    public void testElementIsFoundByNameAndNumber() {
        SiblingDiv div = DRIVER.get(SiblingDiv.class, null, SIBLING_NAME, 1);

        Assert.assertEquals(SIBLING_NAME, div.getText());
    }

    @Override
    public void testElementIsFoundByParentContext() {
        SearchContext parentContext = getRootContext(getDriver());
        SiblingDiv div = DRIVER.get(SiblingDiv.class, parentContext, SIBLING_NAME, 1);

        Assert.assertEquals(SIBLING_NAME, div.getText());
    }

    @Test
    public void getFollowingSibling() {
        SiblingDiv div = DRIVER.get(SiblingDiv.class, null, "1.1");

        final String expectedText = "1.2";
        Assert.assertEquals(expectedText, div.getFollowingSibling().getText());
    }

    @Test
    public void getPrecedingSibling() {
        SiblingDiv div = DRIVER.get(SiblingDiv.class, null, SIBLING_NAME);

        final String expectedText = "1.2";
        Assert.assertEquals(expectedText, div.getPrecedingSibling().getText());
    }

    @Test
    public void getNestedChildren() {
        SiblingDiv div = DRIVER.get(SiblingDiv.class, null, "2.1");

        final int expectedChildrenCount = 146;
        final int actualChildrenCount = div.getNestedChildren().size();
        Assert.assertEquals(expectedChildrenCount, actualChildrenCount);
    }
}