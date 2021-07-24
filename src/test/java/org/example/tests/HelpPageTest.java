package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.example.pages.HelpPageHelper.*;

public class HelpPageTest extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    CurrentBoardPageHelper currentBoardPage;
    MemberMenuHelper memberMenu;
    HelpPageHelper helpPage;

    @BeforeMethod
    public void initTest() throws InterruptedException {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        currentBoardPage = new CurrentBoardPageHelper(driver, boardTitle);
        memberMenu = PageFactory.initElements(driver, MemberMenuHelper.class);
        helpPage = PageFactory.initElements(driver, HelpPageHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage
                .openPage()
                .waitUntilPageIsLoaded()
                .logInWithAttl(LOGIN, PASSWORD);
        memberMenu
                .openMenuMember()
                .waitUntilPageIsLoaded();
        helpPage
                .openHelpPage();
    }

    @Test
    public void openAndCloseHelpPage() {
        helpPage.closeSecondPage();
        Assert.assertEquals(firstPageKey, firstPageKeyAfterSwitching);
    }

    @Test
    public void openHelpPageAndReturn() {
        helpPage.returningToTheHomepage();
        Assert.assertEquals(secondPageKey, secondPageKeyAfter);
    }

}
