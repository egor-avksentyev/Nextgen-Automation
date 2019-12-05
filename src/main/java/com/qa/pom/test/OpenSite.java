package com.qa.pom.test;

import com.qa.pom.base.BaseTest;
import com.qa.pom.pages.*;
import java.io.IOException;
import org.junit.Test;

public class OpenSite extends BaseTest {

    /** Open site and log in. Verify name. Log put and check the log in page. */
    @Test
    public void testOpenSiteAndClickPythonLinkTest() throws IOException {

        // Initialize HomePage
        Home home = openSite();

        // Click on log in Link
        Login login = home.clickLoginLink();

        // Log in
        MyAccount myAccountPage = login.logIn();

        // click Dresses
        Dresses dresses = myAccountPage.clickDresses();
        // click SummerDresses
        Summerdresses Summerdresses = dresses.clickSummerDresses();
        // verify if quantity from text and quantity from content are equals
        Summerdresses.verifyName();

        // CLose site
        closeSite();
    }
}
