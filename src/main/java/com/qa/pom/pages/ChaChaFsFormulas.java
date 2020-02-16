package com.qa.pom.pages;

import com.qa.pom.base.BaseTest;

import java.io.IOException;

public class ChaChaFsFormulas extends AbstractAssessmentPage {

    public ChaChaFsFormulas(BaseTest testClass)  {
        super(testClass);
        testClass.waitTillElementIsVisible(loadingWrapper);
        testClass.waitTillElementNotVisible(wrapXpath);
    }







}