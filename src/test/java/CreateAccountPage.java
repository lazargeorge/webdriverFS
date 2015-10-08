import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateAccountPage extends Base
{

    @FindBy(how = How.ID, id = "r_name")
    WebElement name;
    @FindBy(how = How.ID, id = "r_email")
    WebElement emailElement;
    @FindBy(how = How.ID, id = "r_password")
    WebElement passElement;
    @FindBy(how = How.ID, id = "rc_password")
    WebElement rcpass;
    @FindBy(how = How.ID, id = "ctrigger")
    WebElement acceptTerms;
    @FindBy(how = How.XPATH, xpath = "//input[@type=\"image\"]")
    WebElement submitBtn;

    public CreateAccountPage()
    {
        driver.get("https://www.emag.ro/user/register?ref=ssi_login");
    }

    /**
     * Create account
     */
    public void createAccount(String username, String email, String pass)
    {
        name.sendKeys(username);
        emailElement.sendKeys(email);
        passElement.sendKeys(pass);
        rcpass.sendKeys(pass);
        acceptTerms.click();
        submitBtn.click();
    }

    /**
     * Create random account
     */
    public void createAccount()
    {
        name.sendKeys("asdf guy");
        emailElement.sendKeys(rndEmail);
        passElement.sendKeys(rndPass);
        rcpass.sendKeys(rndPass);
        acceptTerms.click();
        submitBtn.click();
    }
}
