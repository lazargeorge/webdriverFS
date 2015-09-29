import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddToChartPage extends PageObject {
    @FindBy(css = ".cat-nav-tab[href*=\"laptop\"]")
    private WebElement laptopMeniu;

    @FindBy(xpath = "(//*[@class='product-box'])[6]/div/a")
    private WebElement product;

    @FindBy(xpath = "(//*[@class=\"product-box\"])[6]//a[@class=\"add\"]")
    private WebElement addToChart;

    @FindBy(css = ".ct-name>p>a")
    private WebElement productInChart;

    public AddToChartPage(WebDriver driver) {
        super(driver);
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
}
