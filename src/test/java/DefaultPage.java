import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DefaultPage extends Base
{

    DefaultPage()
    {
        driver.get("http://www.emag.ro");
    }

    /**
     * Logout
     */
    public void logout()
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions mouseHover = new Actions(driver);
        mouseHover.moveToElement(driver.findElement(By.xpath("//figure[@id=\"emg-user-menu\"]"))).build().perform();
        WebElement logout = driver.findElement(By.xpath("//div[@class=\"user-logout-icon\"]"));
        logout.click();
    }

    public void addToCart()
    {
        WebElement produs = driver.findElement(By.xpath("//input[@name=\"service_parrent_id\"]"));
        produse[nrProduse] = produs.getAttribute("value");
        nrProduse++;
        driver.findElement(By.xpath("//button[@id=\"add-to-cart-submit\"]")).click();
        if (existsElement("//button[@class=\"emg-button btn-change-warranty\"]"))
            driver.findElement(By.xpath("//button[@class=\"emg-button btn-change-warranty\"]")).click();
    }

    public void removeFromCart(String id, int pozitieProdus)
    {

        WebElement remove = driver.findElement(By.xpath("//div[contains(@onclick,\"" + id + "\")]/img"));
        remove.click();
        driver.findElement(By.xpath("//div[@id=\"yes\"]")).click();

        for (int i = pozitieProdus; i <= nrProduse - 1; i++)
        {
            produse[i] = produse[i + 1];
        }
        nrProduse--;

    }
}
