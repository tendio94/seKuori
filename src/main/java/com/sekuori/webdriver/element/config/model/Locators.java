package com.sekuori.webdriver.element.config.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "element")
@XmlAccessorType(XmlAccessType.FIELD)
public class Locators {
    private String className;
    private String findByNameXpath;
    private String findContainerXpath;

    public Locators() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFindByNameXpath() {
        return findByNameXpath;
    }

    public void setFindByNameXpath(String findByNameXpath) {
        this.findByNameXpath = findByNameXpath;
    }

    public String getFindContainerXpath() {
        return findContainerXpath;
    }

    public void setFindContainerXpath(String findContainerXpath) {
        this.findContainerXpath = findContainerXpath;
    }
}
