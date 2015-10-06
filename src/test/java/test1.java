import java.util.Random;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
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

    private Removed removed;

    private String email;

    private String psswd;

    private GetProperties prop = new GetProperties();

    @BeforeMethod
    public void setUp() throws Exception {
        driver = prop.getBrowser();
        driver.get("http://www.pcgarage.ro/");
    }

    @Test
    public void login() throws InterruptedException {
        homePage = new HomePage(driver).get();
        homePage.clickLogin();
        loginPage = new LoginPage(driver).get();
        email = "7k5t07k6imjrgjvefsk1a9nr5v@trbvm.com";
        psswd = "m6sp2csbin8am217h1p7ru2kq2";
        loginPage.login(email, psswd);
    }

    @Test
    public void fullPath() throws InterruptedException {
        homePage = new HomePage(driver).get();
        homePage.clickLogin();
        createAccountPage = new CreateAccountPage(driver).get();
        createAccountPage.createAccount();
        Assert.assertTrue("Account not created", createAccountPage.getAccountCreated().isDisplayed());
        addToChartPage = new AddToChartPage(driver).get();
        addToChartPage.getLaptopMeniu().click();
        String productNameFromList = addToChartPage.getProduct().getAttribute("title");
        addToChartPage.addToChart();
        String productNameFromChart = addToChartPage.getProductInChart().getText();
        Assert.assertTrue(productNameFromChart.contains(productNameFromList));
        removeFromChartPage = new RemoveFromChartPage(driver).get();
        removeFromChartPage.removeFromChart();
        removed = new Removed(driver).get();
        Assert.assertTrue(removed.getEmpty().isDisplayed());
    }

    @Test
    public void createAccountParams() {
        Random randomGenerator = new Random();
        homePage = new HomePage(driver).get();
        homePage.clickLogin();
        createAccountPage = new CreateAccountPage(driver).get();
        String prenume = createAccountPage.randomString();
        String nume = createAccountPage.randomString();
        String telefon = "07" + randomGenerator.nextInt(100000000);
        String email = createAccountPage.randomString() + "@trbvm.com";
        String psswd = createAccountPage.randomString();
        createAccountPage.createAccount(prenume, nume, telefon, email, psswd);
        Assert.assertTrue("Account not created", createAccountPage.getAccountCreated().isDisplayed());
    }

    @AfterMethod
    private void closeFirefox() {
        driver.close();
    }

}
