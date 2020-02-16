package com.qa.pom.pages;

import com.qa.pom.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public abstract class AbstractAssessmentPage extends  AbstractPage {


    /** Constructor */
    AbstractAssessmentPage(BaseTest testClass)  {
        super(testClass);

    }

public void goToNewSection (String nameOfSection) {

    testClass.findElementAndClick("//div[@id='assessment-tab']//div[@type='button' and text()='"+ nameOfSection +"']");
    testClass.waitTillElementIsVisible(loadingWrapper);
    testClass.waitTillElementNotVisible(wrapXpath);

}




}
