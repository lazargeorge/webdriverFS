package testng_package;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridDriver implements IDriver {
	WebDriver driver;
	String browser,platform, grid;
	public DesiredCapabilities capabilities;
	
	public WebDriver GetDriver(String browser, String platform, String grid) throws MalformedURLException
	{
		switch(browser)
		{
			case "firefox":
				return getFirefox(platform,grid);
				
			case "chrome":
				return getChrome(platform,grid);
			default:
				return null;
			
		}
	}

	public WebDriver getFirefox(String platform, String grid) throws MalformedURLException {
		capabilities = DesiredCapabilities.firefox();
		if (platform.equalsIgnoreCase("Windows"))
			capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		capabilities.setBrowserName("firefox");
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		return driver;
	}


	public WebDriver getChrome(String platform, String grid) throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		capabilities = DesiredCapabilities.chrome();
		if (platform.equalsIgnoreCase("Windows"))
			capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		capabilities.setBrowserName("chrome");
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		return driver;
	}

}
