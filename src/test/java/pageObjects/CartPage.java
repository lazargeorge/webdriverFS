package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage
{
    private static WebElement element = null;
    
    public static WebElement cos(WebDriver driver)
    {
        element = driver.findElement(By.id("sendOrder-form"));
        return element;
    }
    
    public static WebElement empty_basket(WebDriver driver)
    {
        element = driver.findElement(By.className("sterge_tab_cos"));
        return element;
    }
    
    public static WebElement cos_gol(WebDriver driver)
    {
        element = driver.findElement(By.className("error_cart_empty"));
        return element;
    }
}
