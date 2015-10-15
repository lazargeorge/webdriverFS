import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class baseClass {

	static WebDriver driver;

	public boolean elementExist(String id) {
		try {
			driver.findElement(By.xpath(id));

		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	@BeforeMethod
	public void startFirefox() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.oktal.ro");
	}

	@AfterMethod
	public void closeFirefox(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			System.out.println(testResult.getStatus());
            File printScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(printScreen, new File("D:\\webdriverFS\\src\\test\\java\\ScreenShots\\printscreen.jpg"));
            System.out.println(printScreen.getAbsolutePath());
		}
		driver.close();
	}

}
