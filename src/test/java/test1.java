import java.util.Random;

import junit.framework.Assert;

import org.testng.annotations.Test;

/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 */
public class test1 extends GetProperties {

    private HomePage homePage;

    private LoginPage loginPage;

    private CreateAccountPage createAccountPage;

    private String email;

    private String psswd;

    @Test
    public void login() throws InterruptedException {
        homePage = new HomePage(driver).get();
        homePage.clickLogin();
        loginPage = new LoginPage(driver).get();
        email = "7k5t07k6imjrgjvefsk1a9nr5v@trbvm.com";
        psswd = "m6sp2csbin8am217h1p7ru2kq2";
        Assert.assertTrue(loginPage.getEmailInput().isDisplayed());
        Assert.assertTrue(loginPage.getPsswdInput().isDisplayed());
        Assert.assertEquals("Autentificare", loginPage.getAutClick().getText());
        loginPage.login(email, psswd);
        Assert.assertEquals("Salut d8v7rntmdri5k8igd7a8u0kg8d!", loginPage.getHello().getText());
    }

    @Test
    public void createAccountParams() {
        Random randomGenerator = new Random();
        homePage = new HomePage(driver).get();
        homePage.clickLogin();
        createAccountPage = new CreateAccountPage(driver).get();
        String prenume = createAccountPage.randomString();
        String nume = createAccountPage.randomString();
        String telefon = "07" + randomGenerator.nextInt(100000000);
        String email = createAccountPage.randomString() + "@trbvm.com";
        String psswd = createAccountPage.randomString();
        createAccountPage.createAccount(prenume, nume, telefon, email, psswd);
        Assert.assertTrue("Account not created", createAccountPage.getAccountCreated().isDisplayed());
    }

//     @Test
//     public void testScreenshot() {
//     Assert.assertTrue(false);
//     }

}
