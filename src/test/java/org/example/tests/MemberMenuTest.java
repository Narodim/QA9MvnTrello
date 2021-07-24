package org.example.tests;

import org.example.pages.CurrentBoardPageHelper;
import org.example.pages.HomePageHelper;
import org.example.pages.LoginPageHelper;
import org.example.pages.MemberMenuHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MemberMenuTest extends TestBase{
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    CurrentBoardPageHelper currentBoardPage;
    MemberMenuHelper memberMenu;

    @BeforeMethod
    public void initTest() {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        currentBoardPage = new CurrentBoardPageHelper(driver, boardTitle);
        memberMenu = PageFactory.initElements(driver, MemberMenuHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage
                .openPage()
                .waitUntilPageIsLoaded()
                .logInWithAttl(LOGIN, PASSWORD);
        memberMenu
                .openMenuMember()
                .waitUntilPageIsLoaded();
    }

    @Test
    public void profileAndVisibilityTabExisting(){
        Assert.assertEquals(memberMenu.profileAndVisibilityTab(),"Profile and visibility");
    }



}
