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
    public CurrentBoardPageHelper createNewBoard(String boardName) throws InterruptedException {
        waitUntilElementIsVisible(allBoardsPresent, 10);
        addNewBoard.click();
        waitUntilElementIsClickable(newBoardTitle, 10);
        clickAndFielding(newBoardTitle, boardName);
        waitUntilElementIsClickable(backgroundImage, 10);
        backgroundImage.click();
        waitUntilElementIsClickable(dropDownMenuOfAccess, 10);
        dropDownMenuOfAccess.click();
        waitUntilElementIsClickable(typeOfAccess, 10);
        typeOfAccess.click();
        waitUntilElementIsClickable(approveButton, 10);
        approveButton.click();
        waitUntilElementIsClickable(createBoardButton, 10);
        createBoardButton.click();
        newListCancel();
        return this;
    }

    public CurrentBoardPageHelper newListCancel() throws InterruptedException {
        waitUntilElementIsClickable(cancelingNewList, 10);
        Thread.sleep(3000);
        cancelingNewList.click();
        return this;
}

     public CurrentBoardPageHelper backToTheBoardsPage() {
        waitUntilElementIsVisible(allListsPresent, 10);
        waitUntilElementIsVisible(houseIcon,10);
        houseIcon.click();
        return this;
    }

    public String receivingConfirmFromBoardsPage() {
        waitUntilElementIsClickable(By.xpath("//*[@class='boards-page-board-section mod-no-sidebar'][contains(.,'Way to success')]//div[@title='"+boardTitle+"']"), 10);
        WebElement newBoardFromRecentlyViewed = driver.findElement
        (By.xpath("//*[@class='boards-page-board-section mod-no-sidebar'][contains(.,'Recently viewed')]//div[@title='"+boardTitle+"']"));
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
        waitUntilElementIsVisible(allBoardsPresent, 10);
        WebElement chooseBoard = driver.findElement(By.xpath("//div[@title='"+boardTitle+"']"));
        chooseBoard.click();
        waitUntilElementIsVisible(boardWithLists, 10);
        return this;
    }


    public void  addNewListParam(String name) {
        int sizeBefore = this.listSizeBefore();
        addListTab.click();
        WebElement listTitleField = listName;
        listTitleField.sendKeys(name);
        addListButton.click();
        waitUntilElementsBecame(By.cssSelector(".js-list-content"), sizeBefore+1, 10);
            System.out.println("After adding: " + this.listSizeBefore());
        waitUntilElementIsClickable(cancelingNewList, 10);
        cancelingNewList.click();
        waitUntilElementIsClickable(addListTab, 10);

    }

    public void  addNewList() {
        int sizeBefore = this.listSizeBefore();
        addListTab.click();
        WebElement listTitleField = listName;
        listTitleField.sendKeys(listTitle);
        addListButton.click();
        waitUntilElementsBecame(By.cssSelector(".js-list-content"), sizeBefore+1, 10);
        System.out.println("After adding: " + this.listSizeBefore());
        waitUntilElementIsClickable(cancelingNewList, 10);
        cancelingNewList.click();
        waitUntilElementIsClickable(addListTab, 10);

    }


    //======================================================ADD NEW CARD BY LIST NAME==================================================

    public void addCardByListName(String cardName) {
        int cardsBefore = this.numCardsBeforeLst();
        WebElement listActionButton = driver.findElement
        (By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'"+listTitle+"')]]//*[@class='list-header-extras']"));
        listActionButton.click();
        waitUntilElementIsVisible(listActionsMenu, 10);
        waitUntilElementIsClickable(addCard,10);
        addCard.click();
        waitUntilElementIsClickable(addCardTitle, 10);
        addCardTitle.sendKeys(cardName);
        waitUntilElementIsClickable(confirmButtonBorAddCard, 10);
        confirmButtonBorAddCard.click();
        waitUntilElementsBecame(By.cssSelector(".list-card-title"),cardsBefore+1, 10 );
        waitUntilElementIsClickable(cancelNewCard, 10);
        cancelNewCard.click();
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
        waitUntilElementIsVisible(allListsPresent, 10);
        WebElement listActionButton = driver.findElement
        (By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'"+listTitle+"')]]//*[@class='list-header-extras']"));
        listActionButton.click();
        waitUntilElementIsClickable(archiveList, 10);
        archiveList.click();
        waitUntilElementIsVisible(addListTab, 10);
    }




//=================================================COPY LIST BY NAME===============================================================


    public void copyListByName() {
        WebElement listActionButton = driver.findElement
        (By.xpath("//*[@class='list js-list-content'][.//*[contains(.,'"+listTitle+"')]]//*[@class='list-header-extras']"));
        listActionButton.click();
        waitUntilElementIsClickable(copyList, 10);
        copyList.click();
        waitUntilElementIsClickable(nameOfCopy, 10);
        nameOfCopy.click();
        nameOfCopy.clear();
        nameOfCopy.sendKeys(listTitle);
        waitUntilElementIsClickable(createList, 10);
        createList.click();
    }

}

