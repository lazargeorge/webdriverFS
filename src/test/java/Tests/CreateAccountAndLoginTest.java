package Tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.AccountPage;
import pageObjects.LoginPage;

/**
 * @author Silviu Moraru
 */

public class CreateAccountAndLoginTest extends Setup
{

    /**
     * Test for creating an account
     * Step 1: Click on "Contul meu"
     * Step 2: Enter new account details
     * Step 3: Check the login by asserting page element (Contul meu -> name of the account)
     */

    @Test
    public void CreateAccountTest() throws InterruptedException
    {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

        loginPage.Create_Account_Valid("1234567");

        AccountPage accountPage = PageFactory.initElements(driver, AccountPage.class);
        Assert.assertTrue(accountPage.date_personale.isDisplayed());
        Assert.assertTrue(accountPage.nume_ContulMeu.isDisplayed());
        
        loginPage.Logout();
    }

    /**
     * Test for checking the fields have restrictions for min caracters and email formatting
     * Step1 : Click on Create account and enter 4 characters in Nume field (min of 5)
     * Step 2: Click on Create account and enter 4 characters in E-mail field (min: |1 char| "@" |1 char|"."|3 char|)
     * Step 3: Click on Create account and enter 4 characters in Parola field (min of 5)
     * Step 4: Check that an alert pops up with a line of text for each error
     */

    @Test
    public void NegativeCreateAccountTest() throws InterruptedException
    {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

        loginPage.Create_Account_Invalid("abc", "bcom", "123");

        String alert_text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals("Numele trebuie sa contina minim 5 caractere!\nAdresa de e-mail invalida!\nParola trebuie sa contina minim 5 caractere.",
                alert_text);
    }

}
