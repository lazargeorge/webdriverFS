import org.openqa.selenium.WebDriver;
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

    private String email;

    private String psswd;

    private GetProperties prop = new GetProperties();

    @BeforeMethod
    public void setUp() throws Exception {
        driver = prop.getBrowser();
        driver.get("http://www.pcgarage.ro/");
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @Test
    public void login() throws InterruptedException {
        homePage.clickLogin();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        email = "7k5t07k6imjrgjvefsk1a9nr5v@trbvm.com";
        psswd = "m6sp2csbin8am217h1p7ru2kq2";
        loginPage.login(email, psswd);
    }

    @Test
    public void fullPath() throws InterruptedException {
        homePage.clickLogin();
        createAccountPage = PageFactory.initElements(driver, CreateAccountPage.class);
        createAccountPage.createAccount();
    }

    @AfterMethod
    private void closeFirefox() {
        driver.close();
    }

}
