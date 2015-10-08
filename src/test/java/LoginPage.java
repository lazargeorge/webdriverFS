import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends Base
{

    @FindBy(how = How.ID, id = "r_email")
    WebElement loginEmail;
    @FindBy(how = How.ID, id = "r_password")
    WebElement loginPass;
    @FindBy(how = How.XPATH, xpath = "//input[@type=\"image\"]")
    WebElement loginSubmit;

    public LoginPage()
    {
        driver.get("http://www.emag.ro/user/login");
    }

    public void login(String username, String pass)
    {
        loginEmail.sendKeys(username);
        loginPass.sendKeys(pass);
        loginSubmit.click();
    }
}
