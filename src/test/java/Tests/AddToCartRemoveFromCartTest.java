package Tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class AddToCartRemoveFromCartTest
{
    WebDriver driver = new FirefoxDriver();

    @BeforeGroups
    public void setupEnvironment()
    {
        FirefoxBinary binary = new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
        FirefoxProfile profile = new FirefoxProfile();
        WebDriver driver = new FirefoxDriver(binary, profile);
        driver.get("http://www.evomag.ro/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        if (driver.getCurrentUrl() != "http://www.evomag.ro/")
        {
            driver.findElement(By.xpath(".//*[@id='header']/div[1]/a/img")).click();
            System.out.println("The page has a redirect\n");
        }
    }

    /**
     * This test adds 4 products to cart then checks if they are present in the basket at checkout
     * Step 1: Get the full names of the products then truncate to a more manageable size
     * Step 2: Add the products to cart
     * Step 3: Check if all the items the are present
     */

    @Test(groups = "addremove")
    public void AddToBasket() throws Exception
    {
        driver.get("http://www.evomag.ro/PORTABILE-Laptopuri/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        String lap1 = driver.findElement(By.xpath("html/body/div[5]/div[3]/div/div[2]/div[2]/div[5]/div[1]/a/img")).getAttribute("alt").substring(6, 30).trim();
        String lap2 = driver.findElement(By.xpath("html/body/div[5]/div[3]/div/div[2]/div[2]/div[6]/div[1]/a/img[2]")).getAttribute("alt").substring(6, 30)
                .trim();
        String lap3 = driver.findElement(By.xpath("html/body/div[5]/div[3]/div/div[2]/div[2]/div[7]/div[1]/a/img")).getAttribute("alt").substring(6, 30).trim();
        String lap4 = driver.findElement(By.xpath("html/body/div[5]/div[3]/div/div[2]/div[2]/div[8]/div[1]/a/img")).getAttribute("alt").substring(6, 30).trim();

        System.out.printf("%s\n%s\n%s\n%s\n", lap1, lap2, lap3, lap4);

        driver.findElement(By.name("yt0")).click();
        driver.navigate().back();
        driver.findElement(By.name("yt1")).click();
        driver.navigate().back();
        driver.findElement(By.name("yt2")).click();
        driver.navigate().back();
        driver.findElement(By.name("yt3")).click();
        WebElement cos = driver.findElement(By.id("sendOrder-form"));

        Assert.assertEquals(true, cos.isDisplayed());

        Assert.assertEquals(driver.findElement(By.partialLinkText(lap1)).isDisplayed(), true);
        Assert.assertEquals(driver.findElement(By.partialLinkText(lap2)).isDisplayed(), true);
        Assert.assertEquals(driver.findElement(By.partialLinkText(lap3)).isDisplayed(), true);
        Assert.assertEquals(driver.findElement(By.partialLinkText(lap4)).isDisplayed(), true);

    }

    /**
     * This test runs after the first one
     * Step 1: Press the empty basket button
     * Step 2: Check that the basket is empty
     */

    @Test(groups = "addremove")
    public void EmptyBasket() throws Exception
    {
        driver.findElement(By.className("sterge_tab_cos")).click();
        WebElement cos_gol = driver.findElement(By.className("error_cart_empty"));
        Assert.assertEquals(true, cos_gol.isDisplayed());
    }

    @AfterGroups
    public void exit()
    {
        driver.close();
    }

}
