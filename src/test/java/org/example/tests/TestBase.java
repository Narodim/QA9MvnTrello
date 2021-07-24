package org.example.tests;

import java.io.IOException;
import java.net.URL;

import org.example.SuiteConfiguration;
import org.example.pages.HomePageHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.openqa.selenium.support.PageFactory;
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
  public static String LOGIN = "qa.haifa.9@gmail.com";
  public static String PASSWORD = "MonitorSobaka_19";
  public static String boardTitle = "Friday";
  public static String listTitle = "name_first";
  public static String cardTitle = "text for a card field";
  HomePageHelper homePage;

  protected WebDriver driver;

  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    driver.get(baseUrl);
    homePage = PageFactory.initElements(driver, HomePageHelper .class);
    homePage.waitUntilPageIsLoaded();
  }

  //  @AfterSuite(alwaysRun = true)
//  public void tearDown() {
//    WebDriverPool.DEFAULT.dismissAll();
//  }
  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    driver.quit();
  }
}
