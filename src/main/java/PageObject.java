import org.openqa.selenium.WebDriver;


public class PageObject {
    protected WebDriver browserLocal;

    public PageObject(WebDriver driver) {
        browserLocal = driver;
    }
}
