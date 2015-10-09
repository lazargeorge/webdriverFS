import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;

	@FindBy(id = "email_address")
	WebElement emailInput;

	@FindBy(name = "passwordx")
	WebElement passwordInput;

	@FindBy(xpath = "//input[@title=' Autentifica-te ']")
	WebElement clickAuth;

	@FindBy(linkText = "Deconectare")
	WebElement clickDec;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://www.oktal.ro/index.php?main_page=login");
	}

	public void login(String email, String password) {
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		clickAuth.click();
	}

	public void logout() {
		clickDec.click();
	}
}
