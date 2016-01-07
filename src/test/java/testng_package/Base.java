package testng_package;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Base {

	public static WebDriver driver;
	public static RemoteWebDriver remoteWD;
	public static EventFiringWebDriver efdriver;
	public OverrideClass oc;
	public DesiredCapabilities capabilities;

	@Parameters({ "browser", "platform", "grid" })
	@BeforeTest
	public void setup(@Optional("Firefox") String browser, @Optional("Windows") String platform,
			@Optional("false") String grid) throws MalformedURLException {

		IDriver idriver;
		switch (grid) {
		case "false":
			idriver = new NoGridDriver();
			driver = idriver.GetDriver(browser, platform, grid);
			break;
		case "true":
			idriver = new GridDriver();
			driver = idriver.GetDriver(browser, platform, grid);
			break;
		default:
			break;
		}

		driver.manage().window().maximize();
		efdriver = new EventFiringWebDriver(driver);
		oc = new OverrideClass();
		efdriver.register(oc);
	}

	@AfterTest
	public void tearDown() {
		efdriver.quit();
	}

	public WebElement waitForElement(By element) {
		WebDriverWait waiter = new WebDriverWait(efdriver, 2);
		waiter.until(ExpectedConditions.presenceOfElementLocated(element));
		return efdriver.findElement(element);
	}

	public static WebDriver getDriver() {
		return efdriver;
	}
}