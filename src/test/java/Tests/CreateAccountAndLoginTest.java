package Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class CreateAccountAndLoginTest extends Setup
{
    WebDriver driver = new FirefoxDriver();
    int random = 100 + (int) (Math.random() * ((10000 - 100) + 1));

    /**
     * Test for creating an account
     * Step 1: Click on "Contul meu"
     * Step 2: Enter new account details
     * Step 3: Check the login by asserting page element (Contul meu -> name of the account)
     */

    @Test
    public void CreateAccountTest() throws Exception
    {
        driver.get("http://www.evomag.ro");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String nume_generat = "Sandel" + random;
        HomePage.link_ContulMeu(driver).click();
        LoginPage.name(driver).sendKeys(nume_generat);
        LoginPage.email_create(driver).sendKeys(nume_generat + "@test.com");
        LoginPage.password_create(driver).sendKeys("testtesttest");
        LoginPage.register_button(driver).click();

        Assert.assertEquals(nume_generat, HomePage.nume_ContulMeu(driver).getText());

    }

    /**
     * Test for checking the fields have restrictions for min caracters and email formatting
     * Step1 : Click on Create account and enter 4 characters in Nume field (min of 5)
     * Step 2: Click on Create account and enter 4 characters in E-mail field (min: |1 char| "@" |1 char|"."|3 char|)
     * Step 3: Click on Create account and enter 4 characters in Parola field (min of 5)
     * Step 4: Check that an alert pops up with a line of text for each error
     */

    @Test
    public void NegativeCreateAccountTest() throws Exception
    {
        driver.get("http://www.evomag.ro");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        HomePage.link_ContulMeu(driver).click();
        LoginPage.name(driver).sendKeys("1324");
        LoginPage.email_create(driver).sendKeys("1234");
        LoginPage.password_create(driver).sendKeys("1234");
        LoginPage.register_button(driver).click();

        String alert_text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals("Numele trebuie sa contina minim 5 caractere!\nAdresa de e-mail invalida!\nParola trebuie sa contina minim 5 caractere.",
                alert_text);
    }

}
