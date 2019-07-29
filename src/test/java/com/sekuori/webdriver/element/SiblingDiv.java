package com.sekuori.webdriver.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SiblingDiv extends KuoriWebElement<SiblingDiv> {
    public WebElement getFollowingSibling() {
        return findElement(By.xpath(".//following-sibling::*"));
    }

    public WebElement getPrecedingSibling() {
        return findElement(By.xpath(".//preceding-sibling::*"));
    }

    public List<SiblingDiv> getNestedChildren() {
        return getAll(SiblingDiv.class, this);
    }
}
