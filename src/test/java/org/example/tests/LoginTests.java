package org.example.tests;


import org.example.pages.HomePageHelper;
import org.example.pages.LoginPageHelper;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;

    @BeforeMethod(alwaysRun = true)
    public void initTests(){
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        log4j.startMethod("LoginTests - initTest()");
        homePage.waitUntilPageIsLoaded();
        loginPage
                 .openPage()
                 .waitUntilPageIsLoaded();
        log4j.endMethod("LoginTests - initTest()");
    }

    @Test(groups = {"system","smoke"},dataProviderClass = DataProviders.class,dataProvider = "negativeLoginWithoutRandom")
    public void negativeLoginParam(String login, String password, String errorMassage) {
        log4j.startTestCase("negativeLoginParam(), parameters: login = " + " "
                + login + " " + "password = " + " " +password + " " + "error massage =" + " " + errorMassage);
        log4j.info("Enter login not Attl: login = " + " " +login+ " "+"password =" + " "+password);
        loginPage.submitLoginNotAttl(login, password);
        log4j.info("Assert: Message has to be - " +errorMassage);
        Assert.assertEquals(loginPage.invalidMessageReceiving(), errorMassage);
        log4j.endTestCase2();

    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "negativeLoginWithRandom2",groups = {"smoke"})
    public void negativeLoginParamRandom(String login, String password) {
        log4j.startTestCase("negativeLoginParamRandom(), parameters: login = " + " "
                + login + " " + "password = " + " " +password);
        log4j.info("Enter login not Attl: login = " + " " +login+ " "+"password =" + " "+password);
        loginPage.submitLoginNotAttl(login, password);
        Assert.assertEquals(loginPage.invalidMessageReceiving(), "There isn't an account for this username");
        log4j.endTestCase2();


    }


    @Test(groups = {"smoke","system"},dataProviderClass = DataProviders.class,dataProvider = "loginPositive")
    public void positiveLoginParam(String login, String password) {
        log4j.startTestCase("positiveLoginParam(), parameters: login = " + " "
                + login + " " + "password = " + " " +password);
        log4j.info("Enter login Attlasian: login = " + " " +login+ " "+"password =" + " "+password);
        loginPage.logInWithAttl(login, password);
        Assert.assertEquals(loginPage.receivingStringFromBoardsPage(), "Boards");
        loginPage
                .logoutFromAccount()
                .logoutSubmit();
        Assert.assertEquals(loginPage.receivingConfirm(), "Thanks for using Trello.","There was no logout");
        log4j.endTestCase2();
    }
}

