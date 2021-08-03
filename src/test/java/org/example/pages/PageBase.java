package org.example.pages;

import org.example.util.LogLog4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
    WebDriver driver;

    public static LogLog4j log4j = new LogLog4j();
//    public static String LOGINPOSITIVE = "qa.haifa.9@gmail.com";
//    public static String PASSWORDPOSITIVE = "MonitorSobaka_19";
//    public static String loginNegative = "Neg" + System.currentTimeMillis() + "@gmail.com";
//    public static String passwordNegative = "Frgbh9980";
//    public static String boardTitle = "Trello_test";
    public static String listTitle = "name_first";
    public static String cardTitle = "text for a card field";


    public void clickAndFielding(WebElement field, String value) {
        field.click();
        field.sendKeys(value);
    }

    public void newWindowOpen(Integer quantity, Integer time){
        waitUntilNumberOfWindowsToBe(quantity, time);
    }

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsInvisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsInvisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsArePresent(By locator, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void waitUntilElementsBecame(By locator, int quantity, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.numberOfElementsToBe(locator,quantity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilNumberOfWindowsToBe(int quantity, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.numberOfWindowsToBe(quantity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
