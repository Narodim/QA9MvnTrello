package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    @FindBy(xpath = "//*[@class='phenom-desc'][contains(.,'Testovskiy')][contains(.,'added list')]")
    WebElement actionInLog;

    public MemberMenuHelper openMenuMember() {
        waitUntilElementIsClickable(openMemberMenu, 10);
        openMemberMenu.click();
        return this;
       }


    public MemberMenuHelper waitUntilPageIsLoaded() {
        waitUntilElementIsVisible(memberMenuPopover, 10);
        return this;
    }

    public String profileAndVisibilityTab() {
        return profileAndVisibilityTab.getText();
    }


    public String activityTab() {
        accName = accountName.getText();
        activityTab.click();
        waitUntilElementIsVisible(actionLog, 10);
        return actionInLog.getText();
    }
}
