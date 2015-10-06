import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends Base
{

    LoginPage()
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.emag.ro");
        driver.findElement(By.xpath("//figure[@id=\"emg-user-menu\"]/figcaption")).click();
    }

    public void login(String username, String pass)
    {

        WebElement loginEmail = driver.findElement(By.xpath("//input[@id=\"r_email\"]"));
        WebElement loginPass = driver.findElement(By.xpath("//input[@id=\"r_password\"]"));
        WebElement loginSubmit = driver.findElement(By.xpath("//input[@type=\"image\"]"));

        loginEmail.sendKeys(username);
        loginPass.sendKeys(pass);
        loginSubmit.click();
    }
}
