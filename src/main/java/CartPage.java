import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage {

	WebDriver driver;

	@FindBy(xpath = "//div[@class='prima_poza']/a")
	WebElement prodSelect;

	@FindBy(xpath = "//input[@class='buton_comanda']")
	WebElement addCart;

	@FindBy(xpath = "//a[@title='sterge din cos']/b")
	WebElement remCart;

	@FindBy(xpath = "//div[@id='ecqcos']")
	WebElement selectCart;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://www.oktal.ro");
	}

	public void addCart() {
		prodSelect.click();
		addCart.click();
	}

	public void remCart() {
		selectCart.click();
		remCart.click();
	}
}
