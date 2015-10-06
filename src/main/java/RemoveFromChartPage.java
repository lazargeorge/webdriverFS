import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoveFromChartPage extends LoadableComponent<RemoveFromChartPage> {

    @FindBy(xpath = "//*[@id='container']//h1")
    private WebElement title;

    @FindBy(xpath = "//*[@class=\"cpm-top-gc\"]/a[contains(@href,\"golestecos\")]")
    private WebElement remove;

    private WebDriver driver;

    private WebDriverWait wait;

    public RemoveFromChartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public WebElement getTitle() {
        return title;
    }

    public WebElement getRemove() {
        return remove;
    }

    public void removeFromChart() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getRemove());

    }

    @Override
    protected void load() {
        driver.get("https://www.pcgarage.ro/cos/");

    }

    @Override
    protected void isLoaded() throws Error {
        wait.until(ExpectedConditions.visibilityOf(getTitle()));
        Assert.assertTrue(getTitle().isDisplayed());
    }
}
