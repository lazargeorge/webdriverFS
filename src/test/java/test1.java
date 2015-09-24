import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 */
public class test1 {

    private WebDriver chromeDriver;

    private WebDriver firefoxDriver;

    private String email;

    private String psswd;

    private SecureRandom random = new SecureRandom();

    public void setUpChrome() {
        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\V3790119\\Downloads\\chromedriver_win32\\chromedriver.exe");
        chromeDriver = new ChromeDriver();

    }

    public void setUpFirefox() {
        firefoxDriver = new FirefoxDriver();
    }

    @Test
    public void testName() throws Exception {
        Random randomGenerator = new Random();
        int telNr = randomGenerator.nextInt(100000000);
        int length = (int) (Math.log10(telNr) + 1);
        System.out.println(length + ' ' + telNr);
    }

    @Test
    public void clickLoginFirefox() {
        setUpFirefox();
        firefoxDriver.get("http://www.pcgarage.ro/");
        // autButton = button to authentification page
        WebElement autButton = firefoxDriver.findElement(By
            .xpath("//*[@id='user_header']//a[text()=\"Contul meu\"]"));
        autButton.click();
    }

    @Test
    public void clickLoginChrome() {
        setUpChrome();
        chromeDriver.get("http://www.pcgarage.ro/");
        // autButton = button to authentification page
        WebElement autButton = chromeDriver.findElement(By
            .xpath("//*[@id='user_header']//a[text()=\"Contul meu\"]"));
        autButton.click();
    }

    @Test
    public void createAccount() throws InterruptedException {
        Random randomGenerator = new Random();
        clickLoginFirefox();
        Thread.sleep(2000);
        WebElement newPrenumeInput = firefoxDriver.findElement(By
            .xpath("//*[@id='newfirstname']"));
        WebElement newNumeInput = firefoxDriver.findElement(By
            .xpath("//*[@id='newlastname']"));
        WebElement newTelefonInput = firefoxDriver.findElement(By
            .xpath("//*[@id='telephone']"));
        WebElement newEmailInput = firefoxDriver.findElement(By
            .xpath("//*[@id='newemail']"));
        WebElement newParolaInput = firefoxDriver.findElement(By
            .xpath("//*[@id='newpassword']"));
        WebElement newParola2Input = firefoxDriver.findElement(By
            .xpath("//*[@id='newpasswordretype']"));
        WebElement createBtn = firefoxDriver.findElement(By
            .xpath("//*[@id='register']//button"));
        newPrenumeInput.sendKeys(randomString());
        newNumeInput.sendKeys(randomString());
        newTelefonInput.sendKeys("07" + randomGenerator.nextInt(100000000));
        email = randomString() + "@trbvm.com";
        newEmailInput.sendKeys(email);
        psswd = randomString();
        newParolaInput.sendKeys(psswd);
        newParola2Input.sendKeys(psswd);
        firefoxDriver.manage().window().maximize();
        createBtn.click();
        Thread.sleep(1000);
        WebElement accountCreated = firefoxDriver.findElement(By
            .xpath("//div[@id='listing-right']//h1"));
        Assert.assertTrue(accountCreated.isDisplayed());
        System.out.println("Email: " + email);
        System.out.println("Password: " + psswd);
    }

    // Before this test is required to create an account

    @Test
    public void loginFirefox() throws InterruptedException {
        clickLoginFirefox();
        Thread.sleep(1000);
        WebElement emailInput = firefoxDriver.findElement(By
            .xpath("//*[@id='email']"));
        emailInput.sendKeys(email);
        WebElement psswdInput = firefoxDriver.findElement(By
            .xpath("//*[@id='password']"));
        psswdInput.sendKeys(psswd);
        WebElement autClick = firefoxDriver.findElement(By
            .xpath("//*[@id='login']//button[text()=\"Autentificare\"]"));
        autClick.click();
    }

    @Test
    public void addToChart() throws InterruptedException {
        createAccount();
        WebElement laptopMeniu = firefoxDriver.findElement(By
            .cssSelector(".cat-nav-tab[href*=\"laptop\"]"));
        laptopMeniu.click();
        WebElement product = firefoxDriver.findElement(By
            .xpath("(//*[@class='product-box']//a[@class='add'])[2]"));
        product.click();
        Thread.sleep(1000);
        WebElement productInChart = firefoxDriver
            .findElement(By
                .xpath(".//*[@class=\"cpm-product\"]//a[contains(text(),\"Yoga 500-15\")]"));
        Assert.assertTrue(productInChart.isDisplayed());
    }

    public String randomString() {
        return new BigInteger(130, random).toString(32);
    }

    // login
    // add to chart
    // remove from
}
