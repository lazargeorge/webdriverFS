import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {
    @FindBy(id="email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement psswdInput;

    @FindBy(xpath = "//*[@id='login']//button[text()=\"Autentificare\"]")
    private WebElement autClick;

    public LoginPage(WebDriver driver) {
        super(driver);
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

}
