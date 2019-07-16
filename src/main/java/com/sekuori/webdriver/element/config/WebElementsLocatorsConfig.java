package com.sekuori.webdriver.element.config;

import com.sekuori.webdriver.element.config.model.ConfigEntity;
import com.sekuori.webdriver.element.config.model.Locators;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "elements")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebElementsLocatorsConfig implements ConfigEntity {
    @XmlElement(name = "element")
    private List<Locators> locators;

    public List<Locators> getLocators() {
        return locators;
    }

    public void setLocators(List<Locators> locators) {
        this.locators = locators;
    }
}
