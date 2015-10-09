import org.openqa.selenium.WebDriver;

public class HomePage {
	
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://www.oktal.ro");
	}
}
