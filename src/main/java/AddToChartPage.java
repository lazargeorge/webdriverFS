import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddToChartPage extends LoadableComponent<AddToChartPage> {
    @FindBy(css = ".cat-nav-tab[href*=\"laptop\"]")
    private WebElement laptopMeniu;

    @FindBy(xpath = "(//*[@class=\"product-box\"])[6]//img") //(//*[@class=\"product-box\"])[6]/div/a
    private WebElement product;

    @FindBy(xpath = "(//*[@class=\"product-box\"])[6]//a[@class=\"add\"]")
    private WebElement addToChart;

    @FindBy(css = ".ct-name>p>a")
    private WebElement productInChart;

    private WebDriver driver;
    private WebDriverWait wait;

    public AddToChartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public WebElement getLaptopMeniu() {
        return laptopMeniu;
    }

    public WebElement getProduct() {
        return product;
    }

    public WebElement getAddToChart() {
        return addToChart;
    }

    public WebElement getProductInChart() {
        return productInChart;
    }

    public void addToChart() throws InterruptedException {
        getAddToChart().click();
    }

    @Override
    protected void load() {
        driver.get("http://www.pcgarage.ro/notebook-laptop/");
        
    }

    @Override
    protected void isLoaded() throws Error {
        wait.until(ExpectedConditions.visibilityOf(getLaptopMeniu()));
        System.out.println("Add to chart: Laptop meniu is displayed? "+getLaptopMeniu().isDisplayed());
        Assert.assertTrue(getLaptopMeniu().isDisplayed());
    }
}
