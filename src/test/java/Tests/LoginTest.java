package Tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class LoginTest extends Setup
{
    WebDriver driver = new FirefoxDriver();

    /**
     * This is a simple login test
     */

    @BeforeTest
    public void setupEnvironment()
    {
        FirefoxBinary binary = new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
        FirefoxProfile profile = new FirefoxProfile();
        WebDriver driver = new FirefoxDriver(binary, profile);
        driver.get("http://www.evomag.ro/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        if (driver.getCurrentUrl() != "http://www.evomag.ro/")
        {
            driver.findElement(By.xpath(".//*[@id='header']/div[1]/a/img")).click();
            System.out.println("The page has a redirect\n");
        }
    }

    @Test
    public void Login() throws Exception
    {
        driver.get("http://www.evomag.ro/client/auth");
        driver.findElement(By.id("LoginClientForm_Email")).sendKeys("silviu.moraru@yahoo.com");
        driver.findElement(By.id("LoginClientForm_Password")).sendKeys("testwd123");
        driver.findElement(By.name("yt1")).click();

        WebElement sel_cont = driver.findElement(By.cssSelector(".c_header>em"));
        String nume_cont = sel_cont.getText();
        Assert.assertEquals("Silviu M.", nume_cont);

    }

    @AfterTest
    public void closeEverything()
    {
        driver.close();
    }
}
