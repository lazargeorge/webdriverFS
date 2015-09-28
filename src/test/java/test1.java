import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 */
public class test1 {

    private WebDriver chromeDriver;

    private WebDriver firefoxDriver;

    private String email;

    private String psswd;

    private String productNameFromList;

    private String productNameFromChart;

    private SecureRandom random = new SecureRandom();

    private WebDriverWait wait;

    public void setUpChrome() {
        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\V3790119\\Downloads\\chromedriver_win32\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        wait = new WebDriverWait(chromeDriver, 10);

    }

    public void setUpFirefox() {
        firefoxDriver = new FirefoxDriver();
        wait = new WebDriverWait(firefoxDriver, 60);
    }

    public void clickLoginFirefox() {
        setUpFirefox();
        firefoxDriver.get("http://www.pcgarage.ro/");
        // autButton = button to authentification page
        WebElement autButton = firefoxDriver.findElement(By
            .xpath("//*[@id='user_header']//a[text()=\"Contul meu\"]"));
        autButton.click();
    }

    public void clickLoginChrome() {
        setUpChrome();
        chromeDriver.get("http://www.pcgarage.ro/");
        // autButton = button to authentification page
        WebElement autButton = chromeDriver.findElement(By
            .xpath("//*[@id='user_header']//a[text()=\"Contul meu\"]"));
        autButton.click();
    }

    public void createAccount() throws InterruptedException {
        Random randomGenerator = new Random();
        clickLoginFirefox();
        WebElement newPrenumeInput =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='newfirstname']")));
        WebElement newNumeInput =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='newlastname']")));
        WebElement newTelefonInput =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='telephone']")));
        WebElement newEmailInput =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='newemail']")));
        WebElement newParolaInput =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='newpassword']")));
        WebElement newParola2Input =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='newpasswordretype']")));
        WebElement createBtn =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='register']//button")));
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
        WebElement accountCreated =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='listing-right']//h1")));
        Assert.assertTrue(accountCreated.isDisplayed());
        System.out.println("Email: " + email);
        System.out.println("Password: " + psswd);
    }

    @Test
    public void loginFirefox() throws InterruptedException {
        email = "7k5t07k6imjrgjvefsk1a9nr5v@trbvm.com";
        psswd = "m6sp2csbin8am217h1p7ru2kq2";
        clickLoginFirefox();
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='email']")));
        emailInput.sendKeys(email);
        WebElement psswdInput =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='password']")));
        psswdInput.sendKeys(psswd);
        WebElement autClick =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[@id='login']//button[text()=\"Autentificare\"]")));
        autClick.click();
        closeFirefox();
    }

    public void addToChart() throws InterruptedException {
        createAccount();
        WebElement laptopMeniu = firefoxDriver.findElement(By
            .cssSelector(".cat-nav-tab[href*=\"laptop\"]"));
        laptopMeniu.click();
        WebElement product = firefoxDriver.findElement(By.xpath("(//*[@class='product-box'])[6]/div/a"));
        productNameFromList = product.getAttribute("title");
        WebElement addToChart = firefoxDriver.findElement(By
            .xpath(" (//*[@class=\"product-box\"])[6]//a[@class=\"add\"]"));
        addToChart.click();
        WebElement productInChart =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ct-name>p>a")));
        productNameFromChart = productInChart.getText();
        Assert.assertEquals("Notebook / Laptop " + productNameFromList, productNameFromChart);
    }

    @Test
    public void removeFromChart() throws InterruptedException {
        addToChart();
        WebElement title =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']//h1")));
        WebElement remove =
            firefoxDriver.findElement(By.xpath("//*[@class=\"cpm-top-gc\"]/a[contains(@href,\"golestecos\")]"));
        JavascriptExecutor js = (JavascriptExecutor) firefoxDriver;
        js.executeScript("arguments[0].click();", remove);
        WebElement empty = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//*[@id='cart-page-desktop']//*[contains(text(),\"gol\")]")));
        Assert.assertTrue(empty.isDisplayed());
        closeFirefox();
    }

    public String randomString() {
        return new BigInteger(130, random).toString(32);
    }

    private void closeFirefox() {
        firefoxDriver.close();
    }

    private void closeChrome() {
        chromeDriver.close();
    }
}
