import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 */
public class test1
{

    @Test
    public static void method()
    {
        WebDriver fireFox = new FirefoxDriver();

        fireFox.get("http://www.emag.ro");

        // WebDriver chrome = new ChromeDriver();
        // chrome.get("http://www.emag.ro");
        
        fireFox.findElement(By.xpath("//a[@class=\"back-button\"]")).click();
        fireFox.findElement(By.xpath("//figure[@id=\"emg-user-menu\"]/figcaption")).click();
        WebElement email=fireFox.findElement(By.xpath("//input[@id=\"r_email\"]"));
        
        email.sendKeys("awsomeesttraining@cool.com");
        
        WebElement pass=fireFox.findElement(By.xpath("//input[@id=\"r_password\"]"));
        
        pass.sendKeys("randomrandompassword");
        
        
        
        fireFox.findElement(By.xpath("//input[@type=\"image\"]")).click();
        
       new WebDriverWait(fireFox, 1000);
       fireFox.quit();
    }
}
