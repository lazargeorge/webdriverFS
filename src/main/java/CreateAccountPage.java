import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {

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

    private SecureRandom random = new SecureRandom();

    private AddToChartPage addToChartPage;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
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
        Assert.assertTrue("Account not created", getAccountCreated().isDisplayed());
        addToChartPage = PageFactory.initElements(driver, AddToChartPage.class);
        addToChartPage.addToChart();
    }

    public String randomString() {
        return new BigInteger(130, random).toString(32);
    }

}
