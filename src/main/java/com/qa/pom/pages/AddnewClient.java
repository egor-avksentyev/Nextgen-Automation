package com.qa.pom.pages;

import com.qa.pom.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.Random;

public class AddnewClient extends AbstractPage {

    @FindBy(xpath = "//div[@id='firstName']//input")
    WebElement firstName;
    @FindBy(xpath = "//div[@id='middleName']//input")
    WebElement middleName;
    @FindBy(xpath = "//div[@id='lastName']//input")
    WebElement lastName;
    @FindBy(xpath = "//div[@id='salutation']//input")
    WebElement suffix;
    @FindBy(xpath = "//div[@id='sex']//button")
    WebElement genderDropdown;
    @FindBy(xpath = "//div[@id='dateOfBirth']//input")
    WebElement dateOfBirth;
    @FindBy(xpath = "//div[@id='hcn']//input")
    WebElement hCN;
    @FindBy(xpath = "//div[@id='department']//button")
    WebElement departmentDropdown;
    @FindBy(xpath = "//div[@id='country']//input")
    WebElement country;
    @FindBy(xpath = "//div[@id='state']//input")
    WebElement provinceorState;
    @FindBy(xpath = "//div[@id='zipcode']//input")
    WebElement postalCode;
    @FindBy(xpath = "//div[@id='sex']//ul[@aria-expanded='true']")
    WebElement dropdownGenderopenMarker;
    @FindBy(xpath = "//li[@data-original-index='0']//span[contains(text(),'Male')]")
    WebElement clickMale;
    @FindBy(xpath = "//div[@id='department']//ul[@aria-expanded='true']")
    WebElement dropdownDepartmentopenMarker;
    @FindBy(xpath = "//button[contains(text(),'Select All')]")
    WebElement clickSelectall;
    @FindBy(xpath = "//button[contains(@class,'btn-success')]")
    WebElement clickSave;

    public AddnewClient(BaseTest testClass) throws IOException {
        super(testClass);
        testClass.waitTillElementIsVisible(genderDropdown);
    }

    public Pathways FillinNewpatientInformation() {
        Random rndNum = new Random();
        firstName.sendKeys("Acutenet");
        middleName.sendKeys("Test" + rndNum.nextInt());
        lastName.sendKeys("Phase");
        suffix.sendKeys("RT");
        genderDropdown.click();
        testClass.waitTillElementIsVisible(dropdownGenderopenMarker);
        clickMale.click();
        dateOfBirth.sendKeys("11/02/1992");
        hCN.sendKeys("1435263543");
        departmentDropdown.click();
        testClass.waitTillElementIsVisible(dropdownDepartmentopenMarker);
        clickSelectall.click();
        country.sendKeys("NY");
        provinceorState.sendKeys("Test");
        postalCode.sendKeys("A0A");
        clickSave.click();

        return new Pathways(testClass);
    }

}
