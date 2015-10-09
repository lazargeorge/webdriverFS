package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage
{
    WebDriver driver;
    String name_produs;

    @FindBy(name = "yt0")
    WebElement addToCart_produs;

    @FindBy(xpath = "html/body/div[5]/div[3]/div/div[2]/div[2]/div[6]/div[1]/a/img")
    static WebElement produs;

    public ProductPage(WebDriver driver)
    {
        this.driver = driver;
        driver.get("http://www.evomag.ro/PORTABILE-Laptopuri/");
    }

    public void nume_produs()
    {
        name_produs = produs.getAttribute("alt").substring(6, 30).trim();
    }

    public void buy()
    {
        nume_produs();
        System.out.printf("%s\n", name_produs);
        addToCart_produs.click();
    }

}
