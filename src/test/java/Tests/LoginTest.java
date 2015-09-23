package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class LoginTest
{

    /**
     * This is a simple login test
     */

    @Test
    public void Login() throws Exception
    {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.evomag.ro/");
        driver.findElement(By.cssSelector("li>a[href*=auth]")).click();
        driver.findElement(By.id("LoginClientForm_Email")).sendKeys("silviu.moraru@yahoo.com");
        driver.findElement(By.id("LoginClientForm_Password")).sendKeys("testwd123");
        driver.findElement(By.name("yt1")).click();

        WebElement sel_cont = driver.findElement(By.cssSelector(".c_header em"));
        String nume_cont = sel_cont.getText();
        Assert.assertEquals("Silviu M.", nume_cont);

    }
}
