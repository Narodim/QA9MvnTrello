package org.example.tests;

import org.example.pages.HomePageHelper;
import org.example.pages.LoginPageHelper;
import org.example.pages.MemberMenuHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class MemberMenuActivityTabTest extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    MemberMenuHelper memberMenu;

    @BeforeMethod
    public void InitTest(){
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
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
    public void lastActivityInActivityTab(){
        Assert.assertEquals(memberMenu.activityTab(), memberMenu.accName + " " + "added list" + " " + listTitle + " " + "to" + " " + boardTitle);
    }
}
