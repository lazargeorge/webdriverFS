package Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pageObjects.HomePage;

/**
 * @author Silviu Moraru
 */

public class Setup
{

    WebDriver driver;
/**
 * This environment setup initialises the Webdriver, goes to the website's URL and has a "back-to-homepage" mechanism in case the homepage has a redirect
 */
    @BeforeTest
    public void setupEnvironment()
    {
        driver = new FirefoxDriver();
        driver.get("http://www.evomag.ro/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        if (driver.getCurrentUrl() != "http://www.evomag.ro/")
        {
            HomePage homePage = PageFactory.initElements(driver, HomePage.class);
            homePage.go_Home(driver);
            System.out.println("The page has a redirect\n");
        }

    }

    @AfterTest
    public void exit()
    {
        driver.close();
    }
}
