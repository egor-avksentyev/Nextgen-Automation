package com.qa.pom.pages;

import com.qa.pom.base.BaseTest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractAssessmentPage extends AbstractPage {

    @FindBy(xpath="//div[@varname='data-table-l1']//button")
    WebElement addRow;

    @FindBy(xpath="//button[@type='button' and text() = 'Save']")
    WebElement save;

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

    public JSONArray assessmentMap;

    public boolean isFSEnabled = false;

    /** Constructor */
    AbstractAssessmentPage(BaseTest testClass) {
        super(testClass);
    }

    public void setAssessmentMap(JSONArray assessmentMap) {
        this.assessmentMap = assessmentMap;
    }

    public JSONArray getAssessmentMap() {
        return this.assessmentMap;
    }

    /**
     * click UP as many times as necessary to scroll to element
     */
    public void scrollToTop() {
        while (zIndex.getCssValue("position").equals("fixed")) {
            testClass.actions.sendKeys(Keys.PAGE_UP).perform();
            testClass.log("Scroll to top");
        }
    }

    /**
     * Go to target section if current location is not on target section
     *
     * @param nameOfSection target section
     */
    public void goToSection(String nameOfSection) {
        WebElement element =
                testClass
                        .getDriver()
                        .findElement(
                                By.xpath(
                                        "//div[@id='assessment-tab']//div[@type='button' and text()='"
                                                + nameOfSection
                                                + "']"));
        if (!element.getAttribute("class").contains("btn-primary")) {
            testClass.findElementAndClick(
                    "//div[@id='assessment-tab']//div[@type='button' and text()='"
                            + nameOfSection
                            + "']");
            testClass.waitTillElementIsVisible(loadingWrapper);
            testClass.waitTillElementNotVisible(wrapXpath);
            testClass.log("Go to " + nameOfSection);
        }
    }

    /**
     * Go to target supplement (or core ) if current location is not on target supplement
     *
     * @param nameOfSupp
     */
    public void goToSupplement(String nameOfSupp) {
        WebElement element =
                testClass
                        .getDriver()
                        .findElement(
                                By.xpath(
                                        "//div[@class='assessment-supplement-box']//a[contains(@class,'assessment-supplement') and text()='"
                                                + nameOfSupp
                                                + "']"));
        if (!element.getAttribute("class").contains("active")) {
            testClass.findElementAndClick(
                    "//div[@class='assessment-supplement-box']//a[contains(@class,'assessment-supplement') and text()='"
                            + nameOfSupp
                            + "']");
            testClass.waitTillElementIsVisible(loadingWrapper);
            testClass.waitTillElementNotVisible(wrapXpath);
            testClass.log("Go to " + nameOfSupp);
        }
    }

    /**
     * clear target element
     *
     * @param varName target element
     */
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

    /**
     * fill target radio button with value
     *
     * @param varName target varname of radio button
     * @param value value which will be populated in target radio button
     */
    public void fillRadioButton(String varName, String value) {

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

    /**
     * Fill target varname of text field
     *
     * @param varName target varname of text field
     * @param text value which will be populated in target text field
     */
    public void fillInput(String varName, String text) {

       elementClear(varName);
       String textFieldElemXpath = "//div[@varname='" + varName + "']//input";

       try {
            testClass.findElementAndSendKeys(textFieldElemXpath, text);
        } catch (Exception e) {
            scrollToTop();
            testClass.findElementAndSendKeys(textFieldElemXpath, text);
        }
        testClass.log("TextField: " + varName + " Text: " + text + " filled");
    }

    /**
     * Fill target varname of drop down
     *
     * @param varName target varname of drop down
     * @param numberOfElement value which will be populated in target drop down
     */
    public void fillDropdown(String varName, String numberOfElement) {
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

    /**
     * Choose proper method for filling element using markers in html
     *
     * @param varName varname of target element
     * @param value value which will be populated in target element
     */
    public void fillElement(String varName, String value) {
        WebElement element =
                testClass.getDriver().findElement(By.xpath("//div[@varname='" + varName + "']"));
        // Fill radiobutton
        if (element.findElements(
                By.xpath(
                        "//div[@varname='"
                                + varName
                                + "']//div[contains(@class,'checkbox')]"))
                .size()
                > 0) {
            fillRadioButton(varName, value);
            // Fill dropdown
        } else if (element.findElements(
                By.xpath(
                        "//div[@varname='"
                                + varName
                                + "']//button[@data-toggle='dropdown']"))
                .size()
                > 0) {
            fillDropdown(varName, value);
            // Fill input
        } else if (element.findElements(By.xpath("//div[@varname='" + varName + "']//input")).size()
                > 0) {
            fillInput(varName, value);
        }
    }

    /**
     * Find element with varname on assessment map JSON and proceed to it
     *
     * @param varname varname of target element
     */
    public void findAndGoToElement(String varname) {
        String assessmentName = "";
        String sectionName = "";

        outerloop:
        for (Object supplement : this.assessmentMap)
        {
            JSONObject supp = (JSONObject) supplement;
            JSONObject sections = (JSONObject) supp.get("sections");
            for (Object sectionKey : sections.keySet()) {
                JSONArray varnames = (JSONArray) sections.get(sectionKey);
                if (varnames.contains(varname)) {
                    assessmentName = supp.get("supp").toString();
                    sectionName = sectionKey.toString();
                    break outerloop;
                }
            }
        }
        goToSupplement(assessmentName);
        goToSection(sectionName);
    }


    /**
     * Find element with varname on assessment map JSON, proceed to it and fill it
     *
     * @param varname varname of target element
     * @param value value which should be populated in target element
     */
    public void findGoFillElement (String varname, String value) {
        findAndGoToElement(varname);
        fillElement(varname, value);
    }



    /**
     * Used in formulas in precondition for trigering FS = 1
     */
    public void fsTriggered() {
        if (!isFSEnabled) {
            goToSupplement("CHA");
            goToSection("A. IDENTIFICATION");
            fillElement("FS", "1");
            isFSEnabled = true;
        }
    }

    /**
     * Return method by its' name
     *
     * @param method name of method
     */
    public void callPreconditions (String method) {
        try {
            System.out.println(method);
            testClass.log("Necessary precondition/s " + method);
            AbstractAssessmentPage.this.getClass().getMethod(method).invoke(AbstractAssessmentPage.this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void fillMedicationsDataGrid() {
        findAndGoToElement("data-table-l1");
        addRow.click();
        System.out.println("test of datagrid");
        testClass.waitTillElementIsVisible(save);
        fillElement("iM1a1-iM1a12","Cold");
        fillElement("iM1b1-iM1b12","2");
        fillElement("iM1c1-iM1c12", "2");
        fillElement("iM1d1-iM1d12", "2");
        fillElement("iM1e1-iM1e12", "2");
        fillElement("iM1f1-iM1f12", "1");
        fillElement("iM1gc1-iM1gc12", "12345678");
        testClass.getDriver().findElement(By.xpath("//div[@varname='iM1a1-iM1a12']//input[@type='text']")).click();
        save.click();
    }

    public void fillMedicationsDataGrid9Times(){
        int i = 0;
        while (i < 9) {
            fillMedicationsDataGrid();
            i++;
        }
    }

    /**
     * @param formulaName name of formula which is currently calculating
     * @param formulaValue value of formula which is currently calculating
     * @throws IOException
     * @throws ParseException
     */
    public void formulaCalculation(String formulaName, String formulaValue)
            throws IOException, ParseException {
        Object object =
                testClass.jsonParser.parse(
                        new FileReader(
                                "src/main/java/com/qa/pom/formulas/" + formulaName + ".json"));
        testClass.log(formulaName + " will be triggered");
        JSONObject formulaJson = (JSONObject) object;
        JSONArray methodObj = (JSONArray) formulaJson.get("preconditions");
        if (!methodObj.isEmpty()) {
            methodObj.forEach(methodName -> callPreconditions(methodName.toString()));
        }
        JSONObject testCases = (JSONObject) formulaJson.get("testCases");
        if (formulaValue != "All") {
            JSONObject formulaTestCase = (JSONObject) testCases.get(formulaValue);
            testCases.clear();
            testCases.put(formulaValue, formulaTestCase);
        }
        testCases
                .keySet()
                .forEach(
                        key -> {
                            JSONObject testCase = (JSONObject) testCases.get(key);
                            JSONObject teststeps = (JSONObject) testCase.get("data");

                            teststeps
                                    .keySet()
                                    .forEach(
                                            varname -> {
                                                findGoFillElement(
                                                        varname.toString(),
                                                        teststeps.get(varname).toString());
                                            });
                            JSONObject dependency = (JSONObject) testCase.get("dependencies");
                            if (!dependency.isEmpty()) {
                                dependency
                                        .keySet()
                                        .forEach(
                                                dependencyName -> {
                                                    try {
                                                        formulaCalculation(
                                                                dependencyName.toString(),
                                                                dependency
                                                                        .get(dependencyName)
                                                                        .toString());
                                                    } catch (IOException | ParseException e) {
                                                        e.printStackTrace();
                                                    }
                                                });
                            }
                            if (formulaValue == "All") {
                                JSONObject expected = (JSONObject) testCase.get("expected");
                                testClass.log("Formula value should be " + expected.get("value").toString() +  " " + expected.get("description").toString());
                                if (formulaName.contains("CAP")) {
                                    checkCapTrigger(
                                            expected.get("nameOfFormula").toString(),
                                            expected.get("value").toString(),
                                            expected.get("description").toString());
                                } else {
                                    checkScaleTrigger(
                                            expected.get("nameOfFormula").toString(),
                                            expected.get("value").toString(),
                                            expected.get("description").toString());
                                }
                            }
                        });
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
