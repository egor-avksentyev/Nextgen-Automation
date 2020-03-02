package com.qa.pom.pages;

import com.qa.pom.base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractAssessmentPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='assessment-supplement-box']//..")
    WebElement zIndex;

    @FindBy(xpath = "//div[@title='CAPs']")
    WebElement ClickCaps;

    @FindBy(xpath = "//div[@title='Scales']")
    WebElement ClickScales;

    @FindBy(xpath = "//div[@class='modal-content']//h4[text()='CAPs']")
    WebElement capsOpened;

    @FindBy(xpath = "//div[@class='modal-content']//h4[text()='Scales']")
    WebElement scalesOpened;

    @FindBy(xpath = "//button[@class='modal-close']")
    WebElement closeFormulasPage;

    /** Constructor */
    AbstractAssessmentPage(BaseTest testClass) {
        super(testClass);
    }

    public void scrollToTop() {
        while (zIndex.getCssValue("position").equals("fixed")) {
            testClass.actions.sendKeys(Keys.PAGE_UP).perform();
            testClass.log("Scroll to top");
        }
    }

    public void goToNewSection(String nameOfSection) {

        testClass.findElementAndClick(
                "//div[@id='assessment-tab']//div[@type='button' and text()='"
                        + nameOfSection
                        + "']");
        testClass.waitTillElementIsVisible(loadingWrapper);
        testClass.waitTillElementNotVisible(wrapXpath);
    }

    public void goToSupplement(String nameOfSupp) {
        testClass.findElementAndClick(
                "//div[@class='assessment-supplement-box']//a[@class=' assessment-supplement'and text()='"
                        + nameOfSupp
                        + "']");
        testClass.waitTillElementIsVisible(loadingWrapper);
        testClass.waitTillElementNotVisible(wrapXpath);
    }

    public void elementClear(String varName) {

        String gearIconXpath = "//div[@varname='" + varName + "']//div[@class='element-options']";
        String clearButtonXpath = "//div[@varname='" + varName + "']//a[@element-action='clear']";
        testClass.waitTillXpathElementIsVisible(
                gearIconXpath);
        testClass.findElementAndClick(
                gearIconXpath);
        testClass.waitTillXpathElementIsVisible(
                clearButtonXpath);
        testClass.findElementAndClick(
                clearButtonXpath);
    };

    public void fillInRadioButton(String varName, String value) {

        String radioButtonXpath = "//div[@varname='" + varName + "']//label[@value='" + value + "']";

        try {
            testClass.findElementAndClick(
                    radioButtonXpath);
        } catch (Exception e) {
            scrollToTop();
            testClass.findElementAndClick(
                    radioButtonXpath);
        }
        testClass.log("Radiobutton: " + varName + " Value: " + value + " filled");
    }

    public void fillInTextField(String varName, String text) {

       String textFieldElemXpath = "//div[@varname='" + varName + "']//input";

       try {
            testClass.findElementAndSendKeys(textFieldElemXpath, text);
        } catch (Exception e) {
            scrollToTop();
            testClass.findElementAndSendKeys(textFieldElemXpath, text);
        }
        testClass.log("TextField: " + varName + " Text: " + text + " filled");
    }

    public void fillInDropdown(String varName, String numberOfElement) {
       String dropdownElementXpath = "//div[@varname='" + varName + "']//button[@type='button']";
        try {
            testClass.findElementAndClick(
                    dropdownElementXpath);
        } catch (Exception e) {
            scrollToTop();
            testClass.findElementAndClick(
                    dropdownElementXpath);
        }

        String expandDropdownXpath = "//div[@varname='" + varName + "']//button[@aria-expanded='true']";

        testClass.waitTillXpathElementIsVisible(expandDropdownXpath
                );
        testClass.findElementAndClick(
                "//div[@varname='"
                        + varName
                        + "']//li[@data-original-index='"
                        + numberOfElement
                        + "']");
        testClass.waitTillElementNotVisible(
                expandDropdownXpath);

        testClass.log("Dropdown: " + varName + " Value: " + numberOfElement + " filled");
    }

    public void checkCapTrigger(
            String nameOfFormula, String expectedFormulaValue, String expectedFormulaDescription) {

        ClickCaps.click();
        testClass.waitTillElementIsVisible(capsOpened);

        String formulaValue =
                testClass.findElementAndGetText(
                        "//div[@class='formulas__header' and text()='"
                                + nameOfFormula
                                + "']//following-sibling::div//span[@class='badge']");
        String formulaDescription =
                testClass.findElementAndGetText(
                        "//div[@class='formulas__header' and text()='"
                                + nameOfFormula
                                + "']//following-sibling::div//span[contains(@class,'label label')]");

        try {
            Assert.assertEquals(
                    "Value of: " + nameOfFormula + " is not as expected",
                    expectedFormulaValue,
                    formulaValue);
            testClass.log("" + nameOfFormula + "  Value Correct");
        } catch (AssertionError e) {
            testClass.logWarn("Please check value of triggered formula");
            testClass.logError(e.getMessage() + " " + e.getCause());
        }
        try {
            Assert.assertEquals(
                    "Description of: " + nameOfFormula + " is not as expected",
                    expectedFormulaDescription,
                    formulaDescription);
            testClass.log("" + nameOfFormula + "  Description Correct");
        } catch (AssertionError e) {
            testClass.logWarn("Please check Description of triggered formula");
            testClass.logError(e.getMessage() + " " + e.getCause());
        }

        closeFormulasPage.click();
    }

    public void checkScaleTrigger(
            String nameOfFormula, String expectedFormulaValue, String expectedFormulaDescription) {

        ClickScales.click();
        testClass.waitTillElementIsVisible(scalesOpened);

        String formulaValue =
                testClass.findElementAndGetText(
                        "//div[@class='formulas__header' and text()='"
                                + nameOfFormula
                                + "']//following-sibling::div//span[@class='badge']");
        String formulaDescription =
                testClass.findElementAndGetText(
                        "//div[@class='formulas__header' and text()='"
                                + nameOfFormula
                                + "']//following-sibling::div//span[contains(@class,'label')]");

        try {
            Assert.assertEquals(
                    "Value of: " + nameOfFormula + " is not as expected",
                    expectedFormulaValue,
                    formulaValue);
            testClass.log("" + nameOfFormula + "  Value Correct");
        } catch (AssertionError e) {
            testClass.logWarn("Please check value of triggered formula");
            testClass.logError(e.getMessage() + " " + e.getCause());
        }
        try {
            Assert.assertEquals(
                    "Description of: " + nameOfFormula + " is not as expected",
                    expectedFormulaDescription,
                    formulaDescription);
            testClass.log("" + nameOfFormula + " Description Correct");
        } catch (AssertionError e) {
            testClass.logWarn("Please check Description of triggered formula");
            testClass.logError(e.getMessage() + " " + e.getCause());
        }

        closeFormulasPage.click();
    }
}
