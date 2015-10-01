import junit.framework.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RemoveFromChartPage {

    @FindBy(xpath = "//*[@id='container']//h1")
    private WebElement title;

    @FindBy(xpath = "//*[@class=\"cpm-top-gc\"]/a[contains(@href,\"golestecos\")]")
    private WebElement remove;

    @FindBy(xpath = "//*[@id='cart-page-desktop']//*[contains(text(),\"gol\")]")
    private WebElement empty;

    private WebDriver driver;

    public RemoveFromChartPage(WebDriver driver) {
        this.driver = driver;
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

    public void removeFromChart() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getRemove());
        Assert.assertTrue(getEmpty().isDisplayed());
    }
}
