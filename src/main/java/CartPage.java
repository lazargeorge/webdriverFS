import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends LoadableComponent<CartPage>
{
    @FindBy(how = How.ID, id = "yes")
    WebElement yesBtn;
    @FindBy(how=How.XPATH, using="//span[@class=\"emg-cart-bubble\"]")
    WebElement cart;
    @FindBy(how=How.XPATH, using="//p[@class=\"avertisment\"]")
    WebElement alert;
    @FindBy(how=How.ID, id="page-title")
    WebElement pageTitle;
    
    WebDriver driver;
    private WebDriverWait wait;
    
    public CartPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this); 
        wait = new WebDriverWait(driver, 10);
       // driver.get("https://www.emag.ro/shopping-cart");
    }

    public void removeFromCart(String id, int pozitieProdus , Base b)
    {

        WebElement remove = driver.findElement(By.xpath("//div[contains(@onclick,\"" + id + "\")]/img"));
        remove.click();
        yesBtn.click();

        for (int i = pozitieProdus; i <= b.nrProduse - 1; i++)
        {
            b.produse[i] = b.produse[i + 1];
        }
        b.nrProduse--;

    }

    @Override
    protected void load()
    {
        driver.get("https://www.emag.ro/shopping-cart");
        
    }

    @Override
    protected void isLoaded() throws Error
    {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        
    }
}
