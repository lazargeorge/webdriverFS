import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class GetProperties {

    protected WebDriver driver;

    private static Properties prop = new Properties();

    private void initProp() throws Exception {
        String propFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader()
            .getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("Property file '" + propFileName
                + "' not found!");
        }
    }

    private WebDriver getBrowser() throws Exception {
        initProp();
        String browser = prop.getProperty("browser");
        switch (browser) {
            case "FireFox":
                return new FirefoxDriver();
            case "Chrome":
                System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\V3790119\\Downloads\\chromedriver_win32\\chromedriver.exe");
                return new ChromeDriver();
            default:
                return new FirefoxDriver();
        }

    }

    @BeforeMethod
    protected void setup() throws Exception {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        // capability.setPlatform(Platform.XP);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        // driver = getBrowser();
        driver.get("http://www.pcgarage.ro/");
    }

    @AfterMethod
    protected void closeFirefox(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE)
        {
            System.out.println(testResult.getStatus());
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("MMddhhmmss");
            String dateAsString = simpleDateFormat.format(new Date());
            System.out.println(dateAsString);
            FileUtils
                .copyFile(scrFile, new File(".\\Screenshots\\testScreenShot" + dateAsString + ".jpg"));
        }
        driver.close();
    }

}
