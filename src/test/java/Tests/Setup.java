package Tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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

    @BeforeMethod
    public void setupEnvironment()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://www.evomag.ro/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        if (driver.getCurrentUrl() != "http://www.evomag.ro/")
        {
            HomePage homePage = PageFactory.initElements(driver, HomePage.class);
            homePage.go_Home(driver);
            System.out.println("The page has a redirect\n");
        }

    }

    @AfterMethod
    public void exitwithScreenshot(ITestResult testResult) throws IOException
    {
        if ((testResult.getStatus() == ITestResult.FAILURE) || (testResult.getStatus() == ITestResult.SKIP))
        {
            try
            {
                // TakesScreenshot ts = (TakesScreenshot)driver;
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("C:\\Users\\v3790129\\Documents\\GitHub\\webdriverFS\\screenshots\\eroare.jpg"));
                System.out.println("Test failed, screenshot saved in: " + screenshot.getAbsolutePath());
            }
            catch (Exception e)
            {
                System.out.println("Error while taking screenshot");
            }

            driver.close();
        }

    }

}
