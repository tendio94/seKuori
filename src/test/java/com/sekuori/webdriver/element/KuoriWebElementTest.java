package com.sekuori.webdriver.element;

import com.sekuori.webdriver.Urls;
import org.junit.Assert;
import org.junit.Test;

import static com.sekuori.webdriver.WebDriverForTest.getDriver;

public class KuoriWebElementTest {

    @Test(expected = SearchContextNotSetException.class)
    public void testGetElementThrowsExceptionForNullSearchContext() {
        KuoriWebElement<KuoriWebElement> element = new KuoriWebElement<>();
        element.get(KuoriWebElement.class, null);
    }

    @Test
    public void testGetElementFindsWebElementByCustomConfiguration() {
        KuoriWebElement<TextInput> element = new KuoriWebElement<>();
        getDriver().get(Urls.LOGIN);

        String expectedTagName = "input";
        String actualTagName = element.get(TextInput.class, getDriver(), "Username").getTagName();
        Assert.assertEquals(expectedTagName, actualTagName);
    }

}