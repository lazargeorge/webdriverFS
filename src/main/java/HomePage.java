import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageObject {

    @FindBy(xpath = "//*[@id='user_header']//a[text()=\"Contul meu\"]")
    private WebElement autButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getAutButton() {
        return autButton;
    }

}
