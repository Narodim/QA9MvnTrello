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


    @BeforeMethod(alwaysRun = true)
    public void initTest() {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        currentBoardPage = new CurrentBoardPageHelper(driver, boardTitle);
        log4j.startMethod("CurrentBoardTests - InitTest()");
        homePage.waitUntilPageIsLoaded();
        loginPage
                .openPage()
                .waitUntilPageIsLoaded()
                .logInWithAttl(LOGIN, PASSWORD);
        log4j.endMethod("CurrentBoardTests - InitTest()");
    }

    @Test(priority = 1,dataProviderClass = DataProviders.class, dataProvider = "newBoardCreating",groups = {"smoke"})
    public void newBoardAddParam(String boardName) {
        log4j.startTestCase("newBoardAddParam(), parameters : board name =" +" " + boardName);
        log4j.info("Add new board with name:" + " " +boardName);
        currentBoardPage
                        .createNewBoard(boardName)
                        .backToTheBoardsPage();
        log4j.info("Assert: message has to be: "+boardTitle);
        Assert.assertEquals(
                boardTitle,currentBoardPage.receivingConfirmFromBoardsPage(),
                "Ne igraisya s kostilyami");
        log4j.endTestCase2();

    }

    @Test(priority = 2,dataProviderClass = DataProviders.class, dataProvider = "newListCreating",groups = {"smoke"})
    public void newListAddParam(String listName){
        log4j.startTestCase("newListAddParam(), parameters : list name =" +" " + listName);
        log4j.info("Choose board with name:" + " " +boardTitle);
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        log4j.info("Add new list with name:" + " " +listName);
        currentBoardPage.addNewListParam(listName);
        int sizeAfter = currentBoardPage.listSizeBefore();
        log4j.info("Assert: number of lists has to be: "+sizeBefore+1);
        Assert.assertEquals(sizeAfter, sizeBefore+1,
                "Something wrong with adding new list");
        log4j.endTestCase2();
    }


    @Test(priority = 3,dataProviderClass = DataProviders.class, dataProvider = "newCardCreating",groups = {"smoke"})
    public void newCardAddParam(String cardName, String name){
        log4j.startTestCase("newCardAddParam(), parameters : card name =" +" " + cardName);
        log4j.info("Choose board with name:" + " " +boardTitle);
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        if(sizeBefore==0){
            currentBoardPage
                            .addNewList(name);
        }
        int cardsBefore = currentBoardPage.numCardsBeforeLst();
        log4j.info("Add new list with name:" + " " +cardName);
        currentBoardPage.addCardByListName(cardName);
        int cardsAfter = currentBoardPage.numCardsBeforeLst();
        log4j.info("Assert: number of cards has to be: "+cardsBefore+1);
        Assert.assertEquals(cardsAfter, cardsBefore+1,
                "Something wrong with adding new card");
        log4j.endTestCase2();
    }

    @Test(priority = 5,groups = {"smoke"})
    public void listArchive(){
        log4j.startTestCase("listArchive()");
        log4j.info("Choose board with name:" + " " +boardTitle);
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        if(sizeBefore==0){
            currentBoardPage
                            .addNewList(listTitle);
            sizeBefore++;
        }
        log4j.info("Archive list with name:" + " " +listTitle);
        currentBoardPage.listArchiveByName();
        int sizeAfter = currentBoardPage.listSizeBefore();
        Assert.assertEquals(sizeBefore-1, sizeAfter,
                "na etom nashi polnomochiya fsyo");
        log4j.endTestCase2();
    }

    @Test(priority = 4,groups = {"smoke"})
    public void listCopy() {
        log4j.startTestCase("listCopy()");
        log4j.info("Choose board with name:" + " " +boardTitle);
        currentBoardPage.chooseBoard();
        int sizeBefore = currentBoardPage.listSizeBefore();
        if(sizeBefore==0){
            currentBoardPage
                    .addNewList(listTitle);
            sizeBefore++;
        }
        log4j.info("Copy list with name:" + " " +listTitle);
        currentBoardPage.copyListByName();
        int sizeAfter = currentBoardPage.listSizeBefore();
        Assert.assertEquals(sizeAfter, sizeBefore+1,
                "List wasn't copy");
        log4j.endTestCase2();
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


