import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends LoadableComponent<HomePage> {

    @FindBy(xpath = "//*[@id='user_header']//a[text()=\"Contul meu\"]")
    private WebElement autButton;

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public WebElement getAutButton() {
        return autButton;
    }

    public void clickLogin() {
        getAutButton().click();
    }

    @Override
    protected void load() {
        driver.get("http://www.pcgarage.ro/");
    }

    @Override
    protected void isLoaded() throws Error {
        wait.until(ExpectedConditions.visibilityOf(getAutButton()));
        Assert.assertTrue(getAutButton().isDisplayed());

    }

}
