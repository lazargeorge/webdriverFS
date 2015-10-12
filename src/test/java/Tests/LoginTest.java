package Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;

/**
 * @author Silviu Moraru
 */

public class LoginTest extends Setup
{
    /**
     * This is a simple login test
     * Step1: Go to the Login section of the website
     * Step2: enter the username and the password
     * Step3: Check that the login was succesfull
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
