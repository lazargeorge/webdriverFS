package testng_package;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NoGridDriver implements IDriver {
	WebDriver driver;
	String browser,platform, grid;
	
	public WebDriver GetDriver(String browser, String platform, String grid) throws MalformedURLException
	{
		switch(browser)
		{
			case "firefox":
				return getFirefox(platform,grid);
				
			case "chrome":
				return getChrome(platform,grid);
			default:
				return getFirefox(platform,grid);
			
		}
	}
	
	public WebDriver getFirefox(String platform, String grid) {
		driver = new FirefoxDriver();
		return driver;
	}


	public WebDriver getChrome( String platform, String grid) {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}

}
