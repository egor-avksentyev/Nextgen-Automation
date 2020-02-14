package com.qa.pom.pages;
import com.qa.pom.base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.IOException;
public class Pathways extends AbstractPage {

    @FindBy(xpath = "//a[contains(@class,'new-assessment')]")
    private WebElement startNewAssessment;

    @FindBy(xpath = "//div[contains(@class,'modal-body')]")
    private WebElement waitChooseAssessmentTypeWindow;

    @FindBy(xpath = "//input[contains(@value,'INTERRAI_CHA_FS_MH')]/parent::div/parent::td/parent::tr")
    private WebElement InterRaiChaChafsChamh;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement clickSubmit;

    @FindBy(xpath = "//ul[contains(@data-assessment-type,'interRAI CHA (CHA-FS, CHA-MH)')]//div[contains(@class,'in_progress')]")
    private WebElement goToTheCHAAsessmentType;


    public Pathways(BaseTest testClass) {
        super(testClass);
        testClass.waitTillElementIsVisible(startNewAssessment);

    }

    public ChaChaFs chooseInterRaiChaChafsChamh()  {

        startNewAssessment.click();
        testClass.waitTillElementIsVisible(waitChooseAssessmentTypeWindow);
        testClass.waitTillElementIsVisible(InterRaiChaChafsChamh);
        InterRaiChaChafsChamh.click();
        clickSubmit.click();
        testClass.waitTillElementIsVisible(loadingWrapper);
        testClass.waitTillElementNotVisible(wrapXpath);
        goToTheCHAAsessmentType.click();
        return new ChaChaFs(testClass);
    }









}


