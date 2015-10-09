import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class Base
{

    static WebDriver fireFox = new FirefoxDriver();
    static WebDriver driver;
    static String[] produse = new String[100];
    static int nrProduse;
    Random rand = new Random();



  
    public boolean existsElement(String id)
    {
        try
        {
            driver.findElement(By.xpath(id));
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
        return true;
    }

    public void setDriver(String browser)
    {
        if (browser == "Fire_Fox")
            driver = fireFox;

    }

    /**
     * Login into yopmail and verify the confirmation email
     */
    public void verificationEmail(String email)
    {

        driver.get("http://yopmail.com");
        WebElement newEmail = driver.findElement(By.xpath("//input[@id=\"login\"]"));
        newEmail.sendKeys(email);
        driver.findElement(By.xpath("//input[@value=\"Check Inbox\"]")).click();
        driver.switchTo().frame("ifinbox");
        Assert.assertEquals("Bun venit la eMAG", driver.findElement(By.xpath("//span[text()=\"Bun venit la eMAG\"]")).getText());
    }
}
