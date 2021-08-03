package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CurrentBoardPageHelper extends PageBase{
    String boardTitle;


    public CurrentBoardPageHelper(WebDriver driver, String boardName){
        this.driver = driver;
        this.boardTitle = boardName;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".content-all-boards")
    WebElement allBoardsPresent;
    @FindBy(css = ".mod-add")
    WebElement addNewBoard;
    @FindBy(xpath = "//input[@data-test-id='create-board-title-input']")
    WebElement newBoardTitle;
    @FindBy(xpath = "(//ul/li/button)[2]")
    WebElement backgroundImage;
    @FindBy(xpath = "//span[@aria-label='DownIcon']")
    WebElement dropDownMenuOfAccess;
    @FindBy(xpath = "(//nav/ul/li)[3]")
    WebElement typeOfAccess;
    @FindBy(xpath = "//button[@type='button'][contains(text(),'Yes, make board public')]")
    WebElement approveButton;
    @FindBy(xpath = "//button[@data-test-id='create-board-submit-button']")
    WebElement createBoardButton;
    @FindBy(css = ".js-cancel-edit")
    WebElement cancelingNewList;
    @FindBy(xpath = "//span[@aria-label='HouseIcon']")
    WebElement houseIcon;
    @FindBy(css = ".js-list-content")
    List<WebElement> listContent;
    @FindBy(css = ".placeholder")
    WebElement addListTab;
    @FindBy(css = ".list-name-input")
    WebElement listName;
    @FindBy(css = ".js-save-edit")
    WebElement addListButton;
    @FindBy(css = ".list-wrapper")
    WebElement allListsPresent;
    @FindBy(css = ".is-shown")
    WebElement listActionsMenu;
    @FindBy(css = ".js-add-card")
    WebElement addCard;
    @FindBy(css = ".js-card-title")
    WebElement addCardTitle;
    @FindBy(xpath = "//*[@value='Add card']")
    WebElement confirmButtonBorAddCard;
    @FindBy(css = ".js-cancel")
    WebElement cancelNewCard;
    @FindBy(css = ".js-close-list")
    WebElement archiveList;
    @FindBy(css = ".js-copy-list")
    WebElement copyList;
    @FindBy(css = ".js-autofocus")
    WebElement nameOfCopy;
    @FindBy(xpath = "//input[@value='Create list']")
    WebElement createList;
    @FindBy(xpath = "//*[@id='board']")
    WebElement boardWithLists;

//    @FindBy(css = ".list-card-title")
//    List<WebElement> numCards;


//===============================================CREATE NEW BOARD=======================================================
    public CurrentBoardPageHelper createNewBoard(String boardName){
        log4j.startMethod("CurrentBoardPageHelper - createNewBoard()");
        log4j.info("wait until list of all boards will be loaded");
        waitUntilElementIsVisible(allBoardsPresent, 10);
        log4j.info("click on 'add new board' button");
        addNewBoard.click();
        log4j.info("wait until new board title will be clickable");
        waitUntilElementIsClickable(newBoardTitle, 10);
        log4j.info("filling board title and click: " + boardName);
        clickAndFielding(newBoardTitle, boardName);
        log4j.info("wait until background will be clickable");
        waitUntilElementIsClickable(backgroundImage, 10);
        log4j.info("choose background image and click");
        backgroundImage.click();
        log4j.info("wait until type of access menu will be clickable");
        waitUntilElementIsClickable(dropDownMenuOfAccess, 10);
        log4j.info("click on menu");
        dropDownMenuOfAccess.click();
        log4j.info("wait until type of access will be clickable");
        waitUntilElementIsClickable(typeOfAccess, 10);
        log4j.info("choose type of access and click");
        typeOfAccess.click();
        log4j.info("wait until approve button will be clickable");
        waitUntilElementIsClickable(approveButton, 10);
        log4j.info("click on approve button");
        approveButton.click();
        log4j.info("wait until create board button will be clickable");
        waitUntilElementIsClickable(createBoardButton, 10);
        log4j.info("click on create board button");
        createBoardButton.click();
        log4j.info("cancel of new list adding");
        newListCancel();
        log4j.endMethod("CurrentBoardPageHelper - createNewBoard()");
        return this;
    }

    public CurrentBoardPageHelper newListCancel(){
        log4j.startMethod("CurrentBoardPageHelper - newListCancel()");
        log4j.info("wait until 'cancel list' button will be clickable");
        waitUntilElementIsClickable(cancelingNewList, 10);
        log4j.info("click on 'cancel list' button");
        cancelingNewList.click();
        log4j.endMethod("CurrentBoardPageHelper - newListCancel()");
        return this;
}

     public CurrentBoardPageHelper backToTheBoardsPage() {
         log4j.startMethod("CurrentBoardPageHelper - backToTheBoardsPage()");
         log4j.info("wait until all list will be present");
         waitUntilElementIsVisible(allListsPresent, 10);
         log4j.info("wait until house icon will be clickable");
         waitUntilElementIsClickable(houseIcon,10);
         log4j.info("click on house icon");
         houseIcon.click();
         log4j.endMethod("CurrentBoardPageHelper - backToTheBoardsPage()");
        return this;
    }

    public String receivingConfirmFromBoardsPage() {
        log4j.startMethod("CurrentBoardPageHelper - receivingConfirmFromBoardsPage()");
        log4j.info("wait until board name will be clickable");
        waitUntilElementIsClickable(By.xpath("//*[@class='boards-page-board-section mod-no-sidebar'][contains(.,'Way to success')]//div[@title='"+boardTitle+"']"), 10);
        WebElement newBoardFromRecentlyViewed = driver.findElement
        (By.xpath("//*[@class='boards-page-board-section mod-no-sidebar'][contains(.,'Recently viewed')]//div[@title='"+boardTitle+"']"));
        log4j.info("return name of the added board: " + boardTitle);
        log4j.endMethod("CurrentBoardPageHelper - receivingConfirmFromBoardsPage()");
        return newBoardFromRecentlyViewed.getText();
    }

//=============================================ADD NEW LIST==========================================================================

    public int listSizeBefore() {
        return listContent.size();
    }

    public int cardsQuantity() {
        List<WebElement> listContent = driver.findElements(By.cssSelector(".list-card-title"));
        return listContent.size();
    }

    public CurrentBoardPageHelper chooseBoard() {
        log4j.startMethod("CurrentBoardPageHelper - chooseBoard()");
        log4j.info("wait until all boards will be visible");
        waitUntilElementIsVisible(allBoardsPresent, 10);
        log4j.info("choose board by name:" + boardTitle);
        WebElement chooseBoard = driver.findElement(By.xpath("//div[@title='"+boardTitle+"']"));
        log4j.info("click on board");
        chooseBoard.click();
        log4j.info("wait until all list board will be visible");
        waitUntilElementIsVisible(boardWithLists, 10);
        log4j.endMethod("CurrentBoardPageHelper - chooseBoard()");
        return this;
    }


    public void  addNewListParam(String name) {
        log4j.startMethod("CurrentBoardPageHelper - addNewListParam()");
        int sizeBefore = this.listSizeBefore();
        log4j.info("click on 'Add new list' button");
        addListTab.click();
        WebElement listTitleField = listName;
        log4j.info("fill in 'list title' field");
        listTitleField.sendKeys(name);
        log4j.info("click on 'Add list' button");
        addListButton.click();
        log4j.info("wait until number of lists become +1");
        waitUntilElementsBecame(By.cssSelector(".js-list-content"), sizeBefore+1, 10);
            System.out.println("After adding: " + this.listSizeBefore());
        log4j.info("wait until 'cancel list' button will be clickable");
        waitUntilElementIsClickable(cancelingNewList, 10);
        log4j.info("click on 'cancel' button");
        cancelingNewList.click();
        log4j.info("wait until 'add another list' button will be clickable");
        waitUntilElementIsClickable(addListTab, 10);
        log4j.endMethod("CurrentBoardPageHelper - addNewListParam()");
    }

    public void  addNewList() {
        log4j.startMethod("CurrentBoardPageHelper - addNewList()");
        int sizeBefore = this.listSizeBefore();
        log4j.info("click on 'Add new list' button");
        addListTab.click();
        WebElement listTitleField = listName;
        log4j.info("fill in 'list title' field");
        listTitleField.sendKeys(listTitle);
        log4j.info("click on 'Add list' button");
        addListButton.click();
        log4j.info("wait until number of lists become +1");
        waitUntilElementsBecame(By.cssSelector(".js-list-content"), sizeBefore+1, 10);
        System.out.println("After adding: " + this.listSizeBefore());
        log4j.info("wait until 'cancel list' button will be clickable");
        waitUntilElementIsClickable(cancelingNewList, 10);
        log4j.info("click on 'cancel' button");
        cancelingNewList.click();
        log4j.info("wait until 'add another list' button will be clickable");
        waitUntilElementIsClickable(addListTab, 10);
        log4j.endMethod("CurrentBoardPageHelper - addNewList()");
    }


    //======================================================ADD NEW CARD BY LIST NAME==================================================

    public void addCardByListName(String cardName) {
        log4j.startMethod("CurrentBoardPageHelper - addCardByListName()");
        int cardsBefore = this.numCardsBeforeLst();
        WebElement listActionButton = driver.findElement
        (By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'"+listTitle+"')]]//*[@class='list-header-extras']"));
        log4j.info("click on 'list actions' menu");
        listActionButton.click();
        log4j.info("wait until 'list actions' menu weill be visible");
        waitUntilElementIsVisible(listActionsMenu, 10);
        log4j.info("wait until 'Add card' button weill be clickable");
        waitUntilElementIsClickable(addCard,10);
        log4j.info("click on 'Add card' button");
        addCard.click();
        log4j.info("wait until card title will be clickable");
        waitUntilElementIsClickable(addCardTitle, 10);
        log4j.info("fill in 'card title' field");
        addCardTitle.sendKeys(cardName);
        log4j.info("wait until 'Add card' button will be clickable");
        waitUntilElementIsClickable(confirmButtonBorAddCard, 10);
        log4j.info("click on 'Add card' button");
        confirmButtonBorAddCard.click();
        log4j.info("wait until number of cards become +1");
        waitUntilElementsBecame(By.cssSelector(".list-card-title"),cardsBefore+1, 10 );
        log4j.info("wait until 'cancel card' button will be clickable");
        waitUntilElementIsClickable(cancelNewCard, 10);
        log4j.info("click on 'cancel' button");
        cancelNewCard.click();
        log4j.endMethod("CurrentBoardPageHelper - addCardByListName()");
    }

    public int  numCardsBeforeLst(){
        List<WebElement> listContent = driver.findElements(By.cssSelector(".list-card-title"));
        return listContent.size();
    }

    //    public void newCardAddByListName() {
