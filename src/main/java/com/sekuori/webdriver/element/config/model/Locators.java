package com.sekuori.webdriver.element.config.model;

public class Locators {
    private String findByNameXpath;
    private String findContainerXpath;

    public Locators() {
    }

    public Locators(String findByNameXpath, String findContainerXpath) {
        this.findByNameXpath = findByNameXpath;
        this.findContainerXpath = findContainerXpath;
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
