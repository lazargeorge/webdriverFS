package testng_package;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {

	WebDriver driver;
	@FindBy(how = How.ID, id = "contacts_name")
	WebElement contacts_name;
	@FindBy(how = How.NAME, name = "logins_password")
	WebElement logins_password;
	@FindBy(how = How.NAME, name = "logins_confirm_password")
	WebElement logins_confirm_password;
	@FindBy(how = How.NAME, name = "logins_email")
	WebElement logins_email;
	@FindBy(how = How.NAME, name = "logins_confirm_email")
	WebElement logins_confirm_email;
	@FindBy(how = How.ID, id = "profile_account_type")
	WebElement acc_type;

	Select acc_select;
	@FindBy(how = How.NAME, name = "profile_cnp")
	WebElement profile_cnp;
	@FindBy(how = How.NAME, name = "profile_fname")
	WebElement profile_fname;
	@FindBy(how = How.NAME, name = "profile_lname")
	WebElement profile_lname;
	@FindBy(how = How.ID, id = "profile_address")
	WebElement profile_address;
	@FindBy(how = How.NAME, name = "profile_phone")
	WebElement profile_phone;
	@FindBy(how = How.NAME, name = "btn_submit")
	WebElement submit;
	WebElement creare_cont;
	
	By msg_logins_confirm_password = By.id("msg_logins_confirm_password");
	By msg_logins_username = By.id("msg_logins_username");
	By iesire_cont =  By.xpath(".//a[@href='http://www.karte.ro/logout?cmd=profile']");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://www.karte.ro/inregistrare");
		acc_type = driver.findElement(By.id("profile_account_type"));
		acc_select = new Select(acc_type);

	}

	public void register(String contacts_name_string, String logins_password_string,
			String logins_confirm_password_string, String logins_email_string, String logins_confirm_email_string,
			String acc_select_string, String profile_cnp_string, String profile_fname_string,
			String profile_lname_string, String profile_address_string, String profile_phone_string) {
		contacts_name.sendKeys(contacts_name_string);
		logins_password.sendKeys(logins_password_string);
		logins_confirm_password.sendKeys(logins_confirm_password_string);
		logins_email.sendKeys(logins_email_string);
		logins_confirm_email.sendKeys(logins_confirm_email_string);
		acc_select.selectByVisibleText(acc_select_string);
		profile_cnp.sendKeys(profile_cnp_string);
		profile_fname.sendKeys(profile_fname_string);
		profile_lname.sendKeys(profile_lname_string);
		profile_address.sendKeys(profile_address_string);
		profile_phone.sendKeys(profile_phone_string);
		submit.click();
		
	}
	public void register() {
		Long.toHexString(Double.doubleToLongBits(Math.random()));
		
		contacts_name.sendKeys(gen(8));
		String pwd = gen(8);
		
		logins_password.sendKeys(pwd);
		logins_confirm_password.sendKeys(pwd);
		String mail = gen(8);
		String mail_final = mail.concat("@yopmail.com");
	
		logins_email.sendKeys(mail_final);
		logins_confirm_email.sendKeys(mail_final);
		acc_select.selectByVisibleText("Persoana Fizica");
		String CNP = "191103";
		String CNP_end = randInt(1000000,9999999);
		String CNP_Final = CNP.concat(CNP_end);

		profile_cnp.sendKeys(CNP_Final);
		profile_fname.sendKeys(gen(5));
		profile_lname.sendKeys(gen(5));
		profile_address.sendKeys(gen(10));
		
		String NR = "07445";
		String nr_end = randInt(10000,99999);
		String NR_Final = NR.concat(nr_end);
		profile_phone.sendKeys(NR_Final);
		submit.click();
	}
	 public static String gen(int length) {
		    StringBuffer sb = new StringBuffer();
		    for (int i = length; i > 0; i -= 12) {
		      int n = Math.min(12, Math.abs(i));
		      sb.append(StringUtils.leftPad(Long.toString(Math.round(Math.random() * Math.pow(36, n)), 36), n, '0'));
		    }
		    return sb.toString();
		  }
	 
	 public static String randInt(int min, int max) {

		 
		    Random rand = new Random();

		    Integer randomNum = rand.nextInt((max - min) + 1) + min;

		    return randomNum.toString();
		}

}
