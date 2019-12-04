package com.qa.pom.pages;
import com.qa.pom.base.BaseTest;


public class Summerdresses extends AbstractPage {


    public Summerdresses(BaseTest testClass) {
        super(testClass);
        testClass.waitTillElementIsVisible(summerDresses);


    }

    public Summerdresses clickSummerDresses() {
        summerDresses.click();
        return new Summerdresses(testClass);


    }



}