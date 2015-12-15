package testng_package;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 */
public class test1 extends Base {


//	@Test
//	public void login_fail1() {
//
//		LoginPage test_login = PageFactory.initElements(efdriver, LoginPage.class);
//		test_login.log_in("abcd@gmail.com", "ds");
//		assertTrue(waitForElement(test_login.msg_invalid_username).isDisplayed());
//	
//
//	}

//	@Test
//	public void login_fail2() {
//
//		LoginPage test_login = PageFactory.initElements(efdriver, LoginPage.class);
//		test_login.log_in("abcd@gmail.com", "d");
//		assertTrue(waitForElement(test_login.msg_logins_password).isDisplayed());
//
//	}
//
//	@Test
//	public void login_fail3() {
//		LoginPage test_login = PageFactory.initElements(efdriver, LoginPage.class);
//		test_login.log_in("", "pwd");
//		assertTrue(waitForElement(test_login.msg_logins_ident).isDisplayed());
//	}
//
	@Test
	public void login_success() {
		LoginPage test_login = PageFactory.initElements(efdriver, LoginPage.class);
		test_login.log_in();
		assertTrue(waitForElement(test_login.contul_meu).isDisplayed());
	}

//	@Test
//	public void register_fail1() {
//
//		RegisterPage test_reg = PageFactory.initElements(efdriver, RegisterPage.class);
//
//		test_reg.register("ionut123", "pwdabc1", "pwdabc2", "ionut@yahoo.com", "ionut@yahoo.com", "Persoana Fizica",
//				"1911030168964", "ion", "gheorghe", "strada abc, nr 3, Iasi", "0744567890");
//		assertTrue(waitForElement(test_reg.msg_logins_confirm_password).isDisplayed());
//
//	}
//
//	@Test
//	public void register_fail2() {
//
//		RegisterPage test_reg = PageFactory.initElements(efdriver, RegisterPage.class);
//
//		test_reg.register("", "pwdabc1", "pwdabc2", "ionut@yahoo.com", "ionut@yahoo.com", "Persoana Fizica",
//				"1911030168964", "ion", "gheorghe", "strada abc, nr 3, Iasi", "0744567890");
//		assertTrue(waitForElement(test_reg.msg_logins_username).isDisplayed());
//
//	}
//
//	@Test
//	public void register_success() {
//
//		RegisterPage test_reg = PageFactory.initElements(efdriver, RegisterPage.class);
//
//		 test_reg.register();
//		 assertTrue(waitForElement(test_reg.iesire_cont).isDisplayed());
//
//	}
//	
//	@Test
//	public void try_test() {
//		
//		efdriver.get("http://www.google.com/");
//		assertTrue(true);
//
//		
//	}
//

}
