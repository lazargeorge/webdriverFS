import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DefaultPage extends Base
{

    @FindBy(how = How.ID, id = "emg-user-menu")
    WebElement dropDownMenu;
    @FindBy(how = How.CLASS_NAME, className = "user-logout-icon")
    WebElement logout;
    @FindBy(how = How.ID, id = "add-to-cart-submit")
    WebElement addToCart;
    @FindBy(how = How.CLASS_NAME, className = "emg-button btn-change-warranty")
    WebElement changeWarranty;
    @FindBy(how = How.NAME, name = "service-parent-id")
    WebElement produs;
    @FindBy(how = How.ID, id = "yes")
    WebElement yesBtn;

    public DefaultPage()
    {
        driver.get("http://www.emag.ro");
    }

    /**
     * Logout
     */
    public void logout()
    {
        Actions mouseHover = new Actions(driver);
        mouseHover.moveToElement(dropDownMenu).build().perform();
        logout.click();
    }

    public void addToCart()
    {
        produse[nrProduse] = produs.getAttribute("value");
        nrProduse++;
        addToCart.click();
        if (existsElement("//button[@class=\"emg-button btn-change-warranty\"]"))
            changeWarranty.click();
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
