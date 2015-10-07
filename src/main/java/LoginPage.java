import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends LoadableComponent<LoginPage> {
    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement psswdInput;

    @FindBy(xpath = "//*[@id='login']//button[text()=\"Autentificare\"]")
    private WebElement autClick;
    
    @FindBy(xpath = "//*[@id=\"listing-right\"]/h1")
    private WebElement hello;

    private WebDriver driver;

    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getPsswdInput() {
        return psswdInput;
    }

    public WebElement getAutClick() {
        return autClick;
    }
    
    public WebElement getHello() {
        return hello;
    }

    public void login(String email, String psswd) {
        getEmailInput().sendKeys(email);
        getPsswdInput().sendKeys(psswd);
        getAutClick().click();
    }

    @Override
    protected void load() {
        driver.get("https://www.pcgarage.ro/autentificare/");
    }

    @Override
    protected void isLoaded() throws Error {
        wait.until(ExpectedConditions.visibilityOf(getEmailInput()));
        Assert.assertTrue(getEmailInput().isDisplayed());
    }
}
