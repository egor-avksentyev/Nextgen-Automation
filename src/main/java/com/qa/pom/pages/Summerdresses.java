package com.qa.pom.pages;

import com.qa.pom.base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;

public class Summerdresses extends AbstractPage {

    private String ELEM_COUNT = "//ul[contains(@class,'product_list')]/li";

    public Summerdresses(BaseTest testClass) {
        super(testClass);
        testClass.waitTillElementIsVisible(blackColor);
    }

    public void verifyName() {

        int xpathCount = testClass.getDriver().findElements(By.xpath(ELEM_COUNT)).size();

        System.out.println("Number of products " + xpathCount);

        int numberOfproducts = Integer.parseInt(headCount.getText().replaceAll("\\D+", ""));

        System.out.println("Number of products from header" + numberOfproducts);

        Assert.assertEquals("Text is not as expected", xpathCount, numberOfproducts);
    }
}
