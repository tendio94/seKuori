package com.sekuori.webdriver.element;

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
    private static final NarrowedSearchContext<TextInput> CONTEXT = new KuoriWebElement<>(getDriver());

    @BeforeClass
    public static void init() {
        performInitialSetup(Urls.LOGIN);
    }

    @Test
    @Override
    public void testElementIsFoundByName() {
        TextInput input = CONTEXT.get(TextInput.class, null, INPUT_LABEL);

        Assert.assertNotNull(input.getWebElement());
    }

    @Test
    @Override
    public void testDifferentSearchStrategiesFindSameElement() {
        TextInput foundByName = CONTEXT.get(TextInput.class, null, INPUT_LABEL);
        TextInput foundByNumber = CONTEXT.get(TextInput.class, null, null, 1);
        TextInput foundByNameAndNumber = CONTEXT.get(TextInput.class, null, INPUT_LABEL, 1);

        assertWebElementsAreSame(foundByName, foundByNumber, foundByNameAndNumber);
    }

    @Override
    public void testGetAllElements() {
        Collection<TextInput> inputs = CONTEXT.getAll(TextInput.class, null);

        final int expectedSize = 1;
        Assert.assertEquals(expectedSize, inputs.size());
    }

    @Override
    @Test(expected = WebElementNotFoundException.class)
    public void testElementIsNotFound() {
        CONTEXT.get(TextInput.class, null, "Idon`texist");
    }

    @Override
    public void testElementIsFoundByNumber() {
        TextInput input = CONTEXT.get(TextInput.class, null, 1);

        Assert.assertEquals(TEXT_INPUT_TYPE, input.getAttribute("type"));
    }

    @Override
    public void testElementIsFoundByNameAndNumber() {
        TextInput input = CONTEXT.get(TextInput.class, null, INPUT_LABEL, 1);

        Assert.assertEquals(TEXT_INPUT_TYPE, input.getAttribute("type"));
    }

    @Override
    public void testElementIsFoundByParentContext() {
        SearchContext parentContext = getRootContext(getDriver());
        TextInput input = CONTEXT.get(TextInput.class, parentContext, INPUT_LABEL, 1);

        Assert.assertEquals(TEXT_INPUT_TYPE, input.getAttribute("type"));
    }
}