package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage
{
    private static WebElement element = null;
    
    public static WebElement name(WebDriver driver)
    {
        element = driver.findElement(By.cssSelector("#RegisterClientForm_FullName"));
        return element;
    }
    
    public static WebElement email_create(WebDriver driver)
    {
        element = driver.findElement(By.id("RegisterClientForm_Email"));
        return element;
    }
    
    public static WebElement email_login(WebDriver driver)
    {
        element = driver.findElement(By.id("LoginClientForm_Email"));
        return element;
    }
    
    public static WebElement password_create(WebDriver driver)
    {
        element = driver.findElement(By.id("RegisterClientForm_Password"));
        return element;
    }
    
    public static WebElement password_login(WebDriver driver)
    {
        element = driver.findElement(By.id("LoginClientForm_Password"));
        return element;
    }
    
    public static WebElement register_button(WebDriver driver)
    {
        element = driver.findElement(By.name("yt0"));
        return element;
    }
    
    public static WebElement login_button(WebDriver driver)
    {
        element = driver.findElement(By.name("yt1"));
        return element;
    }
    
    
}
