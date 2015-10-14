import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends LoadableComponent<LoginPage>{

    WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(how = How.ID, using = "r_email")
    WebElement loginEmail;
    @FindBy(how = How.ID, using = "r_password")
    WebElement loginPass;
    @FindBy(how = How.XPATH, using = "//input[@type=\"image\"]")
    WebElement loginSubmit;

    
    
    public LoginPage(WebDriver driver)
    {
        this.driver =driver; 
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        driver.get("http://www.emag.ro/user/login");
    }

    public void login(String username, String pass)
    {
        loginEmail.sendKeys(username);
        loginPass.sendKeys(pass);
        loginSubmit.click();
    }

    @Override
    protected void load()
    {
        driver.get("http://www.emag.ro/user/login");
    }

    @Override
    protected void isLoaded() throws Error
    {
        wait.until(ExpectedConditions.visibilityOf(loginEmail));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        
    }
}
