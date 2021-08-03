package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase{

    @FindBy(css = ".text-primary")
    WebElement logInIcon;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(id = "user")
    WebElement login;
    @FindBy(id="password")
    WebElement password;
    @FindBy(css="p.error-message")
    WebElement errorMassage;
    @FindBy(xpath = "//input[@value='Log in with Atlassian']")
    WebElement logInWithAttl;
    @FindBy(id="login-submit")
    WebElement loginSubmit;
    @FindBy(xpath = "//ul[@class='boards-page-board-section-list']")
    WebElement mainBoardsPage;
    @FindBy(xpath = "//button[@data-test-id='header-boards-menu-button']/span[2]")
    WebElement boardIconOnMainPage;
    @FindBy(xpath = "//button[@aria-label='Open member menu']")
    WebElement memberMenuButton;
    @FindBy(xpath = "//nav/ul/li[8]")
    WebElement logOut;
    @FindBy(id="logout-submit")
    WebElement submitLogout;
    @FindBy(xpath = "//div[@class='layout-centered-content']")
    WebElement logoutMessage;


    public LoginPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public void fillInEmailField(String value) {
        clickAndFielding(login, value);
    }

    public void fillInPasswordField(String value) {
        waitUntilElementIsClickable(password, 10);
        clickAndFielding(password, value);
    }

    public LoginPageHelper submitLoginNotAttl(String login, String password) {
        log4j.startMethod("LoginPageHelper - submitLoginNotAttl()");
        log4j.info("Filling field 'email': " + login);
        fillInEmailField(login);
        log4j.info("Filling field 'password': " + password);
        fillInPasswordField(password);
        log4j.info("Clicking on 'Log In' button");
        loginButton.click();
        log4j.endMethod("LoginPageHelper - submitLoginNotAttl()");
        return this;
    }

    public LoginPageHelper logInWithAttl(String login, String password) {
        log4j.startMethod("LoginPageHelper - logInWithAttl()");
        log4j.info("Filling field 'email': " + login);
        fillInEmailField(login);
        log4j.info("Wait until 'Log in with Atlassian' button is clickable");
        waitUntilElementIsClickable(logInWithAttl, 10);
        log4j.info("Clicking on 'Log in with Atlassian' button");
        logInWithAttl.click();
        log4j.info("Filling field 'password': " + password);
        fillInPasswordField(password);
        log4j.info("Clicking on 'Submit' button");
        clickSubmitButton();
        log4j.endMethod("LoginPageHelper - logInWithAttl()");
        return this;
    }

    public  LoginPageHelper waitUntilPageIsLoaded(){
        log4j.startMethod("LoginPageHelper - waitUntilPageIsLoaded()");
        log4j.info("wait until 'login' button is clickable");
        waitUntilElementIsClickable(loginButton,10);
        log4j.endMethod("LoginPageHelper - openPage()");
        return this;
    }

    public LoginPageHelper openPage() {
        log4j.startMethod("LoginPageHelper - openPage()");
        log4j.info("wait until loginIcon is clickable and click on it");
        waitUntilElementIsClickable(logInIcon,40);
        logInIcon.click();
        log4j.endMethod("LoginPageHelper - openPage()");
        return this;
    }

    public LoginPageHelper clickSubmitButton() {
        waitUntilElementIsClickable(loginSubmit, 10);
        loginSubmit.click();
        waitUntilElementIsClickable(mainBoardsPage, 10);
        return this;
    }

    public String receivingStringFromBoardsPage() {
        log4j.startMethod("LoginPageHelper - receivingStringFromBoardsPage()");
        log4j.info("Wain until 'Boards' icon on the top of the main page is clickable");
        waitUntilElementIsClickable(boardIconOnMainPage, 10);
        log4j.info("Clicking on 'Boards' icon");
        boardIconOnMainPage.click();
        waitUntilElementIsVisible(boardIconOnMainPage, 10);
        log4j.info("return 'header of boards icon' " + boardIconOnMainPage.getText());
        log4j.endMethod("LoginPageHelper - receivingStringFromBoardsPage()");
        return boardIconOnMainPage.getText();
    }

    public LoginPageHelper logoutFromAccount() {
        log4j.startMethod("LoginPageHelper - logoutFromAccount()");
        log4j.info("Clicking on 'member menu' button");
        memberMenuButton.click();
        log4j.info("Wain until 'Log out' tab is clickable");
        waitUntilElementIsClickable(logOut, 10);
        log4j.info("Clicking on 'Log out' button");
        logOut.click();
        log4j.endMethod("LoginPageHelper - logoutFromAccount()");
        return this;
    }

    public LoginPageHelper logoutSubmit() {
        waitUntilElementIsClickable(submitLogout, 10);
        submitLogout.click();
        return this;
    }

    public String receivingConfirm() {
        log4j.startMethod("LoginPageHelper - receivingConfirm()");
        log4j.info("wait until 'logout message' is visible");
        waitUntilElementIsVisible(logoutMessage, 10);
        log4j.info("return 'logout message' " + logoutMessage.getText());
        log4j.endMethod("LoginPageHelper - receivingConfirm()");
        return logoutMessage.getText();
    }

    public String invalidMessageReceiving() {
        log4j.startMethod("LoginPageHelper - invalidMessageReceiving()");
        log4j.info("wait until error message is visible");
        waitUntilElementIsVisible(errorMassage, 10);
        log4j.info("return error massage" + errorMassage.getText());
        log4j.endMethod("LoginPageHelper - invalidMessageReceiving()");
        return errorMassage.getText();
    }
}
