import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Argint Alxandru
 */
public class test1 extends Base
{

    Base basicInfo = new Base();

    @BeforeTest
    public void createAccounts()
    {
        generateEmail();
        generatePass();
        nrProduse = 0;
        System.out.println(rndEmail);
        System.out.println(rndPass);

    }

    @AfterSuite
    public void close()
    {
        driver.quit();
    }

    /**
     * Verify if name is good
     */
    private void verificareUsername(String username)
    {
        driver.get("http://www.emag.ro");
        WebElement user = driver.findElement(By.xpath("//span[text()=\"" + username + "\"]"));
        System.out.println(user.getText());
        Assert.assertEquals(username, user.getText());
    }

    @Test(dependsOnMethods = { "fireFoxEmagCreateAccountTest" })
    public void addToCartAndRemoveFromCartTest()
    {

        setDriver("Fire_Fox");
        DefaultPage defaultPage = new DefaultPage();

        /*
         * Select the first laptop
         * Add it to cart
         * Click on my cart
         */
        Actions mouseHover = new Actions(driver);
        mouseHover.moveToElement(driver.findElement(By.xpath("//a[text()=\"Laptop, Tablete & Telefoane\"]"))).build().perform();
        driver.findElement(By.xpath("//a[@href=\"/laptopuri/c?ref=hp_menu_link_1_3&tree_ref=3\"]")).click();
        driver.findElement(By.xpath("//div[@id=\"poza0\"]")).click();
        defaultPage.addToCart();

        /*
         * Verify if there are items inside the cart
         */
        driver.findElement(By.xpath("//div[@id=\"emg-mini-cart\"]")).click();
        WebElement cart = driver.findElement(By.xpath("//span[@class=\"emg-cart-bubble\"]"));
        if (cart.getText() != "0")
            Assert.assertEquals(1, 1, "verificare cos");
        else
            Assert.assertEquals(0, 1, "verificare cos");

        /*
         * remove the items from cart and check
         */

        defaultPage.removeFromCart(produse[0], 0);

        if (existsElement("//p[@class=\"avertisment\"]"))
            Assert.assertEquals(1, 1, "verificare stergere cos");
        else
            Assert.assertEquals(0, 1, "verificarestergerecos");

    }

    /**
     * Create a random email and pass word and create an acount on emag.ro
     * 1. create an account
     * 2. log out
     * 3. login
     * 4. veryfi username
     * 5. logout
     * 6. go on yopmail veryfi confirmation email
     */
    @Test
    public void fireFoxEmagCreateAccountTest() throws InterruptedException
    {
        setDriver("Fire_Fox");
        CreateAccountPage createPage = PageFactory.initElements(driver, CreateAccountPage.class);
        createPage.createAccount();
        DefaultPage defaultPage = PageFactory.initElements(driver, DefaultPage.class);
        defaultPage.logout();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login(rndEmail, rndPass);
        verificareUsername("asdf guy");
        defaultPage.logout();
        verificationEmail(rndEmail);

    }

}
