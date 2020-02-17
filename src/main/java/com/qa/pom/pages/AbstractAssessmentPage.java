package com.qa.pom.pages;

import com.qa.pom.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public abstract class AbstractAssessmentPage extends  AbstractPage {

    @FindBy(xpath = "//div[@title='CAPs']")
     WebElement ClickCaps;



    /** Constructor */
    AbstractAssessmentPage(BaseTest testClass)  {
        super(testClass);

    }

public void goToNewSection (String nameOfSection) {

    testClass.findElementAndClick("//div[@id='assessment-tab']//div[@type='button' and text()='"+ nameOfSection +"']");
    testClass.waitTillElementIsVisible(loadingWrapper);
    testClass.waitTillElementNotVisible(wrapXpath);
    }

public void clickOnRadioButton (String varName , String value) {
        testClass.findElementAndClick("//div[@varname='"+varName+"']//label[@value='" + value + "']");
        testClass.log("Radiobutton: "+ varName +" Value: "+value+" filled");
    }

    public void fillInTextField (String varName , String text ) {
        testClass.findElementAndSendKeys("//div[@varname='"+ varName +"']//input",text);
        testClass.log("TextField: "+ varName +" Text: "+text+" filled");
    }

public void fillInDropdown (String varName, String numberOfElement ) {

    testClass.findElementAndClick("//div[@varname='"+varName+"']//button[@type='button']");
    testClass.waitTillXpathElementIsVisible("//div[@varname='"+varName+"']//button[@aria-expanded='true']");
    testClass.findElementAndClick("//div[@varname='"+varName+"']//li[@data-original-index='"+ numberOfElement +"']");
    testClass.waitTillElementNotVisible("//div[@varname='"+varName+"']//button[@aria-expanded='true']");

    testClass.log("Dropdown: "+ varName +" Value: " + numberOfElement + " filled");

}

public void CheckCap () {

    ClickCaps.click();

    String trigger = testClass.findElementAndGetText("//div[@class='formulas__header' and text()='Falls CAP']//following-sibling::div//span[@class='badge']");
    String value   = testClass.findElementAndGetText();


    }



public void CheckOutcome () {


    }

}