//        List<WebElement> nameOfList = driver.findElements(By.cssSelector(".js-list"));
//        List<String> nameOfListArr = new ArrayList<>();
//        nameOfList.stream().map(WebElement::getText).forEach(nameOfListArr::add);
//        for (String name : nameOfListArr) {
//            if (name.contains(listTitle)) {
//                waitUntilAllElementsArePresent(By.cssSelector(".js-list"), 10);
//                List<WebElement> listAction = driver.findElements(By.xpath("//a[@aria-label='List actions']"));
//                int indexNum = nameOfListArr.indexOf(name);
//                listAction.get(indexNum).click();
//
//                List<WebElement> numCardsBefore = driver.findElements(By.cssSelector(".ui-droppable"));
//                waitUntilElementIsClickable(By.cssSelector(".js-add-card"), 10);
//                WebElement addCard = driver.findElement(By.cssSelector(".js-add-card"));
//                addCard.click();
//                waitUntilElementIsClickable(By.cssSelector(".js-card-title"), 10);
//                WebElement cardTitleField = driver.findElement(By.cssSelector(".js-card-title"));
//                clickAndFielding(cardTitleField, cardTitle);
//
//                waitUntilElementIsClickable(By.cssSelector(".js-add-card"), 10);
//                driver.findElement(By.cssSelector(".js-add-card")).click();
//
//                waitUntilElementIsClickable(By.cssSelector(".js-cancel"), 10);
//                WebElement cancelOperation = driver.findElement(By.cssSelector(".js-cancel"));
//                cancelOperation.click();
//
//                waitUntilElementIsClickable(By.cssSelector(".placeholder"), 10);
//                List<WebElement> numCardsAfter = driver.findElements(By.cssSelector(".ui-droppable"));
//
//                Assert.assertEquals(numCardsAfter.size(), numCardsBefore.size()+1, "Something wrong with add card");
//            }
//        }
//    }

