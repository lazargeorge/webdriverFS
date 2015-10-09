import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreateAccountPage
{

    WebDriver driver;
    
    @FindBy(how = How.ID, using = "r_name")
    WebElement name;
    @FindBy(how = How.ID, using = "r_email")
    WebElement emailElement;
    @FindBy(how = How.ID, using = "r_password")
    WebElement passElement;
    @FindBy(how = How.ID, using = "rc_password")
    WebElement rcpass;
    @FindBy(how = How.ID, using = "ctrigger")
    WebElement acceptTerms;
    @FindBy(how = How.XPATH, using = "//input[@type=\"image\"]")
    WebElement submitBtn;

    public CreateAccountPage(WebDriver driver)
    {
        this.driver = driver;
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
//    public void createAccount()
//    {
//        name.sendKeys("asdf guy");
//        emailElement.sendKeys(rndEmail);
//        passElement.sendKeys(rndPass);
//        rcpass.sendKeys(rndPass);
//        acceptTerms.click();
//        submitBtn.click();
//    }
}
