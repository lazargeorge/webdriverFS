package Tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import pageObjects.HomePage;

public class Setup
{

    WebDriver driver = new FirefoxDriver();

    @BeforeSuite
    public void setupEnvironment()
    {
        FirefoxBinary binary = new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
        FirefoxProfile profile = new FirefoxProfile();
        WebDriver driver = new FirefoxDriver(binary, profile);
        driver.get("http://www.evomag.ro/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        if (driver.getCurrentUrl() != "http://www.evomag.ro/")
        {
            HomePage.home(driver).click();
            System.out.println("The page has a redirect\n");
        }
    }

    @AfterSuite
    public void exit()
    {
        driver.close();
    }
}
