import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage {

	WebDriver driver;

	private SecureRandom random = new SecureRandom();

	@FindBy(xpath = "//input[@name='firstname']")
	WebElement inputNume;

	@FindBy(xpath = "//input[@name='lastname']")
	WebElement inputPrenume;

	@FindBy(xpath = "//input[@name='email_address']")
	WebElement inputMail;

	@FindBy(xpath = "//input[@name='password']")
	WebElement inputPassword;

	@FindBy(xpath = "//input[@name='confirmation']")
	WebElement inputPassword2;

	@FindBy(xpath = "//input[@name='telephone']")
	WebElement inputPhone;

	@FindBy(xpath = "//textarea[@wrap='soft']")
	WebElement inputAdress;

	@FindBy(xpath = "//select[@name='entry_suburb']")
	WebElement inputJudet;

	@FindBy(xpath = "//select[@name='localitati']")
	WebElement inputLocalitate;

	@FindBy(xpath = "//input[@title=' Creeaza Cont ']")
	WebElement clickCreate;

	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://www.oktal.ro/index.php?main_page=login");
	}

	public void createAccount() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		inputNume.sendKeys(randomString());
		inputPrenume.sendKeys(randomString());
		String mail = randomString();
		inputMail.sendKeys(mail + "@yopmail.com");
		String pwd = randomString();
		inputPassword.sendKeys(pwd);
		inputPassword2.sendKeys(pwd);
		Random nr = new Random();
		inputPhone.sendKeys("07" + nr.nextInt(99999999) + 1);
		inputAdress.sendKeys(randomString());
		Select selectJudet = new Select(inputJudet);
		selectJudet.selectByValue("Iasi");
		Select selectLocalitate = new Select(inputLocalitate);
		selectLocalitate.selectByValue("Iasi");
		clickCreate.click();

	}

	public void createAccount(String nume, String prenume, String mail, String pwd, String telefon,
			String adresa, String judet, String localitate) {
		inputNume.sendKeys(nume);
		inputPrenume.sendKeys(prenume);
		inputMail.sendKeys(mail);
		inputPassword.sendKeys(pwd);
		inputPassword2.sendKeys(pwd);
		inputPhone.sendKeys(telefon);
		inputAdress.sendKeys(adresa);
		Select selectJudet = new Select(inputJudet);
		selectJudet.selectByValue(judet);
		Select selectLocalitate = new Select(inputLocalitate);
		selectLocalitate.selectByValue(localitate);
		clickCreate.click();

	}

	public String randomString() {
		return new BigInteger(130, random).toString(32);
	}
}
