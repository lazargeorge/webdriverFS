import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 */

public class test1 {

	public WebDriver ff;

	public boolean elementExist(String id) {
		try {
			ff.findElement(By.xpath(id));

		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	@BeforeTest
	/**
	 * Open FireFox
	 */
	public void startFirefox() {
		ff = new FirefoxDriver();
		ff.get("http://www.oktal.ro/");
	}

	@Test
	/**
	 * Click on Autentificare, no mail, no pass
	 */
	public void testLoginNoInfo() {
		WebElement login = ff.findElement(By.xpath("//span[@id='autentificare_tttip']/a"));
		login.click();
		WebElement ClickAuth = ff.findElement(By.xpath("//input[@title=' Autentifica-te ']"));
		ClickAuth.click();
		Assert.assertEquals(ff.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
	}
	
	@Test
	/**
	 * Click on Autentificare, Enter mail, no pass
	 */
	public void testLoginNoPass() {
		WebElement login = ff.findElement(By.xpath("//span[@id='autentificare_tttip']/a"));
		login.click();
		WebElement EnterEmail = ff.findElement(By.id("email_address"));
		EnterEmail.sendKeys("cata_lucky@yahoo.com");
		WebElement ClickAuth = ff.findElement(By.xpath("//input[@title=' Autentifica-te ']"));
		ClickAuth.click();
		Assert.assertEquals(ff.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
	}
	
	@Test
	/**
	 * Click on Autentificare, Enter mail with 2x@@
	 */
	public void testLoginDouble() {
		WebElement login = ff.findElement(By.xpath("//span[@id='autentificare_tttip']/a"));
		login.click();
		WebElement EnterEmail = ff.findElement(By.id("email_address"));
		EnterEmail.sendKeys("cata_lucky@@yahoo.com");
		WebElement EnterPassword = ff.findElement(By.name("passwordx"));
		EnterPassword.sendKeys("password");
		WebElement ClickAuth = ff.findElement(By.xpath("//input[@title=' Autentifica-te ']"));
		ClickAuth.click();
		Assert.assertEquals(ff.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
	}
	
	@Test
	/**
	 * Click on Autentificare, Enter mail symbol
	 */
	public void testLoginUserWithSymbol() {
		WebElement login = ff.findElement(By.xpath("//span[@id='autentificare_tttip']/a"));
		login.click();
		WebElement EnterEmail = ff.findElement(By.id("email_address"));
		EnterEmail.sendKeys("cata_l#ucky@yahoo.com");
		WebElement EnterPassword = ff.findElement(By.name("passwordx"));
		EnterPassword.sendKeys("password");
		WebElement ClickAuth = ff.findElement(By.xpath("//input[@title=' Autentifica-te ']"));
		ClickAuth.click();
		Assert.assertEquals(ff.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
	}

	@Test
	/**
	 * Click on Autentificare, Enter user/pass, Add 1st Element to cart, Remove
	 * from cart, Click on Deconectare
	 */
	public void testLoginAddCartDisconnect() throws Exception {

		WebElement login = ff.findElement(By.xpath("//span[@id='autentificare_tttip']/a"));
		login.click();
		WebElement EnterEmail = ff.findElement(By.id("email_address"));
		EnterEmail.sendKeys("cata_lucky@yahoo.com");
		WebElement EnterPassword = ff.findElement(By.name("passwordx"));
		EnterPassword.sendKeys("password");
		WebElement ClickAuth = ff.findElement(By.xpath("//input[@title=' Autentifica-te ']"));
		ClickAuth.click();
		WebElement Home = ff.findElement(By.xpath("//a[@href='http://www.oktal.ro']/img"));
		Home.click();
		Thread.sleep(3000);

		Assert.assertTrue(ff.findElement(By.xpath("//a[text()='Contul meu']")).isDisplayed());
		Assert.assertTrue(ff.findElement(By.xpath("//a[text()='Deconectare']")).isDisplayed());

		WebElement SelectProd = ff.findElement(By.xpath("//div[@class='prima_poza']/a"));
		SelectProd.click();
		WebElement AddCart = ff.findElement(By.xpath("//input[@class='buton_comanda']"));
		AddCart.click();
		Assert.assertEquals(ff.findElement(By.xpath("//div[@id='ecqcosb']")).getText(), "1", "verificaaddelement");

		WebElement RemoveProd = ff.findElement(By.xpath("//a[@title='sterge din cos']/b"));
		RemoveProd.click();
		Assert.assertEquals(ff.findElement(By.xpath("//div[@id='ecqcosb']")).getText(), "0", "verificaremelement");

		WebElement Deconectare = ff.findElement(By.linkText("Deconectare"));
		Deconectare.click();
	}

	public String genID() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	@Test
	public void testNewUser() {
		/**
		 * Create New User
		 **/

		String pwd = genID();
		String mail = genID();
		Random nr = new Random();

		ff.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement login = ff.findElement(By.xpath("//span[@id='autentificare_tttip']/a"));
		login.click();
		WebElement enterNume = ff.findElement(By.xpath("//input[@name='firstname']"));
		enterNume.sendKeys(genID());
		WebElement enterPrenume = ff.findElement(By.xpath("//input[@name='lastname']"));
		enterPrenume.sendKeys(genID());

		WebElement enterEmail = ff.findElement(By.xpath("//input[@name='email_address']"));
		enterEmail.sendKeys(mail + "@yopmail.com");

		WebElement enterPass = ff.findElement(By.xpath("//input[@name='password']"));
		enterPass.sendKeys(pwd);
		WebElement enterConfPass = ff.findElement(By.xpath("//input[@name='confirmation']"));
		enterConfPass.sendKeys(pwd);
		WebElement enterNrTel = ff.findElement(By.xpath("//input[@name='telephone']"));
		enterNrTel.sendKeys("07" + nr.nextInt(9999999) + 1);
		WebElement enterAdresa = ff.findElement(By.xpath("//textarea[@wrap='soft']"));
		enterAdresa.sendKeys(genID());
		Select enterJudet = new Select(ff.findElement(By.xpath("//select[@name='entry_suburb']")));
		enterJudet.selectByValue("Iasi");
		Select enterLocalitate = new Select(ff.findElement(By.xpath("//select[@name='localitati']")));
		enterLocalitate.selectByValue("Iasi");
		WebElement clickCreazaCont = ff.findElement(By.xpath("//input[@title=' Creeaza Cont ']"));
		clickCreazaCont.click();
		System.out.println(mail);
		System.out.println(pwd);

		Assert.assertEquals(elementExist("//h2[text()='Contul tau a fost creat !']"), true, "verificarcreareaccont");
	}

	@AfterTest
	/**
	 * Close FireFox
	 */
	public void closeFirefox() {
		ff.close();
	}
}
