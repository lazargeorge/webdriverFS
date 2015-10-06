import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CreateAccountPage extends Base
{

    public CreateAccountPage()
    {
        driver.get("http://www.emag.ro");
        driver.findElement(By.xpath("//figure[@id=\"emg-user-menu\"]/figcaption")).click();
        driver.findElement(By.xpath("//img[@src=\"https://s1emagst.akamaized.net/layout/ro/images/login_layout/newaccount-button.png?v3\"]")).click();
    }

    /**
     * Create account
     */
    public void createAccount(String username, String email, String pass)
    {

        WebElement name = driver.findElement(By.xpath("//input[@id=\"r_name\"]"));
        WebElement emailElement = driver.findElement(By.xpath("//input[@id=\"r_email\"]"));
        WebElement passElement = driver.findElement(By.xpath("//input[@id=\"r_password\"]"));
        WebElement rcpass = driver.findElement(By.xpath("//input[@id=\"rc_password\"]"));
        WebElement acceptTerms = driver.findElement(By.xpath("//span[@id=\"ctrigger\"]"));
        WebElement submitBtn = driver.findElement(By.xpath("//input[@type=\"image\"]"));

        name.sendKeys(username);
        emailElement.sendKeys(email);
        passElement.sendKeys(pass);
        rcpass.sendKeys(pass);
        acceptTerms.click();
        submitBtn.click();
    }

    /**
     * Create random account
     */
    public void createAccount()
    {
        WebElement name = driver.findElement(By.xpath("//input[@id=\"r_name\"]"));
        WebElement emailElement = driver.findElement(By.xpath("//input[@id=\"r_email\"]"));
        WebElement passElement = driver.findElement(By.xpath("//input[@id=\"r_password\"]"));
        WebElement rcpass = driver.findElement(By.xpath("//input[@id=\"rc_password\"]"));
        WebElement acceptTerms = driver.findElement(By.xpath("//span[@id=\"ctrigger\"]"));
        WebElement submitBtn = driver.findElement(By.xpath("//input[@type=\"image\"]"));

        name.sendKeys("asdf guy");
        emailElement.sendKeys(rndEmail);
        passElement.sendKeys(rndPass);
        rcpass.sendKeys(rndPass);
        acceptTerms.click();
        submitBtn.click();
    }
}
