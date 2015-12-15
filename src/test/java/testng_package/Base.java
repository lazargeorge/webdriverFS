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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Base{

	public WebDriver driver;
	public static RemoteWebDriver remoteWD;
	public static EventFiringWebDriver efdriver;

	@Parameters({"browser","platform"})
	@BeforeMethod
	public void setup(String browser, String platform) throws MalformedURLException {
		 if(browser.equalsIgnoreCase("firefox"))
		 {
			// driver = new FirefoxDriver();
		
		 
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		  if(platform.equalsIgnoreCase("Windows"))
			  capabilities.setPlatform(org.openqa.selenium.Platform.  WINDOWS);
		 
		 capabilities.setBrowserName("firefox");
		
		 remoteWD = new RemoteWebDriver( new URL("http://localhost:4444/wd/hub"), capabilities);
		 }
		
	 if(browser.equalsIgnoreCase("chrome")){
		 System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		// driver = new ChromeDriver();
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		  if(platform.equalsIgnoreCase("Windows"))
			  capabilities.setPlatform(org.openqa.selenium.Platform.  WINDOWS);
			 capabilities.setBrowserName("chrome");
			
			 remoteWD = new RemoteWebDriver( new URL("http://localhost:4444/wd/hub"), capabilities);
		 }
       
		
	
		 remoteWD.manage().window().maximize();
		 efdriver = new EventFiringWebDriver(remoteWD);
		 OverrideClass oc = new OverrideClass();
		 efdriver.register(oc);
		
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) efdriver;
				File source = ts.getScreenshotAs(OutputType.FILE);
			    long time = result.getEndMillis() - result.getStartMillis();
			    String time_string = Long.toString(time);
				FileUtils.copyFile(source, new File("./screenshots/" + result.getName() +"_"+time_string+ ".png"));
				System.out.println("Screenshot taken");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}

		}
	
		efdriver.quit();
		
	}

	public WebElement waitForElement(By element) {
		WebDriverWait waiter = new WebDriverWait(efdriver, 2);
		waiter.until(ExpectedConditions.presenceOfElementLocated(element));
		return efdriver.findElement(element);
	}

}
