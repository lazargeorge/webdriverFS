package testng_package;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RunMe {

	@Test
	public void start(){
		
	}
	
	@Test
	public void googleSearch()
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
	
		driver.get("http://google.com");
		driver.quit();
	}
	
}
