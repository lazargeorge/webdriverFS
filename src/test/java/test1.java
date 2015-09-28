import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

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
            fireFox.findElement(By.xpath(id));
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
        return true;
    }

    @BeforeSuite
    public void createAccounts()
    {
        generateEmail();
        generatePass();

        System.out.println(rndEmail);
        System.out.println(rndPass);
    }

    @AfterSuite
    public void close()
    {
        fireFox.quit();
    }

    @Test(dependsOnMethods = { "fireFoxEmagCreateAccountTest" })
    public void addToCartAndRemoveFromCartTest()
    {

        /**
         * Login
         */
        fireFox.get("http://www.emag.ro");

        /*
         * Select the first laptop
         * Add it to cart
         * Click on my cart
         */
        Actions mouseHover = new Actions(fireFox);
        mouseHover.moveToElement(fireFox.findElement(By.xpath("//a[text()=\"Laptop, Tablete & Telefoane\"]"))).build().perform();
        fireFox.findElement(By.xpath("//a[@href=\"/laptopuri/c?ref=hp_menu_link_1_3&tree_ref=3\"]")).click();
        fireFox.findElement(By.xpath("//div[@id=\"poza0\"]")).click();
        fireFox.findElement(By.xpath("//button[@id=\"add-to-cart-submit\"]")).click();
        fireFox.findElement(By.xpath("//button[@class=\"emg-button btn-change-warranty\"]")).click();

        /*
         * Verify if there are items inside the cart
         */
        fireFox.findElement(By.xpath("//div[@id=\"emg-mini-cart\"]")).click();
        WebElement cart = fireFox.findElement(By.xpath("//span[@class=\"emg-cart-bubble\"]"));
        if (cart.getText() != "0")
            Assert.assertEquals(1, 1, "verificare cos");
        else
            Assert.assertEquals(0, 1, "verificare cos");

        /*
         * remove the items from cart and check
         */
        WebElement remove = fireFox.findElement(By.xpath("//img[contains(@src,\"btn_close.png\")]"));
        remove.click();
        fireFox.findElement(By.xpath("//div[@id=\"yes\"]")).click();
        if (existsElement("//p[@class=\"avertisment\"]"))
            Assert.assertEquals(1, 1, "verificare stergere cos");
        else
            Assert.assertEquals(0, 1, "verificarestergerecos");

    }

    /**
     * Create a random email and pass word and create an acount on emag.ro
     * 1. generate random email and password
     * 2. go to yopmail.com
     * 3. create random email
     * 4. go to emag
     * 5. my acount
     * 6. create account
     * 7. random credentials
     * 8. create account
     * 9. Logout and log back in
     * 10. Verify username
     * 11. Login into yopmail and verify confirmation email
     */
    @Test
    public void fireFoxEmagCreateAccountTest() throws InterruptedException
    {
        /**
         * Create account
         */
        fireFox.get("http://www.emag.ro");
        fireFox.findElement(By.xpath("//figure[@id=\"emg-user-menu\"]/figcaption")).click();
        fireFox.findElement(By.xpath("//img[@src=\"https://s1emagst.akamaized.net/layout/ro/images/login_layout/newaccount-button.png?v3\"]")).click();
        WebElement name = fireFox.findElement(By.xpath("//input[@id=\"r_name\"]"));
        name.sendKeys("asdf guy");
        WebElement email = fireFox.findElement(By.xpath("//input[@id=\"r_email\"]"));
        email.sendKeys(rndEmail);
        WebElement pass = fireFox.findElement(By.xpath("//input[@id=\"r_password\"]"));
        pass.sendKeys(rndPass);
        WebElement rcpass = fireFox.findElement(By.xpath("//input[@id=\"rc_password\"]"));
        rcpass.sendKeys(rndPass);
        fireFox.findElement(By.xpath("//span[@id=\"ctrigger\"]")).click();
        fireFox.findElement(By.xpath("//input[@type=\"image\"]")).click();

        /**
         * Logout
         */
        Actions mouseHover = new Actions(fireFox);
        mouseHover.moveToElement(fireFox.findElement(By.xpath("//figure[@id=\"emg-user-menu\"]"))).build().perform();
        WebElement logout = fireFox.findElement(By.xpath("//div[@class=\"user-logout-icon\"]"));
        logout.click();

        /**
         * Login Emag
         */
        fireFox.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        fireFox.findElement(By.xpath("//figure[@id=\"emg-user-menu\"]/figcaption")).click();
        WebElement loginEmail = fireFox.findElement(By.xpath("//input[@id=\"r_email\"]"));
        loginEmail.sendKeys(rndEmail);
        WebElement loginPass = fireFox.findElement(By.xpath("//input[@id=\"r_password\"]"));
        loginPass.sendKeys(rndPass);
        fireFox.findElement(By.xpath("//input[@type=\"image\"]")).click();

        /**
         * Verify if name is good
         */
        WebElement user = fireFox.findElement(By.xpath("//span[text()=\"asdf guy\"]"));
        System.out.println(user.getText());
        Assert.assertEquals("asdf guy", user.getText());

        /**
         * Login into yopmail and verify the confirmation email
         */
        fireFox.get("http://yopmail.com");
        WebElement newEmail = fireFox.findElement(By.xpath("//input[@id=\"login\"]"));
        newEmail.sendKeys(rndEmail);
        fireFox.findElement(By.xpath("//input[@value=\"Check Inbox\"]")).click();
        fireFox.switchTo().frame("ifinbox");
        Assert.assertEquals("Bun venit la eMAG", fireFox.findElement(By.xpath("//span[text()=\"Bun venit la eMAG\"]")).getText());

    }

}
