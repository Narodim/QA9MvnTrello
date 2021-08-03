package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HelpPageHelper extends PageBase {

    public static String textHomePageBefore;
    public static String textHomePageAfter;
    public static String textFromHelpPage;
    public static String textFromHomePage;
    public static String firstPageKey;
    public static String secondPageKey;
    public static String firstPageKeyAfterSwitching;
    public static String secondPageKeyAfter;

    public HelpPageHelper(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//nav/ul/li[6]")
    WebElement helpTab;
    @FindBy(xpath = "//h1")
    WebElement helpPageHeader;
    @FindBy(xpath = "(//h3[@class='boards-page-board-section-header-name'])[2]")
    WebElement homePageBoardsListName;
    @FindBy(xpath = "//span[contains(text(),'Go to your boards')]")
    WebElement goToYourBoardsButton;
    @FindBy(xpath = "//button[@aria-label='Close popover']")
    WebElement closePopover;
    @FindBy(css = ".mod-no-sidebar")
    WebElement homePageBoardSection;




    public HelpPageHelper openHelpPage() {
        log4j.startMethod("HelpPageHelper - openHelpPage()");
        firstPageKey= driver.getWindowHandle();
        System.out.println("Page key before switch :" +firstPageKey);
        log4j.info("wait until 'help' tab will be visible");
        waitUntilElementIsClickable(helpTab, 10);
        log4j.info("click on 'help' tab");
        helpTab.click();
        log4j.info("number of open window equal 2");
        newWindowOpen(2,10);
        log4j.endMethod("HelpPageHelper - openHelpPage()");
        return this;
    }


    public HelpPageHelper openAndCloseNewPage() {
        log4j.startMethod("HelpPageHelper - openAndCloseNewPage()");
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(firstPageKey)) secondPageKey = handle;
        }
        log4j.info("switching to new window");
        driver.switchTo().window(secondPageKey);
        System.out.println("Page key after switch :" + secondPageKey);
        log4j.info("wait until header of new window will be visible : " + helpPageHeader);
        waitUntilElementIsVisible(helpPageHeader, 10);
        textFromHelpPage = helpPageHeader.getText();
        System.out.println("Header from the second page: " + helpPageHeader.getText());
        log4j.info("closing active window");
        driver.close();
        log4j.endMethod("HelpPageHelper - openAndCloseNewPage()");
        return this;
    }



    public HelpPageHelper closeSecondPage() {
        log4j.startMethod("HelpPageHelper - closeSecondPage()");
        textHomePageBefore = homePageBoardSection.getText();
        for(String handle: driver.getWindowHandles()){
            if(!handle.equals(firstPageKey)) secondPageKey = handle;
        }
        log4j.info("switching to new window");
        driver.switchTo().window(secondPageKey);
        System.out.println("Page key after switch :" +secondPageKey);
        log4j.info("wait until header of new window will be visible : " + helpPageHeader);
        waitUntilElementIsVisible(helpPageHeader, 10);
        textFromHelpPage = helpPageHeader.getText();
        System.out.println("Header from the second page: " +helpPageHeader.getText());
        log4j.info("closing active window");
        driver.close();
        log4j.info("switching to previous window");
        driver.switchTo().window(firstPageKey);
        firstPageKeyAfterSwitching = driver.getWindowHandle();
        log4j.info("wait until 'close popover of member menu' button will be clickable");
        waitUntilElementIsClickable(closePopover, 10);
        log4j.info("click on 'close popover' button");
        closePopover.click();
        textHomePageAfter = homePageBoardSection.getText();
        System.out.println("Page key after closing second page and returning to the homepage :" +firstPageKey);
        log4j.endMethod("HelpPageHelper - closeSecondPage()");
        return this;
    }

    public HelpPageHelper returningToTheHomepage() {
        log4j.startMethod("HelpPageHelper - returningToTheHomepage()");
        textHomePageBefore = homePageBoardSection.getText();
        for(String handle: driver.getWindowHandles()){
            if(!handle.equals(firstPageKey)) secondPageKey = handle;
        }
        log4j.info("switching to new window");
        driver.switchTo().window(secondPageKey);
        System.out.println("Page key after switch :" +secondPageKey);
        log4j.info("wait until 'go to your boards' button will be visible");
        waitUntilElementIsVisible(goToYourBoardsButton, 10);
        log4j.info("click on 'go to your boards' button");
        goToYourBoardsButton.click();
        log4j.info("return to previous page and wait until all boards will be visible");
        waitUntilElementIsVisible(homePageBoardSection, 10);
        textFromHomePage = homePageBoardSection.getText();
        secondPageKeyAfter = driver.getWindowHandle();
        textHomePageAfter = homePageBoardSection.getText();
        System.out.println("Page key after click on 'Go to your boards' button :" +secondPageKeyAfter);
        log4j.endMethod("HelpPageHelper - returningToTheHomepage()");
        return this;
    }

}
