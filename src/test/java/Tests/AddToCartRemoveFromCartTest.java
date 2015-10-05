package Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.ProductPage;

public class AddToCartRemoveFromCartTest
{
    WebDriver driver = new FirefoxDriver();

    /**
     * This test adds 4 products to cart then checks if they are present in the basket at checkout
     * Step 1: Get the full names of the products then truncate to a more manageable size
     * Step 2: Add the products to cart
     * Step 3: Check if all the items the are present
     */

    @Test
    public void AddToBasket() throws Exception
    {
        driver.get("http://www.evomag.ro/PORTABILE-Laptopuri/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        String lap1 = ProductPage.produs_1(driver).getAttribute("alt").substring(6, 30).trim();
        String lap2 = ProductPage.produs_2(driver).getAttribute("alt").substring(6, 30).trim();
        String lap3 = ProductPage.produs_3(driver).getAttribute("alt").substring(6, 30).trim();
        String lap4 = ProductPage.produs_4(driver).getAttribute("alt").substring(6, 30).trim();

        System.out.printf("%s\n%s\n%s\n%s\n", lap1, lap2, lap3, lap4);

        ProductPage.addToCart_prod1(driver).click();
        driver.navigate().back();
        ProductPage.addToCart_prod2(driver).click();
        driver.navigate().back();
        ProductPage.addToCart_prod3(driver).click();
        driver.navigate().back();
        ProductPage.addToCart_prod4(driver).click();

        Assert.assertTrue(CartPage.cos(driver).isDisplayed(), "The cart is being filled");

        Assert.assertTrue(driver.findElement(By.partialLinkText(lap1)).isDisplayed(), String.format("%s se regaseste in cos", lap1));
        Assert.assertTrue(driver.findElement(By.partialLinkText(lap2)).isDisplayed(), String.format("%s se regaseste in cos", lap2));
        Assert.assertTrue(driver.findElement(By.partialLinkText(lap3)).isDisplayed(), String.format("%s se regaseste in cos", lap3));
        Assert.assertTrue(driver.findElement(By.partialLinkText(lap4)).isDisplayed(), String.format("%s se regaseste in cos", lap4));

    }

    /**
     * This test runs after the first one
     * Step 1: Press the empty basket button
     * Step 2: Check that the basket is empty
     */

    @Test
    public void EmptyBasket() throws Exception
    {
        CartPage.empty_basket(driver).click();
        Assert.assertTrue(CartPage.cos_gol(driver).isDisplayed(), "The cart is empty");
    }

}
