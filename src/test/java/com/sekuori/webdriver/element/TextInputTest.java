package com.sekuori.webdriver.element;

import com.sekuori.webdriver.KuoriWebDriver;
import com.sekuori.webdriver.Urls;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.SearchContext;

import java.util.Collection;

import static com.sekuori.webdriver.WebDriverForTest.getDriver;

public class TextInputTest extends AbstractKuoriElementTest {
    private static final String INPUT_LABEL = "Username";
    private static final String TEXT_INPUT_TYPE = "text";
    private static final KuoriWebDriver DRIVER = new KuoriWebDriver(getDriver());

    @BeforeClass
    public static void init() {
        performInitialSetup(Urls.LOGIN);
    }

    @Test
    @Override
    public void testElementIsFoundByName() {
        TextInput input = DRIVER.get(TextInput.class, null, INPUT_LABEL);

        Assert.assertNotNull(input.getWebElement());
    }

    @Test
    @Override
    public void testDifferentSearchStrategiesFindSameElement() {
        TextInput foundByName = DRIVER.get(TextInput.class, null, INPUT_LABEL);
        TextInput foundByNumber = DRIVER.get(TextInput.class, null, null, 1);
        TextInput foundByNameAndNumber = DRIVER.get(TextInput.class, null, INPUT_LABEL, 1);

        assertWebElementsAreSame(foundByName, foundByNumber, foundByNameAndNumber);
    }

    @Override
    public void testGetAllElements() {
        Collection<TextInput> inputs = DRIVER.getAll(TextInput.class, null);

        final int expectedSize = 1;
        final int actualSize = (inputs != null) ? inputs.size() : 0;
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Override
    @Test(expected = WebElementNotFoundException.class)
    public void testElementIsNotFound() {
        DRIVER.get(TextInput.class, null, "Idon`texist");
    }

    @Override
    public void testElementIsFoundByNumber() {
        TextInput input = DRIVER.get(TextInput.class, null, 1);

        Assert.assertEquals(TEXT_INPUT_TYPE, input.getAttribute("type"));
    }

    @Override
    public void testElementIsFoundByNameAndNumber() {
        TextInput input = DRIVER.get(TextInput.class, null, INPUT_LABEL, 1);

        Assert.assertEquals(TEXT_INPUT_TYPE, input.getAttribute("type"));
    }

    @Override
    public void testElementIsFoundByParentContext() {
        SearchContext parentContext = getRootContext(getDriver());
        TextInput input = DRIVER.get(TextInput.class, parentContext, INPUT_LABEL, 1);

        Assert.assertEquals(TEXT_INPUT_TYPE, input.getAttribute("type"));
    }
}