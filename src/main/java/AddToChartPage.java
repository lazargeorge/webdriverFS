import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToChartPage {
    @FindBy(css = ".cat-nav-tab[href*=\"laptop\"]")
    private WebElement laptopMeniu;

    @FindBy(xpath = "(//*[@class='product-box'])[6]/div/a")
    private WebElement product;

    @FindBy(xpath = "(//*[@class=\"product-box\"])[6]//a[@class=\"add\"]")
    private WebElement addToChart;

    @FindBy(css = ".ct-name>p>a")
    private WebElement productInChart;

    private WebDriver driver;
    private RemoveFromChartPage removeFromChartPage;

    public AddToChartPage(WebDriver driver) {
        this.driver = driver;
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
        getLaptopMeniu().click();
        String productNameFromList = getProduct().getAttribute("title");
        getAddToChart().click();
        String productNameFromChart = getProductInChart().getText();
        Assert.assertEquals("Notebook / Laptop " + productNameFromList, productNameFromChart);
        removeFromChartPage = PageFactory.initElements(driver, RemoveFromChartPage.class);
        removeFromChartPage.removeFromChart();
    }
}
