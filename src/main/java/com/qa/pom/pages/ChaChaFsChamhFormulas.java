package com.qa.pom.pages;

import com.qa.pom.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class ChaChaFsChamhFormulas extends AbstractAssessmentPage {

    @FindBy(xpath = "//div[@varname='FS']")
    private WebElement waitForElemUpload;

    public ChaChaFsChamhFormulas(BaseTest testClass) {
        super(testClass);
        testClass.waitTillElementIsVisible(loadingWrapper);
        testClass.waitTillElementNotVisible(wrapXpath);
        testClass.waitTillElementIsVisible(waitForElemUpload);
    }

    // -----------------------------------Scales---------------------------------------------------

    /** Method which includes all levels test of Communication Scale */
    public void communicationScaleTest() {
        communicationScale_0_Intact();
        communicationScale_1_BORDERLINE_INTACT();
        communicationScale_2_MildImpairment();
        communicationScale_3_MildOrModerateImpairment();
        communicationScale_4_ModerateImpairment();
        communicationScale_5_ModerateOrSevereImpairment();
        communicationScale_6_SevereImpairment();
        communicationScale_7_SevereOrVerySevereImpairment();
        communicationScale_8_VerySevereImpairment();
    }

    public void communicationScale_0_Intact() {
        goToNewSection("D. COMMUNICATION");
        fillInDropdown("iD1", "0");
        fillInDropdown("iD2", "0");
        checkScaleTrigger("Communication Scale (CS)", "0 out of 8", "Intact");
    }

    public void communicationScale_1_BORDERLINE_INTACT() {
        fillInDropdown("iD1", "1");
        fillInDropdown("iD2", "0");
        checkScaleTrigger("Communication Scale (CS)", "1 out of 8", "Borderline intact");
    }

    public void communicationScale_2_MildImpairment() {
        fillInDropdown("iD1", "2");
        fillInDropdown("iD2", "0");
        checkScaleTrigger("Communication Scale (CS)", "2 out of 8", "Mild impairment");
    }

    public void communicationScale_3_MildOrModerateImpairment() {
        fillInDropdown("iD1", "3");
        fillInDropdown("iD2", "0");
        checkScaleTrigger("Communication Scale (CS)", "3 out of 8", "Mild/moderate impairment");
    }

    public void communicationScale_4_ModerateImpairment() {
        fillInDropdown("iD1", "4");
        fillInDropdown("iD2", "0");
        checkScaleTrigger("Communication Scale (CS)", "4 out of 8", "Moderate impairment");
    }

    public void communicationScale_5_ModerateOrSevereImpairment() {
        fillInDropdown("iD1", "4");
        fillInDropdown("iD2", "1");
        checkScaleTrigger("Communication Scale (CS)", "5 out of 8", "Moderate/severe impairment");
    }

    public void communicationScale_6_SevereImpairment() {
        fillInDropdown("iD1", "4");
        fillInDropdown("iD2", "2");
        checkScaleTrigger("Communication Scale (CS)", "6 out of 8", "Severe impairment");
    }

    public void communicationScale_7_SevereOrVerySevereImpairment() {
        fillInDropdown("iD1", "4");
        fillInDropdown("iD2", "3");
        checkScaleTrigger(
                "Communication Scale (CS)", "7 out of 8", "Severe/very severe impairment");
    }

    public void communicationScale_8_VerySevereImpairment() {
        fillInDropdown("iD1", "4");
        fillInDropdown("iD2", "4");
        checkScaleTrigger("Communication Scale (CS)", "8 out of 8", "Very severe impairment");
    }

    public void ADLScale_ShortFormTest() {}

    public void ADLScale_ShortForm_0_Out_Of_16() {

        goToNewSection("G. FUNCTIONAL");
        fillInDropdown("iG2b", "0");
        fillInDropdown("iG2f", "0");
        checkScaleTrigger(
                "Activities of Daily Living (ADL) Scale - Short Form",
                "0 out of 16",
                "No ADL Impairment");
    }

    public void ADLScale_ShortForm_1_Out_Of_16() {

        fillInDropdown("iG2b", "2");
        fillInDropdown("iG2f", "0");
        checkScaleTrigger(
                "Activities of Daily Living (ADL) Scale - Short Form",
                "1 out of 16",
                "Higher values indicate greater difficulty in performing activities");
    }

    public void ADLScale_ShortForm_2_Out_Of_16() {

        fillInDropdown("iG2b", "2");
        fillInDropdown("iG2f", "2");
        checkScaleTrigger(
                "Activities of Daily Living (ADL) Scale - Short Form",
                "2 out of 16",
                "Higher values indicate greater difficulty in performing activities");
    }

    public void ADLScale_ShortForm_3_Out_Of_16() {

        fillInDropdown("iG2b", "3");
        fillInDropdown("iG2f", "2");
        checkScaleTrigger(
                "Activities of Daily Living (ADL) Scale - Short Form",
                "3 out of 16",
                "Higher values indicate greater difficulty in performing activities");
    }

    public void ADLScale_ShortForm_4_Out_Of_16() {

        fillInDropdown("iG2b", "4");
        fillInDropdown("iG2f", "2");
        checkScaleTrigger(
                "Activities of Daily Living (ADL) Scale - Short Form",
                "4 out of 16",
                "Higher values indicate greater difficulty in performing activities");
    }

    public void ADLScale_ShortForm_5_Out_Of_16() {

        fillInDropdown("iG2b", "4");
        fillInDropdown("iG2f", "2");
        checkScaleTrigger(
                "Activities of Daily Living (ADL) Scale - Short Form",
                "4 out of 16",
                "Higher values indicate greater difficulty in performing activities");
    }

    public void formulasTestScales() {

        communicationScaleTest();
    }

    // -----------------------------------Caps---------------------------------------------------

    public void formulasTestCaps() throws InterruptedException {
        behaviorCapTest();
        deliriumCapTest();
        cardioRespiratoryCapTest();
    }

    public void behaviorCapTest() {
        testClass.log("Start test of Behavior Cap");
        testClass.log("Small Set");
        behaviorCap_0();
        behaviorCap_1();
        behaviorCap_2();

    }

    public void behaviorCap_0() {
        clickOnRadioButton("FS", "1");
        goToSupplement("CHA-FS");
        goToNewSection("C. MOOD");
        fillInDropdown("iE3a", "0");
        fillInDropdown("iE3b", "1");
        fillInDropdown("iE3c", "0");
        fillInDropdown("iE3d", "1");
        fillInDropdown("iE3e", "0");
        fillInDropdown("iE3f", "1");
        checkCapTrigger("Behavior CAP", "0", "Not triggered");
    }

    public void behaviorCap_1() {
        fillInDropdown("iE3a", "2");
        fillInDropdown("iE3b", "1");
        fillInDropdown("iE3c", "0");
        fillInDropdown("iE3d", "1");
        fillInDropdown("iE3e", "0");
        fillInDropdown("iE3f", "1");
        checkCapTrigger("Behavior CAP", "1", "Triggered - prevent almost daily behavior");
    }

    public void behaviorCap_2() {
        fillInDropdown("iE3a", "2");
        fillInDropdown("iE3b", "3");
        fillInDropdown("iE3c", "0");
        fillInDropdown("iE3d", "1");
        fillInDropdown("iE3e", "0");
        fillInDropdown("iE3f", "1");
        checkCapTrigger("Behavior CAP", "2", "Triggered - reduce daily behavior");
    }

    public void deliriumCapTest() {
        testClass.log("Start test of Delirium Cap");
        testClass.log("Small Set");
        deliriumCap_0();
        deliriumCap_1();
    }

    public void deliriumCap_0() {
        goToNewSection("B. COGNITION");
        fillInDropdown("iC3a", "0");
        fillInDropdown("iC3b", "0");
        fillInDropdown("iC3c", "0");
        clickOnRadioButton("iC4", "0");
        checkCapTrigger("DELIRIUM CAP", "0", "Not triggered");
    }

    public void deliriumCap_1() {
        fillInDropdown("iC3a", "0");
        fillInDropdown("iC3b", "2");
        fillInDropdown("iC3c", "0");
        clickOnRadioButton("iC4", "0");
        checkCapTrigger("DELIRIUM CAP", "1", "Triggered");
    }

    public void cardioRespiratoryCapTest() throws InterruptedException {
        testClass.log("Start test of Cardio Respiratory Cap");
        testClass.log("Small Set");
        cardioRespiratoryCap_0();
        cardioRespiratoryCap_1();
    }

    public void cardioRespiratoryCap_0() throws InterruptedException {
        goToSupplement("CHA");
        goToNewSection("J. HEALTH");
        fillInDropdown("iJ2c", "0");
        Thread.sleep(1000);
        fillInDropdown("iJ2e", "0");
        Thread.sleep(1000);
        fillInDropdown("iJ3", "0");
        Thread.sleep(1000);
        checkCapTrigger("Cardiorespiratory Conditions CAP", "0", "Not triggered");
        Thread.sleep(1000);
    }

    public void cardioRespiratoryCap_1() throws InterruptedException {
        Thread.sleep(1000);
        fillInDropdown("iJ2c", "0");
        Thread.sleep(1000);
        fillInDropdown("iJ2e", "2");
        Thread.sleep(1000);
        fillInDropdown("iJ3", "0");
        Thread.sleep(1000);
        checkCapTrigger("Cardiorespiratory Conditions CAP", "1", "Triggered");
    }
}
