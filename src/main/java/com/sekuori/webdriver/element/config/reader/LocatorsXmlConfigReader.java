package com.sekuori.webdriver.element.config.reader;

import com.sekuori.webdriver.element.config.WebElementsLocatorsConfig;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class LocatorsXmlConfigReader extends LocatorsConfigReader {

    @Override
    public WebElementsLocatorsConfig readConfig(@Nullable File config) {
        File actualConfig = (config != null) ? config : new File(DEFAULT_XML_CONFIG_PATH);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WebElementsLocatorsConfig.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (WebElementsLocatorsConfig) jaxbUnmarshaller.unmarshal(actualConfig);
        } catch (JAXBException e) {
            throw new ConfigNotReadException(e.getMessage());
        }
    }

}
