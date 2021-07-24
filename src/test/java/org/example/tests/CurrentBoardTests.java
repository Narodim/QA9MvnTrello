package org.example.tests;

import org.example.pages.CurrentBoardPageHelper;
import org.example.pages.HomePageHelper;
import org.example.pages.LoginPageHelper;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrentBoardTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    CurrentBoardPageHelper currentBoardPage;


    @BeforeMethod
    public void initTest() {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        currentBoardPage = new CurrentBoardPageHelper(driver, boardTitle);

        homePage.waitUntilPageIsLoaded();
        loginPage
                .openPage()
                .waitUntilPageIsLoaded()
                .logInWithAttl(LOGIN, PASSWORD);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "newBoardCreating")
    public void newBoardAddParam(String boardName) throws InterruptedException {
        currentBoardPage
                        .createNewBoard(boardName)
                        .backToTheBoardsPage();
        Assert.assertEquals(
                boardTitle,currentBoardPage.receivingConfirmFromBoardsPage(),
                "Ne igraisya s kostilyami");

    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "newListCreating")
    public void newListAddParam(String listName){
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        currentBoardPage.addNewListParam(listName);
        int sizeAfter = currentBoardPage.listSizeBefore();
        Assert.assertEquals(sizeAfter, sizeBefore+1,
                "Something wrong with adding new list");
    }


    @Test(dataProviderClass = DataProviders.class, dataProvider = "newCardCreating")
    public void newCardAddParam(String cardName){
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        if(sizeBefore==0){
            currentBoardPage
                            .addNewList();
        }
        int cardsBefore = currentBoardPage.numCardsBeforeLst();
        currentBoardPage.addCardByListName(cardName);
        int cardsAfter = currentBoardPage.numCardsBeforeLst();
        Assert.assertEquals(cardsAfter, cardsBefore+1,
                "Something wrong with adding new card");
    }

    @Test
    public void listArchive(){
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        if(sizeBefore==0){
            currentBoardPage
                            .addNewList();
            sizeBefore++;
        }
        currentBoardPage.listArchiveByName();
        int sizeAfter = currentBoardPage.listSizeBefore();
        Assert.assertEquals(sizeBefore-1, sizeAfter,
                "na etom nashi polnomochiya fsyo");
    }

    @Test
    public void listCopy() {
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        if(sizeBefore==0){
            currentBoardPage
                    .addNewList();
            sizeBefore++;
        }
        currentBoardPage.copyListByName();
        int sizeAfter = currentBoardPage.listSizeBefore();
        Assert.assertEquals(sizeAfter, sizeBefore+1,
                "List wasn't copy");
    }


//    @Test
//    public void boardDeleting() {
//        currentBoardPage.chooseBoard()
//
//        waitUntilElementIsClickable(By.cssSelector("a.js-show-sidebar"), 10);
//        WebElement showSidebar = driver.findElement(By.cssSelector("a.js-show-sidebar"));
//        showSidebar.click();
//
//        waitUntilElementIsVisible(By.cssSelector(".js-pop-widget-view"), 10);
//        WebElement goBackButton = driver.findElement(By.cssSelector("a.js-pop-widget-view"));
//        goBackButton.click();
//
//        waitUntilElementIsClickable(By.xpath("//a[@class='board-menu-navigation-item-link js-open-more']"), 10);
//        WebElement selectMoreButtonFromMenu = driver.findElement(By.xpath("//a[@class='board-menu-navigation-item-link js-open-more']"));
//        selectMoreButtonFromMenu.click();
//
//        waitUntilElementIsClickable(By.cssSelector("a.js-close-board"), 10);
//        WebElement closeBoardButton = driver.findElement(By.cssSelector("a.js-close-board"));
//        closeBoardButton.click();
//
//        waitUntilElementIsClickable(By.xpath("//input[@value='Close']"), 10);
//        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Close']"));
//        submitButton.click();
//    }
}


