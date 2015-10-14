import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DefaultPage extends LoadableComponent<DefaultPage>
{

    WebDriver driver;
    private WebDriverWait wait;
    @FindBy(how = How.ID, using = "emg-user-menu")
    WebElement dropDownMenu;
    @FindBy(how = How.XPATH, using = "//a[contains(@href,'logout')]")
    WebElement logout;
    @FindBy(how = How.ID, using = "add-to-cart-submit")
    WebElement addToCart;
    @FindBy(how = How.XPATH, using = "(//button[@class=\"emg-button btn-change-warranty\"])[1]")
    WebElement changeWarranty;
    @FindBy(how = How.NAME, using = "service_parrent_id")
    WebElement produs;

    public DefaultPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);

    }

    /**
     * Logout
     * 
     * @throws InterruptedException
     */
    public void logout() throws InterruptedException
    {

        driver.get("http://www.emag.ro/user/logout");

    }

    @Override
    protected void load()
    {
        driver.get("http://www.emag.ro");
    }

    @Override
    protected void isLoaded() throws Error
    {

        wait.until(ExpectedConditions.visibilityOf(dropDownMenu));
    }

    public void addToCart(Base b)
    {
        b.produse[b.nrProduse] = produs.getAttribute("value");
        b.nrProduse++;
        addToCart.click();
        if (changeWarranty.isDisplayed())
            changeWarranty.click();
    }

}
