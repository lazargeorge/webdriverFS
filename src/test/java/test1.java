import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 */
public class test1 {
	
	@Test
	public void clickLogin() {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.pcgarage.ro/");
		WebElement login=driver.findElement(By.xpath("//*[@id='user_header']//a[text()=\"Contul meu\"]"));
		login.click();
	}
}
