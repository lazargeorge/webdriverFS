import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RemoveFromChartPage extends PageObject {
    @FindBy(xpath = "//*[@id='container']//h1")
    private WebElement title;

    @FindBy(xpath = "//*[@class=\"cpm-top-gc\"]/a[contains(@href,\"golestecos\")]")
    private WebElement remove;

    @FindBy(xpath = "//*[@id='cart-page-desktop']//*[contains(text(),\"gol\")]")
    private WebElement empty;

    public RemoveFromChartPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getTitle() {
        return title;
    }

    public WebElement getRemove() {
        return remove;
    }

    public WebElement getEmpty() {
        return empty;
    }
}
