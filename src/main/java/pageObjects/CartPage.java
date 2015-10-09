package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage
{
    WebDriver driver;

    @FindBy(id = "sendOrder-form")
    public WebElement cos;

    @FindBy(css = ".sterge_tab_cos")
    WebElement empty_basket;

    @FindBy(className = "error_cart_empty")
    public WebElement cos_gol;

    public CartPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public void EmptyCart(WebDriver driver)
    {
        empty_basket.click();
    }

}
