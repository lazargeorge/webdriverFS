import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Argint Alxandru
 */
public class test1
{

    WebDriver driver;
    String rndEmail;
    String rndPass;
    Base basicInfo;

    private LoginPage loginPage;
    private DefaultPage defaultPage;
    private CreateAccountPage createPage;
    private CartPage cartPage;
    private WebDriverWait wait;

    @BeforeTest
    public void createAccounts()
    {
        basicInfo = new Base();
        driver = basicInfo.driver;
        rndEmail = basicInfo.generateEmail();
        rndPass = basicInfo.generatePass();
        basicInfo.nrProduse = 0;
        System.out.println(rndEmail);
        System.out.println(rndPass);

    }

    @AfterTest
    public void close()
    {
        driver.quit();
    }

    @Test
    public void addToCartAndRemoveFromCartTest() throws InterruptedException
    {

        driver.get("https://www.emag.ro/user/register?ref=ssi_login");
        createPage = new CreateAccountPage(driver).get();
        createPage.createAccount("asdf guy", rndEmail, rndPass);

        /*
         * Select the first laptop
         * Add it to cart
         * Click on my cart
         */
        driver.get("http://www.emag.ro");
        defaultPage = new DefaultPage(driver).get();
        Actions mouseHover = new Actions(driver);
        mouseHover.moveToElement(driver.findElement(By.xpath("//a[text()=\"Laptop, Tablete & Telefoane\"]"))).build().perform();
        driver.findElement(By.xpath("//a[@href=\"/laptopuri/c?ref=hp_menu_link_1_3&tree_ref=3\"]")).click();
        driver.findElement(By.xpath("//div[@id=\"poza0\"]")).click();
        defaultPage.addToCart(basicInfo);

        /*
         * Verify if there are items inside the cart
         */
        cartPage = new CartPage(driver).get();
        // driver.findElement(By.xpath("//div[@id=\"emg-mini-cart\"]")).click();
        WebElement cart = driver.findElement(By.xpath("//span[@class=\"emg-cart-bubble\"]"));
        if (cart.getText() != "0")
            Assert.assertEquals(1, 1, "verificare cos");
        else
            Assert.assertEquals(0, 1, "verificare cos");

        /*
         * remove the items from cart and check
         */

        cartPage.removeFromCart(basicInfo.produse[0], 0, basicInfo);

        if (basicInfo.existsElement("//p[@class=\"avertisment\"]"))
            Assert.assertEquals(1, 1, "verificare stergere cos");
        else
            Assert.assertEquals(0, 1, "verificarestergerecos");

        defaultPage.logout();
        
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
        rndEmail = basicInfo.generateEmail();
        rndPass = basicInfo.generatePass();
        
        driver.get("https://www.emag.ro/user/register?ref=ssi_login");
        createPage = new CreateAccountPage(driver).get();
        createPage.createAccount("asdf guy", rndEmail, rndPass);

        driver.get("http://www.emag.ro");
        defaultPage = new DefaultPage(driver).get();
        defaultPage.logout();

        driver.get("http://www.emag.ro/user/login");
        loginPage = new LoginPage(driver).get();
        loginPage.login(rndEmail, rndPass);

        Assert.assertTrue(basicInfo.verificareUsername("asdf guy"), "The username is valid");

        defaultPage.logout();

        basicInfo.verificationEmail(rndEmail);

    }

    @Test
    public void loginTest()
    {

        loginPage = new LoginPage(driver).get();
        loginPage.login("ttO0YO5pTr3a@yopmail.com", "M7gTXA9nMmQW");

    }

}
