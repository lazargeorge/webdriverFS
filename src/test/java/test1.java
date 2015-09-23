
/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 * Modified by Silviu Moraru
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class test1
{

    public static void main(String[] args)
    {

        WebDriver driver = new FirefoxDriver();

        driver.get("http://www.evomag.ro/");
        
/*
* Go to login page
*/
        WebElement Login = driver.findElement(By.cssSelector("li>a[href*=auth]"));
        Login.click();

/*
* Enter account+pwd and click login
*/
        
        driver.findElement(By.id("LoginClientForm_Email")).sendKeys("silviu.moraru@yahoo.com");
        driver.findElement(By.id("LoginClientForm_Password")).sendKeys("testwd123");
        driver.findElement(By.name("yt1")).click();

    }
}
