import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPage extends PageObject {
    @FindBy(id = "newfirstname")
    private WebElement newPrenumeInput;

    @FindBy(id="newlastname")
    private WebElement newNumeInput;

    @FindBy(id="telephone")
    private WebElement newTelefonInput;

    @FindBy(id="newemail")
    private WebElement newEmailInput;

    @FindBy(id="newpassword")
    private WebElement newParolaInput;

    @FindBy(id="newpasswordretype")
    private WebElement newParola2Input;

    @FindBy(xpath = "//*[@id='register']//button")
    private WebElement createBtn;

    @FindBy(xpath = "//div[@id='listing-right']//h1")
    WebElement accountCreated;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
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

}
