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
    static String rndEmail;
    static String rndPass;
    static String[] produse = new String[100];
    static int nrProduse;
    Random rand = new Random();

    /**
     * generates random email adress
     */
    public String generateEmail()
    {
        for (int i = 1; i <= 12; i++)
        {
            int ok = 0;
            while (ok != 1)
            {
                int ascii = rand.nextInt(123);
                if (ascii >= 48 && ascii <= 57)
                {
                    rndEmail += Character.toString((char) ascii);
                    ok = 1;
                }
                else if (ascii >= 65 && ascii <= 90 || ascii >= 97 && ascii <= 122)
                {
                    rndEmail += Character.toString((char) ascii);
                    ok = 1;
                }
            }
        }

        rndEmail += "@yopmail.com";
        return rndEmail;
    }

    /**
     * generates random password
     */
    public String generatePass()
    {

        for (int i = 1; i <= 12; i++)
        {
            int ok = 0;
            while (ok != 1)
            {
                int ascii = rand.nextInt(123);
                if (ascii >= 48 && ascii <= 57)
                {
                    rndPass += Character.toString((char) ascii);
                    ok = 1;
                }
                else if (ascii >= 65 && ascii <= 90 || ascii >= 97 && ascii <= 122)
                {
                    rndPass += Character.toString((char) ascii);
                    ok = 1;
                }
            }
        }
        return rndPass;
    }

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
