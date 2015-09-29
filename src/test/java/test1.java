import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 */
public class test1 {

    private WebDriver driver;

    private String email;

    private String psswd;

    private String productNameFromList;

    private String productNameFromChart;

    private SecureRandom random = new SecureRandom();

    private WebDriverWait wait;

    private static Properties prop = new Properties();

    public void initProp() throws Exception {
        String propFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader()
            .getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("Property file '" + propFileName
                + "' not found!");
        }
    }

    public WebDriver getBrowser() throws Exception {
        initProp();
        String browser = prop.getProperty("browser");
        switch (browser) {
            case "FireFox":
                return new FirefoxDriver();
            case "Chrome":
                System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\V3790119\\Downloads\\chromedriver_win32\\chromedriver.exe");
                return new ChromeDriver();
            default:
                return new FirefoxDriver();
        }
    }

    @BeforeMethod
    public void setUp() throws Exception {
        driver = getBrowser();
        wait = new WebDriverWait(driver, 10);

    }

    public void clickLoginFirefox() {

        driver.get("http://www.pcgarage.ro/");
        // autButton = button to authentification page
        WebElement autButton = driver.findElement(By
            .xpath("//*[@id='user_header']//a[text()=\"Contul meu\"]"));
        autButton.click();
    }

    public void clickLoginChrome() {

        driver.get("http://www.pcgarage.ro/");
        // autButton = button to authentification page
        WebElement autButton = driver.findElement(By
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
        driver.manage().window().maximize();
        createBtn.click();
        WebElement accountCreated =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='listing-right']//h1")));
        Assert.assertTrue(accountCreated.isDisplayed());
        //System.out.println("Email: " + email);
        //System.out.println("Password: " + psswd);
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
        WebElement laptopMeniu = driver.findElement(By
            .cssSelector(".cat-nav-tab[href*=\"laptop\"]"));
        laptopMeniu.click();
        WebElement product = driver.findElement(By.xpath("(//*[@class='product-box'])[6]/div/a"));
        productNameFromList = product.getAttribute("title");
        WebElement addToChart = driver.findElement(By
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
            driver.findElement(By.xpath("//*[@class=\"cpm-top-gc\"]/a[contains(@href,\"golestecos\")]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", remove);
        WebElement empty = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//*[@id='cart-page-desktop']//*[contains(text(),\"gol\")]")));
        Assert.assertTrue(empty.isDisplayed());
        closeFirefox();
    }

    public String randomString() {
        return new BigInteger(130, random).toString(32);
    }

    @AfterMethod
    private void closeFirefox() {
        driver.close();
    }

}
