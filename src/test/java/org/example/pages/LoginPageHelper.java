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
        fillInEmailField(login);
        fillInPasswordField(password);
        loginButton.click();
        return this;
    }

    public LoginPageHelper logInWithAttl(String login, String password) {
        fillInEmailField(login);
        waitUntilElementIsClickable(logInWithAttl, 10);
        logInWithAttl.click();
        fillInPasswordField(password);
        clickSubmitButton();
        return this;
    }

    public  LoginPageHelper waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(loginButton,10);
        return this;
    }

    public LoginPageHelper openPage() {
        waitUntilElementIsClickable(logInIcon,40);
        logInIcon.click();
        return this;
    }

    public LoginPageHelper clickSubmitButton() {
        waitUntilElementIsClickable(loginSubmit, 10);
        loginSubmit.click();
        waitUntilElementIsClickable(mainBoardsPage, 10);
        return this;
    }

    public String receivingStringFromBoardsPage() {
        waitUntilElementIsClickable(boardIconOnMainPage, 10);
        boardIconOnMainPage.click();
        return boardIconOnMainPage.getText();
    }

    public LoginPageHelper logoutFromAccount() {
        memberMenuButton.click();
        waitUntilElementIsClickable(logOut, 10);
        logOut.click();
        return this;
    }

    public LoginPageHelper logoutSubmit() {
        waitUntilElementIsClickable(submitLogout, 10);
        submitLogout.click();
        return this;
    }

    public String receivingConfirm() {
        waitUntilElementIsVisible(logoutMessage, 10);
        return logoutMessage.getText();
    }

    public String invalidMessageReceiving() {
        waitUntilElementIsVisible(errorMassage, 10);
        return errorMassage.getText();
    }
}
