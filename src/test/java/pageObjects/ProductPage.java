package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage
{
    private static WebElement element = null;
    
    public static WebElement productPageLink(WebDriver driver)
    {
        element = driver.findElement(By.xpath("html/body/div[5]/div[3]/div/div[2]/div[2]/div[5]/div[1]/a/img"));
        return element;
    }

    public static WebElement produs_1(WebDriver driver)
    {
        element = driver.findElement(By.xpath("html/body/div[5]/div[3]/div/div[2]/div[2]/div[5]/div[1]/a/img"));
        return element;
    }
    
    public static WebElement produs_2(WebDriver driver)
    {
        element = driver.findElement(By.xpath("html/body/div[5]/div[3]/div/div[2]/div[2]/div[6]/div[1]/a/img"));
        return element;
    }

    public static WebElement produs_3(WebDriver driver)
    {
        element = driver.findElement(By.xpath("html/body/div[5]/div[3]/div/div[2]/div[2]/div[7]/div[1]/a/img"));
        return element;
    }

    public static WebElement produs_4(WebDriver driver)
    {
        element = driver.findElement(By.xpath("html/body/div[5]/div[3]/div/div[2]/div[2]/div[8]/div[1]/a/img"));
        return element;
    }

    public static WebElement addToCart_prod1(WebDriver driver)
    {
        element = driver.findElement(By.name("yt0"));
        return element;
    }

    public static WebElement addToCart_prod2(WebDriver driver)
    {
        element = driver.findElement(By.name("yt1"));
        return element;
    }

    public static WebElement addToCart_prod3(WebDriver driver)
    {
        element = driver.findElement(By.name("yt2"));
        return element;
    }

    public static WebElement addToCart_prod4(WebDriver driver)
    {
        element = driver.findElement(By.name("yt3"));
        return element;
    }

}
