import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage extends LoadableComponent<CreateAccountPage>
{

    WebDriver driver;
    private WebDriverWait wait;
    
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
        this.driver =driver;
        PageFactory.initElements(driver, this); 
        wait = new WebDriverWait(driver, 10);
        
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

    @Override
    protected void load()
    {
        driver.get("https://www.emag.ro/user/register?ref=ssi_login");
        
    }

    @Override
    protected void isLoaded() throws Error
    {
        wait.until(ExpectedConditions.visibilityOf(submitBtn));
        
    }

}
