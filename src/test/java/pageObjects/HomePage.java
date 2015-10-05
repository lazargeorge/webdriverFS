package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage 
{
     private static WebElement element = null;
     
     public static WebElement link_ContulMeu(WebDriver driver)
     {   
        element = driver.findElement(By.linkText("/client/auth"));
        return element;
     }
     
     public static WebElement nume_ContulMeu(WebDriver driver)
     {   
        element = driver.findElement(By.cssSelector(".c_header>em"));
        return element;
     }

     public static WebElement home(WebDriver driver)
     {   
        element = driver.findElement(By.className("img_logo"));
        return element;
     }
}
