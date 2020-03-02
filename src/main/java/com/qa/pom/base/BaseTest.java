package com.qa.pom.base;

import com.qa.pom.pages.Login;
import com.qa.pom.utils.YamlFile;
import com.qa.pom.utils.YamlParser;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    public Actions actions;
    private WebDriver driver;
    private WebDriverWait wait;
    public Logger logger;
    public Properties props;
    public String testName;
    public YamlFile yamlData;
    // Logger

    // Rule
    @Rule public RunTestRules runTestRules = new RunTestRules(this);

    /** Constructor */
    public BaseTest()  {
        // Parse Yaml file
        parseYamlData();
        // Clear Directories
        clearDirectories();
        // Logger
        logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

        crossBrowserSetup("Chrome");

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 20);

        actions = new Actions(driver);

        props = new Properties();

        setEmailProperties();
    }

    public void crossBrowserSetup(String browser) {

        switch (browser) {
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                ChromeOptions options = new ChromeOptions();

                /** Collection with settings of browser */
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);

                options.setExperimentalOption(
                        "excludeSwitches", Collections.singletonList("enable-automation"));
                options.setExperimentalOption("useAutomationExtension", false);

                /** Options which are disable forgot password pop-up */
                options.setExperimentalOption("prefs", prefs);
                // Initialize path to ChromeDriver
                System.setProperty("webdriver.chrome.driver", yamlData.getChromeDriverSrc());

                // Initialize instance of ChromeDriver and add options
                driver = new ChromeDriver(options);
                break;
        }
    }
    /**
     * Clear screenshot and logs directories.
     */
    protected void clearDirectories() {
        try {
            FileUtils.cleanDirectory(new File(yamlData.getScreenshotSrcClear()));
            FileUtils.cleanDirectory(new File(yamlData.getLogSrcClear ()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Parse yaml Data
     */
    protected void parseYamlData() {
        try {
            yamlData = YamlParser.getYamlData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open site and get instance of HomePage
     *
     * @return the instance of HomePage
     */
    protected Login openSite() {
        driver.get(yamlData.getUrl());
        return new Login(this);
    }

    /** Close site and make driver quit */
    protected void closeSite() {
        driver.quit();
        sendEmail(testName);
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

    public void setEmailProperties() {
        props.put("mail.smtp.host", yamlData.getSmtpHost());
        props.put("mail.smtp.socketFactory.port", yamlData.getSmtpSocketFactoryPort ());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", yamlData.getSmtpAuth ());
        props.put("mail.smtp.port", yamlData.getSmtpPort ());
    }

    /**
     * sendEmail test method .
     * @param NameOfTest the name of current test
     */

    public void sendEmail(String NameOfTest) {
        // This will handle the complete authentication
        Session session =
                Session.getDefaultInstance(
                        props,
                        new javax.mail.Authenticator() {

                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(
                                        yamlData.getUserName(), yamlData.getPasswordEmail());
                            }
                        });
        try {
            // Create object of MimeMessage class
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(yamlData.getMessageFrom()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(yamlData.getMessageRecipients()));
            message.setSubject(yamlData.getMessageSubject());
            String messageText =
                    "" + NameOfTest + " Success on fork " + yamlData.getUrl() + "";

            // Create another object to add another content
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            String filename = yamlData.getLogSrc();

            // Create data source and pass the filename
            DataSource source = new FileDataSource(filename);

            // set the handler
            messageBodyPart2.setDataHandler(new DataHandler(source));

            // set the file
            messageBodyPart2.setFileName(filename);
            MimeBodyPart messageBodyPart3 = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            if (new File(yamlData.getScreenshotSrc()).exists()) {
                messageText =
                        ""
                                + NameOfTest
                                + " Failed on fork "
                                + yamlData.getUrl()
                                + "";
                String filename1 = yamlData.getScreenshotSrc();

                // Create data source and pass the filename
                DataSource source1 = new FileDataSource(filename1);

                // set the handler
                messageBodyPart3.setDataHandler(new DataHandler(source1));

                // set the file
                messageBodyPart3.setFileName(filename1);
                multipart.addBodyPart(messageBodyPart3);
            }
            // Create object of MimeMultipart class
            // Create object to add multimedia type content
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText(messageText);

            // add body part 2
            multipart.addBodyPart(messageBodyPart2);

            // add body part 1
            multipart.addBodyPart(messageBodyPart1);

            // set the content
            message.setContent(multipart);

            // finally send the email
            Transport.send(message);

            System.out.println("=====Email Sent=====");

        } catch (MessagingException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * Wait till element is not visible
     *
     * @param xpath waiting for the element not visible
     */
    public void waitTillElementNotVisible(String xpath) {
        wait.until(
                ExpectedConditions.not(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath))));
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

    public void findElementAndClick(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public String findElementAndGetText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public void findElementAndSendKeys(String xpath, String text) {
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
    public void sizeConditionInprogressElementPresent(
            String xpath,
            WebElement clickGear,
            WebElement wait,
            WebElement clickCancel,
            WebElement visible,
            String notvisible) {
        if (driver.findElements(By.xpath(xpath)).size() > 0) {
            clickGear.click();
            waitTillElementIsVisible(wait);
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
