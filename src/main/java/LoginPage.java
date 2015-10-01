import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement psswdInput;

    @FindBy(xpath = "//*[@id='login']//button[text()=\"Autentificare\"]")
    private WebElement autClick;

    private WebDriver driver;
    private HomePage homePage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
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

    public void login(String email, String psswd) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickLogin();
        getEmailInput().sendKeys(email);
        getPsswdInput().sendKeys(psswd);
        getAutClick().click();
    }
}
