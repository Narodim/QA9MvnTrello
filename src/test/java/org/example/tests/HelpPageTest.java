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

    @BeforeMethod(alwaysRun = true)
    public void initTest(){
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        currentBoardPage = new CurrentBoardPageHelper(driver, boardTitle);
        memberMenu = PageFactory.initElements(driver, MemberMenuHelper.class);
        helpPage = PageFactory.initElements(driver, HelpPageHelper.class);
        log4j.startMethod("HelpPageTest - initTest()");
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
        log4j.endMethod("HelpPageTest - initTest()");
    }

    @Test(priority = 1,groups = {"smoke"})
    public void goToHelpPage() {
        log4j.startMethod("HelpPageTest - goToHelpPage()");
        log4j.info("go to the new page");
        helpPage.openAndCloseNewPage();
        Assert.assertEquals(textFromHelpPage, "Get help with Trello","New page wasn't reached");
        log4j.endTestCase2();
    }


    @Test(priority = 2,groups = {"smoke"})
    public void openHelpPageAndClose() {
        log4j.startMethod("HelpPageTest - openHelpPageAndClose()");
        log4j.info("go to the new page and after close it");
        helpPage.closeSecondPage();
        Assert.assertEquals(textHomePageBefore, textHomePageAfter);
        log4j.endTestCase2();
    }

    @Test(priority = 3,groups = {"smoke"})
    public void returnToHomePageByButton(){
        log4j.startMethod("HelpPageTest - returnToHomePageByButton()");
        log4j.info("go to the new page and after return to the homepage");
        helpPage.returningToTheHomepage();
        Assert.assertEquals(textHomePageBefore, textHomePageAfter);
        log4j.endTestCase2();
    }

}
