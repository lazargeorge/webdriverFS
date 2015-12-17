package testng_package;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Base {

	public static WebDriver driver;
	public static RemoteWebDriver remoteWD;
	public static EventFiringWebDriver efdriver;
	public OverrideClass oc;
	public DesiredCapabilities capabilities;

	@Parameters({ "browser", "platform", "grid" })
	@BeforeMethod
	public void setup(@Optional("Firefox") String browser, @Optional("Windows") String platform,
			@Optional("false") String grid) throws MalformedURLException {

		if (grid.contentEquals("false")) {
			if (browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}
		} else {
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
		}

		driver.manage().window().maximize();
		efdriver = new EventFiringWebDriver(driver);
		oc = new OverrideClass();
		efdriver.register(oc);
	}

	@AfterMethod
	public void tearDown() {
		efdriver.quit(); // afterSuite nu imi inchide toate browserele, idk why
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
