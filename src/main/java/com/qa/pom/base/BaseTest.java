package com.qa.pom.base;

import com.qa.pom.pages.Login;
import com.qa.pom.utils.YamlParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class BaseTest {


    private WebDriver driver;
    private WebDriverWait wait;

    // Logger

    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    // Rule
    @Rule
    public RunTestRules runTestRules = new RunTestRules(this);

    /**
     * Constructor
     */
    public BaseTest() {
        // If you want to disable infobars please use this code
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(
                "excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        // Initialize path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        // Initialize instance of ChromeDriver and add options
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 20);


    }

    /**
     * Open site and get instance of HomePage
     *
     * @return the instance of HomePage
     */
    protected Login openSite() {
        try {
            driver.get(YamlParser.getYamlData().getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Login(this);
    }

    /**
     * Close site and make driver quit
     */
    protected void closeSite() {
        driver.quit();
    }

    /**
     * Get instance of driver
     *
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Wait till element is visible
     *
     * @param element element which test is waiting to appear on the page
     */
    public void waitTillElementIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * Wait till element is not visible
     *
     * @param xpath waiting for the element not visible
     */
    public void waitTillElementNotVisible(String xpath) {
        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath))));
    }
    /**
     * Condition Terminate and login Check
     *
     * @param xpath of element returns the number of web elements
     * @param element which test is going to click
     */
    public void sizeConditionLoginCheckAndClickOnElem(String xpath, WebElement element) {
        if (driver.findElements(By.xpath(xpath)).size() > 0) {
            element.click();
        }
    }

    public void findElementAndClick (String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public String  findElementAndGetText (String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();

    }

    public void findElementAndSendKeys (String xpath, String text) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }
    public void waitTillXpathElementIsVisible(String xpathVisible) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathVisible)));
    }
    /**
     * Condition presence of inprogress assessment on pathway
     *
     * @param xpath of element returns the number of web elements
     * @param clickGear click on gear button
     * @param wait wait for pop-up menu
     * @param clickCancel click cancel button
     */
    public void sizeConditionInprogressElementPresent(String xpath, WebElement clickGear, WebElement wait, WebElement clickCancel,WebElement visible, String notvisible ) {
        if (driver.findElements(By.xpath(xpath)).size() > 0) {
            clickGear.click();
            waitTillElementIsVisible (wait);
            clickCancel.click();
            waitTillElementIsVisible(visible);
            waitTillElementNotVisible(notvisible);
        }
    }


    /**
     * Write down info message
     *
     * @param message
     */
    public void log(String message) {

        logger.info(message);
    }

    public void logWarn(String message) {

        logger.warn(message);
    }


    public void logError(String message) {

        logger.error(message);
    }
    /**
     * Write down error message
     *
     * @param error
     */
    public void error(String error) {

        logger.error(error);
    }

    /**
     * Get date and time
     *
     * @return
     */
    public String getDateTime() {

        return new SimpleDateFormat("YYYY-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
    }
}
