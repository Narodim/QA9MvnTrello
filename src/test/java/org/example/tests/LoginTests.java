package org.example.tests;


import org.example.pages.HomePageHelper;
import org.example.pages.LoginPageHelper;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;

    @BeforeMethod
    public void initTests(){
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage
                 .openPage()
                 .waitUntilPageIsLoaded();
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "negativeLoginWithoutRandom")
    public void negativeLoginParam(String login, String password, String errorMassage) {

        loginPage.submitLoginNotAttl(login, password);
        Assert.assertEquals(loginPage.invalidMessageReceiving(), errorMassage);
        Assert.assertEquals(loginPage.invalidMessageReceiving(), errorMassage);
        Assert.assertEquals(loginPage.invalidMessageReceiving(), errorMassage);
        System.out.println(errorMassage);
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "negativeLoginWithRandom")
    public void negativeLoginParamRandom(String login, String password) throws InterruptedException {

        loginPage.submitLoginNotAttl(login, password);
        Thread.sleep(1000);
        System.out.println(login);


    }


    @Test(dataProviderClass = DataProviders.class,dataProvider = "loginPositive")
    public void positiveLoginParam(String login, String password) {

        loginPage.logInWithAttl(login, password);
        Assert.assertEquals(loginPage.receivingStringFromBoardsPage(), "Boards");
        loginPage
                .logoutFromAccount()
                .logoutSubmit();
        Assert.assertEquals(loginPage.receivingConfirm(), "Thanks for using Trello." +
                "\nYou’re all logged out. So now what?","Troubles with assert");
    }
}

