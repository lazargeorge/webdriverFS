import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CreateAccountPage extends LoadableComponent<CreateAccountPage> {

    @FindBy(id = "newfirstname")
    private WebElement newPrenumeInput;

    @FindBy(id = "newlastname")
    private WebElement newNumeInput;

    @FindBy(id = "telephone")
    private WebElement newTelefonInput;

    @FindBy(id = "newemail")
    private WebElement newEmailInput;

    @FindBy(id = "newpassword")
    private WebElement newParolaInput;

    @FindBy(id = "newpasswordretype")
    private WebElement newParola2Input;

    @FindBy(xpath = "//*[@id='register']//button")
    private WebElement createBtn;

    @FindBy(xpath = "//div[@id='listing-right']//h1")
    WebElement accountCreated;

    private WebDriver driver;

    private WebDriverWait wait;

    private SecureRandom random = new SecureRandom();

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public WebElement getNewPrenumeInput() {
        return newPrenumeInput;
    }

    public WebElement getNewNumeInput() {
        return newNumeInput;
    }

    public WebElement getNewTelefonInput() {
        return newTelefonInput;
    }

    public WebElement getNewEmailInput() {
        return newEmailInput;
    }

    public WebElement getNewParolaInput() {
        return newParolaInput;
    }

    public WebElement getNewParola2Input() {
        return newParola2Input;
    }

    public WebElement getCreateBtn() {
        return createBtn;
    }

    public WebElement getAccountCreated() {
        return accountCreated;
    }

    public void createAccount() throws InterruptedException {
        Random randomGenerator = new Random();
        getNewPrenumeInput().sendKeys(randomString());
        getNewNumeInput().sendKeys(randomString());
        getNewTelefonInput().sendKeys("07" + randomGenerator.nextInt(100000000));
        String email = randomString() + "@trbvm.com";
        getNewEmailInput().sendKeys(email);
        String psswd = randomString();
        getNewParolaInput().sendKeys(psswd);
        getNewParola2Input().sendKeys(psswd);
        driver.manage().window().maximize();
        getCreateBtn().click();
        // System.out.println("Email: " + email);
        // System.out.println("Password: " + psswd);
    }

    public void createAccount(String prenume, String nume, String telefon, String email, String psswd) {
        getNewPrenumeInput().sendKeys(prenume);
        getNewNumeInput().sendKeys(nume);
        getNewTelefonInput().sendKeys(telefon);
        getNewEmailInput().sendKeys(email);
        getNewParolaInput().sendKeys(psswd);
        getNewParola2Input().sendKeys(psswd);
        driver.manage().window().maximize();
        getCreateBtn().click();
    }

    public String randomString() {
        return new BigInteger(130, random).toString(32);
    }

    @Override
    protected void load() {
        driver.get("https://www.pcgarage.ro/autentificare/");

    }

    @Override
    protected void isLoaded() throws Error {
        wait.until(ExpectedConditions.visibilityOf(getNewPrenumeInput()));
        System.out.println("Create account: prenume is displayed? " + getNewPrenumeInput().isDisplayed());
        Assert.assertTrue(getNewPrenumeInput().isDisplayed());
        System.out.println("Create account: nume is displayed? " + getNewNumeInput().isDisplayed());
        Assert.assertTrue(getNewNumeInput().isDisplayed());
        System.out.println("Create account: email is displayed? " + getNewEmailInput().isDisplayed());
        Assert.assertTrue(getNewEmailInput().isDisplayed());
    }

}
