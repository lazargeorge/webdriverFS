import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class GetProperties {
    
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

    public WebDriver getBrowser() throws Exception {
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

}
