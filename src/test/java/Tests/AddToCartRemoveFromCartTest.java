package Tests;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartRemoveFromCartTest
{

    /**
     * This test will add items to cart
     * 
     * Step 1: Go to the Laptop category
     * Step 2: Add the first laptop in the list to cart
     * Step 3: Check if the basket has items in it
     */

    FirefoxBinary binary = new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
    FirefoxProfile profile = new FirefoxProfile();
    WebDriver driverc = new FirefoxDriver(binary, profile);
    Actions action = new Actions(driverc);

    @Test
    public void AddToBasket() throws Exception
    {
        driverc.get("http://www.evomag.ro/PORTABILE-Laptopuri/");
        driverc.findElement(By.className("btn_form_cumpar")).click();
        WebElement cos = driverc.findElement(By.id("sendOrder-form"));
        Assert.assertEquals(true, cos.isDisplayed());
    }

    
    /**
     * This test runs after the first one
     * 
     * Step 1: Press the empty basket button
     * Step 2: Check that the basket is empty
     * 
     */
    @Test
    public void EmptyBasket() throws Exception
    {
        driverc.findElement(By.className("sterge_tab_cos")).click();
        WebElement cos_gol = driverc.findElement(By.className("error_cart_empty"));
        Assert.assertEquals(true, cos_gol.isDisplayed());
    }

}
