import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CartPage extends Base
{
    @FindBy(how = How.ID, id = "yes")
    WebElement yesBtn;

    public CartPage()
    {
        driver.get("https://www.emag.ro/shopping-cart");
    }

    public void removeFromCart(String id, int pozitieProdus)
    {

        WebElement remove = driver.findElement(By.xpath("//div[contains(@onclick,\"" + id + "\")]/img"));
        remove.click();
        yesBtn.click();

        for (int i = pozitieProdus; i <= nrProduse - 1; i++)
        {
            produse[i] = produse[i + 1];
        }
        nrProduse--;

    }
}
