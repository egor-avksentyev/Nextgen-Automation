package com.qa.pom.pages;
import com.qa.pom.base.BaseTest;
import org.openqa.selenium.By;


public class Summerdresses extends AbstractPage {


    public Summerdresses(BaseTest testClass) {
        super(testClass);
        testClass.waitTillElementIsVisible(blackColor);





    }

    public void verifyName() {

        int xpathCount = testClass.getDriver().findElements(By.xpath("//ul[contains(@class,'product_list')]/li")).size();

       System.out.println(xpathCount);


    }

}