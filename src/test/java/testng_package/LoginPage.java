package testng_package;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	@FindBy(how = How.ID, using = "logins_ident")
	WebElement user;
	@FindBy(how = How.ID, using = "logins_password")
	WebElement password;
	@FindBy(how = How.NAME, using = "btn_submit")
	WebElement submit;

	By msg_invalid_username = By.id("msg_invalid_username");
	By msg_logins_password = By.id("msg_logins_password");
	By msg_logins_ident = By.id("msg_logins_ident");
	By contul_meu = By.xpath(".//a[@title='Contul meu']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://www.karte.ro/autentificare");
	}

	public void log_in() {
		user.sendKeys("Marcel12");
		password.clear();
		password.sendKeys("marcel12");
		submit.click();
	}

	public void log_in(String user_string, String password_string) {
		user.sendKeys(user_string);
		password.sendKeys(password_string);
		submit.click();
	}

}
