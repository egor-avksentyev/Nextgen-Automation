package com.qa.pom.pages;

import com.qa.pom.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class ChaChaFsFormulas extends AbstractAssessmentPage {

    @FindBy(xpath = "//div[@varname='FS']")
    private WebElement waitForElemUpload;


    public ChaChaFsFormulas(BaseTest testClass)  {
        super(testClass);
        testClass.waitTillElementIsVisible(loadingWrapper);
        testClass.waitTillElementNotVisible(wrapXpath);
        testClass.waitTillElementIsVisible(waitForElemUpload);
    }

public void TestFillIn () {

    clickOnRadioButton("FS","0");
    fillInTextField("iA18b","test");
    fillInDropdown("iA18a","0");
    goToNewSection("B. HISTORY");

    }





}