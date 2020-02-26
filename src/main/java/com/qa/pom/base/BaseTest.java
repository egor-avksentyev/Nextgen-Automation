package com.qa.pom.base;

import com.qa.pom.pages.Login;
import com.qa.pom.utils.YamlParser;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataSource;

public class BaseTest {

    public Actions actions;
    private WebDriver driver;
    private WebDriverWait wait;
    public Logger logger;
    public Properties props;
    // Logger

    // Rule
    @Rule public RunTestRules runTestRules = new RunTestRules(this);

    /** Constructor */
    public BaseTest()  {

        // Clear Directories
        clearDirectories ();
        // Logger
        logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

        ChromeOptions options = new ChromeOptions();

        /** Collection with settings of browser*/
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption(
                "excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        /** Options which are disable forgot password pop-up*/
        options.setExperimentalOption("prefs", prefs);
        // Initialize path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        // Initialize instance of ChromeDriver and add options
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 20);

        actions = new Actions(driver);

        props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
       // sendEmail ();

    }


    protected void clearDirectories () {
        try {
            FileUtils.cleanDirectory(new File("src/main/resources/screenshots"));
            FileUtils.cleanDirectory(new File("src/main/resources/logs"));
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
        try {
            driver.get(YamlParser.getYamlData().getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Login(this);
    }

    /** Close site and make driver quit */
    protected void closeSite() {
        driver.quit();
        sendEmail ();
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


    public void sendEmail () {
        // This will handle the complete authentication
        Session session = Session.getDefaultInstance(props,

                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("eavksentyev@gmail.com", "Omega1234");
                    }
                });
        try {
            // Create object of MimeMessage class
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("eavksentyev@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("eavksentyev@acutenet.com,bkoptev@acutenet.com" ));
            message.setSubject("Testing Subject");
            String messageText = "Test Success";

            // Create another object to add another content
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            String filename = "src/main/resources/logs/app.log";

            // Create data source and pass the filename
            DataSource source = new FileDataSource(filename);

            // set the handler
            messageBodyPart2.setDataHandler(new DataHandler(source));

            // set the file
            messageBodyPart2.setFileName(filename);
            MimeBodyPart messageBodyPart3 = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
         if(new File("src/main/resources/screenshots/Error.png").exists()) {
              messageText = "Test Failed";
            String filename1 = "src/main/resources/screenshots/Error.png";

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
            // add body part 3

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
