package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage
{
    WebDriver driver;

    @FindBy(css = ".detalii_cont_container>table>tbody")
    public WebElement date_personale;
    
    @FindBy(css = ".c_header>em")
    public WebElement nume_ContulMeu;

    public AccountPage(WebDriver driver)
    {
        this.driver = driver;
        driver.get("http://www.evomag.ro/client/details");
    }
}
