package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
    static WebDriver driver;

    @FindBy(id = "RegisterClientForm_FullName")
    WebElement name;

    @FindBy(id = "RegisterClientForm_Email")
    WebElement email_create;

    @FindBy(id = "LoginClientForm_Email")
    WebElement email_login;

    @FindBy(id = "RegisterClientForm_Password")
    WebElement password_create;

    @FindBy(id = "LoginClientForm_Password")
    WebElement password_login;

    @FindBy(name = "yt0")
    WebElement register_button;

    @FindBy(name = "yt1")
    WebElement login_button;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        driver.get("http://www.evomag.ro/client/auth");
    }

    public void Login(String email, String password)
    {
        driver.get("http://www.evomag.ro/client/auth");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        email_login.sendKeys(email);
        password_login.sendKeys(password);
        login_button.click();
    }

    public void Create_Account_Valid(String password)
    {
        int random = 100 + (int) (Math.random() * ((10000 - 100) + 1));
        String nume_generat = "Sandel" + random;
        name.sendKeys(nume_generat);
        email_create.sendKeys(nume_generat + "@test.com");
        password_create.sendKeys(password);
        register_button.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
 
    }

    public void Create_Account_Invalid(String name_inv, String email_inv, String password_inv)
    {
        name.sendKeys(name_inv);
        email_create.sendKeys(email_inv);
        password_create.sendKeys(password_inv);
        register_button.click();
    }
    
    public static void Logout()
    {
       driver.get("http://www.evomag.ro/client/logout");
    }

}
