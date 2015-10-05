package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginTest extends Setup
{
    WebDriver driver = new FirefoxDriver();

    /**
     * This is a simple login test
     */

    @Test
    public void Login() throws Exception
    {
        driver.get("http://www.evomag.ro/client/auth");
        LoginPage.email_login(driver).sendKeys("silviu.moraru@yahoo.com");
        LoginPage.password_login(driver).sendKeys("testwd123");
        LoginPage.login_button(driver).click();

        Assert.assertEquals("Silviu M.", HomePage.nume_ContulMeu(driver).getText());

    }
}
