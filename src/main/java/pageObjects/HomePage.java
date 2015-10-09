package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
    WebDriver driver;

    @FindBy(linkText = "/client/auth")
    WebElement link_ContulMeu;

    @FindBy(css = ".c_header>em")
    public WebElement nume_ContulMeu;

    @FindBy(css = ".imgResponsive.lazy-hidden")
    WebElement home;

    @FindBy(css = ".HeaderWidgetAuth")
    public WebElement fb_login;

    @FindBy(linkText = "/client/details")
    public WebElement acc_details;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        driver.get("http://www.evomag.ro/");
    }

    public String getNume_ContulMeu()
    {
        return nume_ContulMeu.getText();
    }

    public void go_Home(WebDriver driver)
    {
        home.click();
    }
}
