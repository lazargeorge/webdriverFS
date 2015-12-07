import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Modified by Sergiu Vidrascu on 21/09/15.
 */
public class test1 {
	
	 @Test
	 public void test()
	 {
		    WebDriver driver = new FirefoxDriver();
			driver.get("http://www.elefant.ro");
			System.out.println(driver.getPageSource());
			WebElement drop_menu= driver.findElement(By.className("header-account-display"));
			drop_menu.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			 try{
				 driver.findElement(By.cssSelector("a[href='/autentificare']")).click();
			 }
			 catch(Exception e){
				 System.out.println("nu a fost selectat dropdownul");
				 drop_menu.click();
				 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 }
			 
			 try{
				 driver.findElement(By.id("login_username")).sendKeys("abcd@gmail.com");
				 driver.findElement(By.id("login_password")).sendKeys("pwd");
				 driver.findElement(By.id("login_classic")).click();
			 }catch(Exception e){
				 System.out.println("nu a fost selectat dropdownul");
				 drop_menu.click();
				 driver.findElement(By.cssSelector("a[href='/autentificare']")).click();
			 }
	 }
	 }

