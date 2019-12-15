package com.qa.pom.test;

import com.qa.pom.base.BaseTest;
import com.qa.pom.pages.*;
import org.junit.Test;

import java.io.IOException;

public class OpenSite extends BaseTest {

    /** Open site and log in. Verify name. Log put and check the log in page. */
    @Test
    public void testOpenSiteAndClickPythonLinkTest() throws IOException {

        // Initialize HomePage
        Home home = openSite();
        log("Initialize HomePage");
        // Click on login Link
        Login login = home.clickLoginLink();
        log("Click on login Link");
        // Log in
        MyAccount myAccountPage = login.logIn();
        log("Log in");
        // click Dresses
        Dresses dresses = myAccountPage.clickDresses();
        log("click Dresses");
        // click SummerDresses
        Summerdresses Summerdresses = dresses.clickSummerDresses();
        log("click SummerDresses");
        // verify if quantity from text and quantity from content are equals
        Summerdresses.verifyName();
        log("verify if quantity from text and quantity from content are equals");
        // CLose site
        closeSite();
    }
}
