import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 */
public class test1 {

	private WebDriver chromeDriver;
	private WebDriver firefoxDriver = new FirefoxDriver();

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\V3790119\\Downloads\\chromedriver_win32\\chromedriver.exe");
		chromeDriver = new ChromeDriver();
	}

	@Test
	public void clickLoginFirefox() {
		firefoxDriver.get("http://www.pcgarage.ro/");
		// autButton = button to authentification page
		WebElement autButton = firefoxDriver.findElement(By
				.xpath("//*[@id='user_header']//a[text()=\"Contul meu\"]"));
		autButton.click();
	}

	@Test
	public void clickLoginChrome() {
		chromeDriver.get("http://www.pcgarage.ro/");
		// autButton = button to authentification page
		WebElement autButton = chromeDriver.findElement(By
				.xpath("//*[@id='user_header']//a[text()=\"Contul meu\"]"));
		autButton.click();
	}

	@Test
	public void loginChrome() throws InterruptedException {
		clickLoginChrome();
		Thread.sleep(1000);
		WebElement emailInput = chromeDriver.findElement(By
				.xpath("//*[@id='email']"));
		emailInput.sendKeys("sirbucristina.is@gmail.com");
		WebElement psswdInput = chromeDriver.findElement(By
				.xpath("//*[@id='password']"));
		psswdInput.sendKeys("password");
	}
}
