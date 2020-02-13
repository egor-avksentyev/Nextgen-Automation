package com.qa.pom.pages;

import com.qa.pom.base.BaseTest;
import com.qa.pom.utils.YamlParser;
import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends AbstractPage {

    @FindBy(xpath = "//input[@type='text']")
    private WebElement username;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement password;

    @FindBy(xpath = "//button[contains(text(),'Terminate and Log In')]")
    private WebElement terminateandLogin;
    /**
     * Constructor
     *
     * @param testClass the instance of login page
     */
  public  Login(BaseTest testClass) {
        super(testClass);
        testClass.waitTillElementIsVisible(loginLoad);
    }

    /**
     * Log in using email and password form configuration.yaml
     *
     * @return next page
     */
    public Dashboard logIn() throws IOException {
        username.sendKeys(YamlParser.getYamlData().getEmail());
        password.sendKeys(YamlParser.getYamlData().getPassword());
        submitButton.click();
       if( terminateandLogin.isDisplayed()){
           testClass.log("Session Terminated proceed further");
           terminateandLogin.click();
       }
       else{

       }

        return new Dashboard(testClass);
    }

};

