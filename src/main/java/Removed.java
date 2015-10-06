import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Removed extends LoadableComponent<Removed> {

    @FindBy(xpath = "//*[@id='cart-page-desktop']//*[contains(text(),\"gol\")]")
    private WebElement empty;
    private WebDriver driver;
    private WebDriverWait wait;
    
    public Removed(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }
    
    public WebElement getEmpty() {
        return empty;
    }

    @Override
    protected void load() {
        driver.get("https://www.pcgarage.ro/cos/");

    }

    @Override
    protected void isLoaded() {
        wait.until(ExpectedConditions.visibilityOf(getEmpty()));
        System.out.println(getEmpty().isDisplayed());
        Assert.assertTrue(getEmpty().isDisplayed());

    }

}
