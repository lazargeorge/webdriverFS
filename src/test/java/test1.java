import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

/**
 * Argint Alxandru
 */
public class test1
{

    // create random 10 caracters email
    static WebDriver fireFox = new FirefoxDriver();
    static String rndEmail;
    static String rndPass;
    Random rand = new Random();

    
    /**
     * 
     *  generates random email adress
     */
    public String generateEmail()
    {
        for (int i = 0; i <= 12; i++)
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
     * 
     *  generates random password
     */
    public String generatePass()
    {

        for (int i = 0; i <= 15; i++)
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

    @BeforeTest
    public void createAccounts()
    {
        generateEmail();
        generatePass();
        fireFox.get("http://yopmail.com");
        WebElement newEmail = fireFox.findElement(By.xpath("//input[@id=\"login\"]"));
        newEmail.sendKeys(rndEmail);
        fireFox.findElement(By.xpath("//input[@value=\"Check Inbox\"]")).click();

    }

    @AfterTest
    public void close()
    {
        fireFox.quit();
    }

    
    /**
     *  Goes on the emag site and tryes to login
     *  1. navigate to emag.ro
     *  2. click back to site
     *  3. click on my account
     *  4. put in random credentials
     *  5. press login
     */
    
    @Test
    public static void fireFoxEmagLoginTest()
    {

        fireFox.get("http://www.emag.ro");

        // WebDriver chrome = new ChromeDriver();
        // chrome.get("http://www.emag.ro");

        fireFox.findElement(By.xpath("//a[@class=\"back-button\"]")).click();
        fireFox.findElement(By.xpath("//figure[@id=\"emg-user-menu\"]/figcaption")).click();

        WebElement email = fireFox.findElement(By.xpath("//input[@id=\"r_email\"]"));
        email.sendKeys(rndEmail);

        WebElement pass = fireFox.findElement(By.xpath("//input[@id=\"r_password\"]"));
        pass.sendKeys(rndPass);

        fireFox.findElement(By.xpath("//input[@type=\"image\"]")).click();

    }

    
    /**
     * 
     * Create a random email and pass word and create an acount on emag.ro
     * 
     * 1. generate random email and password
     * 2. go to yopmail.com
     * 3. create random email
     * 4. go to emag
     * 5. my acount
     * 6. create account
     * 7. random credentials
     * 8. create account
     * 9. close firefox
     * 10. open another firefox windows
     * 11. login
     */
    @Test
    public void fireFoxEmagCreateAccountTest() throws InterruptedException
    {
        // emag
        fireFox.get("http://www.emag.ro");
        // inapoi la site
        fireFox.findElement(By.xpath("//a[@class=\"back-button\"]")).click();
        // my acount
        fireFox.findElement(By.xpath("//figure[@id=\"emg-user-menu\"]/figcaption")).click();
        // create account
        fireFox.findElement(By.xpath("//img[@src=\"https://s1emagst.akamaized.net/layout/ro/images/login_layout/newaccount-button.png?v3\"]")).click();
        // introducem datele
        WebElement name = fireFox.findElement(By.xpath("//input[@id=\"r_name\"]"));
        name.sendKeys("asdf guy");

        WebElement email = fireFox.findElement(By.xpath("//input[@id=\"r_email\"]"));
        email.sendKeys(rndEmail);

        WebElement pass = fireFox.findElement(By.xpath("//input[@id=\"r_password\"]"));
        pass.sendKeys(rndPass);

        WebElement rcpass = fireFox.findElement(By.xpath("//input[@id=\"rc_password\"]"));
        rcpass.sendKeys(rndPass);
        // i agree with the terms
        fireFox.findElement(By.xpath("//span[@id=\"ctrigger\"]")).click();
        // register
        fireFox.findElement(By.xpath("//input[@type=\"image\"]")).click();

        fireFox.quit();

        WebDriver newFireFox= new FirefoxDriver();
        
        //login process
        newFireFox.get("http://www.emag.ro/homepage");
        newFireFox.findElement(By.xpath("//figure[@id=\"emg-user-menu\"]/figcaption")).click();
        WebElement loginEmail = newFireFox.findElement(By.xpath("//input[@id=\"r_email\"]"));
        loginEmail.sendKeys(rndEmail);
        WebElement loginPass = newFireFox.findElement(By.xpath("//input[@id=\"r_password\"]"));
        loginPass.sendKeys(rndPass);
        newFireFox.findElement(By.xpath("//input[@type=\"image\"]")).click();
        WebElement user= newFireFox.findElement(By.xpath("//span[text()=\"asdf guy\"]"));
        
        System.out.println(user.getText());
        Assert.assertEquals("asdf guy",user.getText());
        
        newFireFox.quit();

    }

}
