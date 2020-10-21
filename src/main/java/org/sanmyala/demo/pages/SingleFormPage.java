package org.sanmyala.demo.pages;

import org.sanmyala.config.TestInstance;
import org.sanmyala.constants.Identifiers;

import org.testng.Assert;

public class SingleFormPage {

    private TestInstance testInstance;

    public SingleFormPage(TestInstance testInstance){
        this.testInstance = testInstance;
    }

    /**
     * Functions to handle a single input field
     */
    public SingleFormPage singleInputField(){
        testInstance.getElementActions().setText(testInstance.getWebDriver(), testInstance.getElementActions().getBy(Identifiers.XPATH, "//input[@id='user-message']"), "User message!!!");
        testInstance.getElementActions().clickElement(testInstance.getWebDriver(), testInstance.getElementActions().getBy(Identifiers.XPATH, "//button[contains(text(),'Show Message')]"));
        String elementText = testInstance.getElementActions().getElementText(testInstance.getWebDriver(), testInstance.getElementActions().getBy(Identifiers.XPATH, "//span[@id='display']"));
        Assert.assertEquals(elementText, "User message!!!");
        return this;
    }

    /**
     * Function to handle two input fields
     */
    public SingleFormPage twoInputFields(){
        testInstance.getElementActions().setText(testInstance.getWebDriver(), testInstance.getElementActions().getBy(Identifiers.XPATH, "//input[@id='sum1']"), "2");
        testInstance.getElementActions().setText(testInstance.getWebDriver(), testInstance.getElementActions().getBy(Identifiers.XPATH, "//input[@id='sum2']"), "2");
        testInstance.getElementActions().clickElement(testInstance.getWebDriver(), testInstance.getElementActions().getBy(Identifiers.XPATH, "//button[contains(text(),'Get Total')]"));
        String elementText = testInstance.getElementActions().getElementText(testInstance.getWebDriver(), testInstance.getElementActions().getBy(Identifiers.XPATH, "//span[@id='displayvalue']"));
        Assert.assertEquals(elementText, "4");
        return this;
    }
}
