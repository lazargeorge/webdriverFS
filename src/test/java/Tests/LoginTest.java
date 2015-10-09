package Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginTest extends Setup
{
    /**
     * This is a simple login test
     */

    @Test
    public void Log() throws InterruptedException
    {

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

        loginPage.Login("silviu.moraru@yahoo.com", "testwd123");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        homePage.go_Home(driver);
        Assert.assertEquals("Silviu M.", homePage.getNume_ContulMeu());
    }
}
