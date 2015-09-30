package Tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class CreateAccountAndLoginTest extends Setup
{
    WebDriver driver = new FirefoxDriver();
    int random = 100 + (int) (Math.random() * ((10000 - 100) + 1));

    @BeforeGroups
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

    /**
     * Test for creating an account
     * Step 1: Click on "Contul meu"
     * Step 2: Enter new account details
     * Step 3: Check the login by asserting page element (Contul meu -> name of the account)
     */

    @Test(groups = "create login")
    public void CreateAccountTest() throws Exception
    {
        driver.get("http://www.evomag.ro");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String nume_generat = "Sandel" + random;
        driver.findElement(By.cssSelector("li>a[href*=auth]")).click();
        driver.findElement(By.cssSelector("#RegisterClientForm_FullName")).sendKeys(nume_generat);
        driver.findElement(By.cssSelector("#RegisterClientForm_Email")).sendKeys(nume_generat + "@test.com");
        driver.findElement(By.cssSelector("#RegisterClientForm_Password")).sendKeys("testtesttest");
        driver.findElement(By.name("yt0")).click();

        WebElement sel_cont = driver.findElement(By.cssSelector(".c_header em"));
        String nume_cont = sel_cont.getText();
        Assert.assertEquals(nume_generat, nume_cont);

    }

    /**
     * Test for checking the fields have restrictions for min caracters and email formatting
     * Step1 : Click on Create account and enter 4 characters in Nume field (min of 5)
     * Step 2: Click on Create account and enter 4 characters in E-mail field (min: |1 char| "@" |1 char|"."|3 char|)
     * Step 3: Click on Create account and enter 4 characters in Parola field (min of 5)
     * Step 4: Check that an alert pops up with a line of text for each error
     */

    @Test(groups = "create login")
    public void NegativeCreateAccountTest() throws Exception
    {
        driver.get("http://www.evomag.ro");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("li>a[href*=auth]")).click();
        driver.findElement(By.cssSelector("#RegisterClientForm_FullName")).sendKeys("1324");
        driver.findElement(By.cssSelector("#RegisterClientForm_Email")).sendKeys("1234");
        driver.findElement(By.cssSelector("#RegisterClientForm_Password")).sendKeys("1234");
        driver.findElement(By.name("yt0")).click();

        String alert_text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals("Numele trebuie sa contina minim 5 caractere!\nAdresa de e-mail invalida!\nParola trebuie sa contina minim 5 caractere.",
                alert_text);
    }

    @AfterGroups
    public void closeEverything()
    {
        driver.close();
    }
}
