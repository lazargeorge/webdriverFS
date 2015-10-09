import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DefaultPage
{

    WebDriver driver;
    
    @FindBy(how = How.ID, using = "emg-user-menu")
    WebElement dropDownMenu;
    @FindBy(how = How.XPATH,  using = "//a[contains(@href,'logout')]")
    WebElement logout;
    @FindBy(how = How.ID, using = "add-to-cart-submit")
    WebElement addToCart;
    @FindBy(how = How.CLASS_NAME, using = "emg-button btn-change-warranty")
    WebElement changeWarranty;
    @FindBy(how = How.NAME, using = "service-parent-id")
    WebElement produs;

    public DefaultPage(WebDriver driver)
    {
        this.driver = driver;
//        driver.get("http://www.emag.ro");
    }

    /**
     * Logout
     * @throws InterruptedException 
     */
    public void logout() throws InterruptedException
    {
//        Actions mouseHover = new Actions(driver);
        driver.get("http://www.emag.ro/user/logout");
//        mouseHover.moveToElement(dropDownMenu).click(logout).build().perform();
        // logout.click();
    }

//    public void addToCart()
//    {
//        produse[nrProduse] = produs.getAttribute("value");
//        nrProduse++;
//        addToCart.click();
//        if (existsElement("//button[@class=\"emg-button btn-change-warranty\"]"))
//            changeWarranty.click();
//    }

}
