package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.example.tests.TestBase.boardTitle;
import static org.example.tests.TestBase.listTitle;

public class MemberMenuHelper extends PageBase {
    public String accName;

    public MemberMenuHelper(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//button[@aria-label='Open member menu']")
    WebElement openMemberMenu;
    @FindBy(xpath = "//*[@data-test-id='header-member-menu-popover']")
    WebElement memberMenuPopover;
    @FindBy(xpath = "//nav/ul/li[2]")
    WebElement profileAndVisibilityTab;
    @FindBy(xpath = "//*[@class='_3_9rx_DdTAFtig']")
    WebElement accountName;
    @FindBy(xpath = "//nav/ul/li[3]")
    WebElement activityTab;
    @FindBy(css = ".list-actions")
    WebElement actionLog;
//    @FindBy(xpath = "//*[@class='phenom-desc'][contains(.,'Testovskiy')][contains(.,'added list')]")
//    WebElement actionInLog;

    public MemberMenuHelper openMenuMember() {
        log4j.startMethod("MemberMenuHelper - openMenuMember()");
        log4j.info("wait until 'member menu' button will be clickable");
        waitUntilElementIsClickable(openMemberMenu, 10);
        log4j.info("click on menu");
        openMemberMenu.click();
        log4j.endMethod("MemberMenuHelper - openMenuMember()");
        return this;
    }


    public MemberMenuHelper waitUntilPageIsLoaded() {
        log4j.startMethod("MemberMenuHelper - waitUntilPageIsLoaded()");
        log4j.info("wait until 'member menu' frame will be visible");
        waitUntilElementIsVisible(memberMenuPopover, 10);
        log4j.endMethod("MemberMenuHelper - waitUntilPageIsLoaded()");
        return this;
    }

    public String profileAndVisibilityTab() {
        log4j.startMethod("MemberMenuHelper - profileAndVisibilityTab()");
        log4j.info("wait until 'profile and visibility' tab will be visible");
        log4j.endMethod("MemberMenuHelper - profileAndVisibilityTab()");
        return profileAndVisibilityTab.getText();
    }


    public String activityTab() {
        log4j.startMethod("MemberMenuHelper - activityTab()");
        accName = accountName.getText();
        log4j.info("click on 'activity' tab");
        activityTab.click();
        log4j.info("wait until 'action log' will be visible");
        waitUntilElementIsVisible(actionLog, 10);
        WebElement actionInLog = driver.findElement(By.xpath(
                "//*[@class='phenom-desc'][contains(.,'"+accName+"')][contains(.,'added list')]" +
                        "[contains(.,'"+listTitle+"')][contains(.,'"+boardTitle+"')]"));
        log4j.endMethod("MemberMenuHelper - activityTab()");
        return actionInLog.getText();
    }
}
