package org.example.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;


import com.google.common.io.Files;
import org.example.SuiteConfiguration;
import org.example.pages.HomePageHelper;
import org.example.util.LogLog4j;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  public static LogLog4j log4j = new LogLog4j();
  public static String LOGIN = "qa.haifa.9@gmail.com";
  public static String PASSWORD = "MonitorSobaka_19";
  public static String boardTitle = "Sunday";
  public static String listTitle = "name_second";
  public static String cardTitle = "text for a card field";
  HomePageHelper homePage;

//  protected WebDriver driver;
protected EventFiringWebDriver driver;
  public static class MyListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      log4j.info("Element has to be found: " + by);
    }
    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      log4j.info("Element was found: " + by);
    }
    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      log4j.error("Error - " + throwable);
//      getScreenShot((TakesScreenshot)driver);
    }
    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
      log4j.info("Window before switch: " + windowName);
    }
    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
      log4j.info("Window after switch: " + windowName);
    }
    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
      log4j.info("Element has to be clicked: " + element);
    }
    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
      log4j.info("Element was clicked: " + element);
    }
  }

  public static void getScreenShot(TakesScreenshot driver, Method method_name){
    File tmp = driver.getScreenshotAs(OutputType.FILE);
    File screen = new File(method_name.getName()+"_" + System.currentTimeMillis() + ".png");
    try {
      Files.copy(tmp, screen);
    } catch (IOException e) {
      e.printStackTrace();
    }
    log4j.info("See screenshot :" + screen);
  }

  @BeforeSuite(alwaysRun = true)
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod(alwaysRun = true)
  public void initWebDriver() {
    //enable headless mod(work without gpu)
    ChromeOptions options = new ChromeOptions();
    options.addArguments("headless");
    options.addArguments("window-size=2560x1440");
    driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl,options));
    //
//    driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities));
    driver.register(new MyListener());
    driver.manage().window().maximize();
    driver.get(baseUrl);
    homePage = PageFactory.initElements(driver, HomePageHelper .class);
    homePage.waitUntilPageIsLoaded();
  }

  //  @AfterSuite(alwaysRun = true)
//  public void tearDown() {
//    WebDriverPool.DEFAULT.dismissAll();
//  }

  @AfterMethod(alwaysRun = true)
  public void finishTest(ITestResult result, Method method_name){
    if(result.getStatus()==ITestResult.FAILURE){
      log4j.error("Test was failure");
      getScreenShot(driver, method_name);
    }
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    driver.quit();
  }
}

