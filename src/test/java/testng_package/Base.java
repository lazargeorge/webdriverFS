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

		initDriver(browser, platform, grid);
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

	public void initDriver(String browser, String platform, String grid) throws MalformedURLException {
		switch (grid) {
		case "false":
			if (browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}
			break;

		case "true":
			if (browser.equalsIgnoreCase("firefox")) {
				capabilities = DesiredCapabilities.firefox();
				if (platform.equalsIgnoreCase("Windows"))
					capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				capabilities.setBrowserName("firefox");
			} else {

				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				capabilities = DesiredCapabilities.chrome();
				if (platform.equalsIgnoreCase("Windows"))
					capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				capabilities.setBrowserName("chrome");
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			break;
		default:
			break;
		}
		driver.manage().window().maximize();

	}

}
