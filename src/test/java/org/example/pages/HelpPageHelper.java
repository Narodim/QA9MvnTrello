package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HelpPageHelper extends PageBase {

    public static String headerHalpPage;
    public static String headerHomePage;
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
    @FindBy(css = ".mod-add")
    WebElement addButton;



    public void openHelpPage() throws InterruptedException {
        firstPageKey= driver.getWindowHandle();
        System.out.println("Page key before switch :" +firstPageKey);
        waitUntilElementIsClickable(helpTab, 10);
        helpTab.click();
        Thread.sleep(1000);
    }


    public void closeSecondPage() {
        for(String handle: driver.getWindowHandles()){
            if(!handle.equals(firstPageKey)) secondPageKey = handle;
        }
        driver.switchTo().window(secondPageKey);
        System.out.println("Page key after switch :" +secondPageKey);
        waitUntilElementIsVisible(helpPageHeader, 10);
        System.out.println("Header from the second page: " +helpPageHeader.getText());
        driver.close();
        driver.switchTo().window(firstPageKey);
        firstPageKeyAfterSwitching = driver.getWindowHandle();
        waitUntilElementIsClickable(closePopover, 10);
        closePopover.click();
        System.out.println("Page key after closing second page and returning to the homepage :" +firstPageKey);
    }



    public void returningToTheHomepage() {
        for(String handle: driver.getWindowHandles()){
            if(!handle.equals(firstPageKey)) secondPageKey = handle;
        }
        driver.switchTo().window(secondPageKey);
        System.out.println("Page key after switch :" +secondPageKey);
        waitUntilElementIsVisible(helpPageHeader, 10);
        System.out.println("Header from the second page: " +helpPageHeader.getText());
        waitUntilElementIsVisible(goToYourBoardsButton, 10);
        goToYourBoardsButton.click();
        waitUntilElementIsClickable(addButton, 10);
        secondPageKeyAfter = driver.getWindowHandle();
        System.out.println("Page key after click on 'Go to your boards' button :" +secondPageKeyAfter);
    }

}
