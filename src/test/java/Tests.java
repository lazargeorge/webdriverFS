import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests extends baseClass {

	@Test
	/**
	 * Login-Logout Test
	 */
	public static void LogInLogOutTest() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("cata_lucky@yahoo.com", "password");
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Contul meu']")).isDisplayed());
		loginPage.logout();

	}

	@Test
	/**
	 * Click on Autentificare, no mail, no pass
	 */
	public static void LoginNoMailNoPass() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("", "");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
	}

	@Test
	/**
	 * Click on Autentificare, no pass
	 */
	public static void LoginNoPass() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("cata_lucky@yahoo.com", "");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
	}

	@Test
	/**
	 * Click on Autentificare, Enter mail with 2x@@
	 */
	public static void LoginMailDouble() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("cata_lucky@@yahoo.com", "password");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
	}

	@Test
	/**
	 * Click on Autentificare, Enter mail symbol
	 */
	public static void LoginMailSymbol() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("cata_lu#cky@yahoo.com", "password");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='messageStackError']")).getText(),
				" Eroare: Nu s-a gasit nici o inregistrare pentru adresa E-Mail si/sau Parola introduse.");
	}

	@Test
	/**
	 * Create Account Test
	 */
	public static void CreateAccountTest() {
		CreateAccountPage createAcc = PageFactory.initElements(driver, CreateAccountPage.class);
		createAcc.createAccount();
		Assert.assertEquals(driver.findElement(By.xpath("//td[@class='pageHeading']")).getText(),
				"Contul tau a fost creat !");
	}

	@Test
	/**
	 * Create Specific Account Test
	 */
	public static void CreateSpecificAccountTest() {
		CreateAccountPage createAcc = PageFactory.initElements(driver, CreateAccountPage.class);
		baseClass baseclass = PageFactory.initElements(driver, baseClass.class);
		createAcc.createAccount("Numdea", "Prednumee", "sdfgsdg@asdsgsdg.com", "asdadsd", "0742587887",
				"sdjlsdfgsdfgdfdfg", "Botosani", "Botosani");
		Assert.assertEquals(baseclass.elementExist("//h2[text()='Contul tau a fost creat !']"), true, "verificarcreareaccont");
	}

	@Test
	/**
	 * Login, Add Cart, Rem Cart, Logout
	 */
	public static void LoginAddCart() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("cata_lucky@yahoo.com", "password");
		CartPage addProd = PageFactory.initElements(driver, CartPage.class);
		addProd.addCart();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='ecqcosb']")).getText(), "1", "verificaaddelement");
		
		addProd.remCart();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='ecqcosb']")).getText(), "0", "verificaremelement");
		
		loginPage.logout();
	}
}
