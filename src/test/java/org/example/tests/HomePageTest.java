package org.example.tests;

import org.example.pages.HomePageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase{
        HomePageHelper homePage;

        @BeforeMethod(alwaysRun = true)
    public void initTest(){
            homePage = PageFactory.initElements(driver, HomePageHelper.class);
            homePage.waitUntilPageIsLoaded();
        }

        @Test(groups = {"smoke","regression","system"})
    public void verifyAppTest(){
            log4j.startTestCase("verifyAppTest");
            Assert.assertTrue(homePage.isCorrectPage());
        }
}
