import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests {

	@Test
	/**
	 * Login-Logout Test
	 */
	public static void LogInLogOutTest() {
		WebDriver driver = new FirefoxDriver();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("cata_lucky@yahoo.com", "password");
		loginPage.logout();
		driver.close();
	}

	@Test
	/**
	 * Click on Autentificare, no mail, no pass
	 */
	public static void LoginNoMailNoPass() {
		WebDriver driver = new FirefoxDriver();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("", "");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
		driver.close();
	}

	@Test
	/**
	 * Click on Autentificare, no pass
	 */
	public static void LoginNoPass() {
		WebDriver driver = new FirefoxDriver();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("cata_lucky@yahoo.com", "");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
		driver.close();
	}

	@Test
	/**
	 * Click on Autentificare, Enter mail with 2x@@
	 */
	public static void LoginMailDouble() {
		WebDriver driver = new FirefoxDriver();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("cata_lucky@@yahoo.com", "password");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
		driver.close();
	}

	@Test
	/**
	 * Click on Autentificare, Enter mail symbol
	 */
	public static void LoginMailSymbol() {
		WebDriver driver = new FirefoxDriver();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("cata_lu#cky@yahoo.com", "password");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
		driver.close();
	}

	@Test
	/**
	 * Create Account Test
	 */
	public static void CreateAccountTest() {
		WebDriver driver = new FirefoxDriver();
		CreateAccountPage createAcc = PageFactory.initElements(driver, CreateAccountPage.class);
		createAcc.createAccount();
		driver.close();
	}

	@Test
	/**
	 * Login, Add Cart, Rem Cart, Logout
	 */
	public static void LoginAddCart() {
		WebDriver driver = new FirefoxDriver();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("cata_lucky@yahoo.com", "password");
		CartPage addProd = PageFactory.initElements(driver, CartPage.class);
		addProd.addCart();
		addProd.remCart();
		loginPage.logout();
		driver.close();
	}
}
