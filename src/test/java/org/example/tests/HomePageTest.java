package org.example.tests;


import org.example.pages.HomePageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase{
        HomePageHelper homePage;

        @BeforeMethod
    public void initTest(){
            homePage = PageFactory.initElements(driver, HomePageHelper.class);
            homePage.waitUntilPageIsLoaded();
        }

        @Test
    public void verifyAppTest(){
            Assert.assertTrue(homePage.isCorrectPage());
        }
}
