package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;


public class CreateAccountAndLoginTest
{
    WebDriver driver = new FirefoxDriver();
    int random = 100 + (int)(Math.random() * ((10000 - 100) + 1));
    
    @BeforeTest
    public void setupEnvironment() throws InterruptedException
    {
        driver.get("http://www.evomag.ro/");
        // driver.wait(10);
    }

    /**
     * Test for creating an account
     * 
     * Step 1: Click on "Contul meu"
     * Step 2: Enter new account details
     * Step 3: Check the login by asserting page element (Contul meu -> name of the account)
     */
    @Test
    public void CreateAccountTest() throws Exception
    {
        driver.findElement(By.cssSelector("li>a[href*=auth]")).click();
        driver.findElement(By.cssSelector("#RegisterClientForm_FullName")).sendKeys("Sandel"+random);
        driver.findElement(By.cssSelector("#RegisterClientForm_Email")).sendKeys("notabot"+ random + "@test.com");
        driver.findElement(By.cssSelector("#RegisterClientForm_Password")).sendKeys("testtesttest");
        driver.findElement(By.name("yt0")).click();
        
        WebElement sel_cont = driver.findElement(By.cssSelector(".c_header em"));
        String nume_cont = sel_cont.getText();
        Assert.assertEquals("Sandel"+random, nume_cont);
               
    }

    @AfterTest
    public void closeEverything()
    {
        driver.close();
    }
}
