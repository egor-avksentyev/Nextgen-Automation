package com.qa.pom.pages;

import com.qa.pom.base.BaseTest;

import java.io.IOException;

public class Dashboard extends AbstractPage {

    private String wrapXpath = "//div[@class='loading-wrapper']";
    /**
     * Constructor
     *
     * @param testClass the instance of account page
     */
    public Dashboard(BaseTest testClass) {
        super(testClass);
        testClass.waitTillElementIsVisible(submitButton);

    }


    public AddnewClient addnewClientclick() throws IOException {
        testClass.waitTillElementIsVisible(loadingWrapper);
        testClass.waitTillElementNotVisible(wrapXpath);
        addClient.click();
        return new AddnewClient(testClass);
    }
}