//==============================================ARCHIVE LIST BY NAME===============================================================


    public void listArchiveByName() {
        log4j.startMethod("CurrentBoardPageHelper - listArchiveByName()");
        log4j.info("wait until all lists in card will be present");
        waitUntilElementIsVisible(allListsPresent, 10);
        WebElement listActionButton = driver.findElement
        (By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'"+listTitle+"')]]//*[@class='list-header-extras']"));
        log4j.info("click on 'list actions' menu");
        listActionButton.click();
        log4j.info("wait until 'Archive this list' button weill be clickable");
        waitUntilElementIsClickable(archiveList, 10);
        log4j.info("click on 'Archive this list' button");
        archiveList.click();
        log4j.info("wait until 'add another list' button will be clickable");
        waitUntilElementIsVisible(addListTab, 10);
        log4j.endMethod("CurrentBoardPageHelper - listArchiveByName()");
    }
//=================================================COPY LIST BY NAME===============================================================


    public void copyListByName() {
        log4j.startMethod("CurrentBoardPageHelper - copyListByName()");
        WebElement listActionButton = driver.findElement
        (By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'"+listTitle+"')]]//*[@class='list-header-extras']"));
        log4j.info("click on 'list actions' menu");
        listActionButton.click();
        log4j.info("wait until 'Copy list' button weill be clickable");
        waitUntilElementIsClickable(copyList, 10);
        log4j.info("click on 'Copy list' button");
        copyList.click();
        log4j.info("wait until 'title of copy' field weill be clickable");
        waitUntilElementIsClickable(nameOfCopy, 10);
        nameOfCopy.click();
        nameOfCopy.clear();
        log4j.info("fill in 'title of copy' field");
        nameOfCopy.sendKeys(listTitle);
        log4j.info("wait until 'create list' button will be clickable");
        waitUntilElementIsClickable(createList, 10);
        log4j.info("click on 'create list' button");
        createList.click();
        log4j.endMethod("CurrentBoardPageHelper - copyListByName()");
    }

}

