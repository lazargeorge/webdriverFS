import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;

import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 */
public class test1 {

    private WebDriver driver;

    private HomePage homePage;

    private LoginPage loginPage;

    private CreateAccountPage createAccountPage;

    private AddToChartPage addToChartPage;

    private RemoveFromChartPage removeFromChartPage;

    private String email;

    private String psswd;

    private String productNameFromList;

    private String productNameFromChart;

    private SecureRandom random = new SecureRandom();

    // private WebDriverWait wait;

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
        // wait = new WebDriverWait(driver, 10);
    }

    public void clickLogin() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        driver.get("http://www.pcgarage.ro/");
        // autButton = button to authentification page
        homePage.getAutButton().click();
    }

    public void createAccount() throws InterruptedException {
        createAccountPage = PageFactory.initElements(driver, CreateAccountPage.class);
        Random randomGenerator = new Random();
        clickLogin();
        createAccountPage.getNewPrenumeInput().sendKeys(randomString());
        createAccountPage.getNewNumeInput().sendKeys(randomString());
        createAccountPage.getNewTelefonInput().sendKeys("07" + randomGenerator.nextInt(100000000));
        email = randomString() + "@trbvm.com";
        createAccountPage.getNewEmailInput().sendKeys(email);
        psswd = randomString();
        createAccountPage.getNewParolaInput().sendKeys(psswd);
        createAccountPage.getNewParola2Input().sendKeys(psswd);
        driver.manage().window().maximize();
        createAccountPage.getCreateBtn().click();
        Assert.assertTrue(createAccountPage.getAccountCreated().isDisplayed());
        // System.out.println("Email: " + email);
        // System.out.println("Password: " + psswd);
    }

    @Test
    public void login() throws InterruptedException {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        email = "7k5t07k6imjrgjvefsk1a9nr5v@trbvm.com";
        psswd = "m6sp2csbin8am217h1p7ru2kq2";
        clickLogin();
        loginPage.getEmailInput().sendKeys(email);
        loginPage.getPsswdInput().sendKeys(psswd);
        loginPage.getAutClick().click();
    }

    public void addToChart() throws InterruptedException {
        addToChartPage = PageFactory.initElements(driver, AddToChartPage.class);
        createAccount();
        addToChartPage.getLaptopMeniu().click();
        productNameFromList = addToChartPage.getProduct().getAttribute("title");
        addToChartPage.getAddToChart().click();
        productNameFromChart = addToChartPage.getProductInChart().getText();
        Assert.assertEquals("Notebook / Laptop " + productNameFromList, productNameFromChart);
    }

    @Test
    public void removeFromChart() throws InterruptedException {
        removeFromChartPage = PageFactory.initElements(driver, RemoveFromChartPage.class);
        addToChart();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", removeFromChartPage.getRemove());
        Assert.assertTrue(removeFromChartPage.getEmpty().isDisplayed());
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
