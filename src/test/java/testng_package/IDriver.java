package testng_package;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

public interface IDriver {
	public WebDriver getFirefox(String platform, String grid) throws MalformedURLException;
	public WebDriver getChrome(String platform, String grid) throws MalformedURLException;
	public WebDriver GetDriver(String browser, String platform, String grid) throws MalformedURLException;

}
