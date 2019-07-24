package com.sekuori.webdriver.element;

import com.sekuori.junit.runners.OrderedRunner;
import com.sekuori.webdriver.Urls;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.SearchContext;

import java.util.Collection;

import static com.sekuori.webdriver.WebDriverForTest.getDriver;

public class ButtonTest extends AbstractKuoriElementTest {
    private static final String DELETE_LABEL = "Delete";
    private static final String ADD_ELEMENT_LABEL = "Add Element";
    private static final NarrowedSearchContext<Button> CONTEXT = new KuoriWebElement<>(getDriver());
    private static final AfterLoginSeleniumAction ADD_ELEMENT_ACTION = () ->
            CONTEXT.get(Button.class, null, ADD_ELEMENT_LABEL).click();

    @BeforeClass
    public static void init() {
        performInitialSetup(Urls.ELEMENTS, ADD_ELEMENT_ACTION, ADD_ELEMENT_ACTION, ADD_ELEMENT_ACTION);
    }

    //making sure this test is executed first by setting order
    @Test
    @Override
    @OrderedRunner.Order(order = 1)
    public void testGetAllElements() {
        Collection<Button> buttons = CONTEXT.getAll(Button.class, null);

        final int expectedSize = 4;
        Assert.assertEquals(expectedSize, buttons.size());
    }

    @Test
    @Override
    public void testElementIsFoundByName() {
        Button input = CONTEXT.get(Button.class, null, ADD_ELEMENT_LABEL);

        Assert.assertEquals(ADD_ELEMENT_LABEL, input.getText());
    }

    @Override
    public void testElementIsFoundByNumber() {
        Button input = CONTEXT.get(Button.class, null, 1);

        Assert.assertEquals(ADD_ELEMENT_LABEL, input.getText());
    }

    @Override
    public void testElementIsFoundByNameAndNumber() {
        Button input = CONTEXT.get(Button.class, null, ADD_ELEMENT_LABEL, 1);

        Assert.assertEquals(ADD_ELEMENT_LABEL, input.getText());
    }

    @Override
    public void testElementIsFoundByParentContext() {
        SearchContext parentContext = getRootContext(getDriver());
        Button input = CONTEXT.get(Button.class, parentContext, ADD_ELEMENT_LABEL, 1);

        Assert.assertEquals(ADD_ELEMENT_LABEL, input.getText());
    }

    @Override
    @Test(expected = WebElementNotFoundException.class)
    public void testElementIsNotFound() {
        CONTEXT.get(Button.class, null, "Idon`texist");
    }

    @Test
    @Override
    public void testDifferentSearchStrategiesFindSameElement() {
        Button foundByName = CONTEXT.get(Button.class, null, ADD_ELEMENT_LABEL);
        Button foundByNumber = CONTEXT.get(Button.class, null, null, 1);
        Button foundByNameAndNumber = CONTEXT.get(Button.class, null, ADD_ELEMENT_LABEL, 1);

        assertWebElementsAreSame(foundByName, foundByNumber, foundByNameAndNumber);
    }

}