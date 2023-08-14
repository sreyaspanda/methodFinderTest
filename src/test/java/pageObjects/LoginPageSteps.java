package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageSteps  {
    public void loadPage(WebDriver driver) throws InterruptedException {
        driver.get("http://localhost:3000/login");
        Thread.sleep(5000);
    }

    public void enterLoginCredentials(WebDriver driver, String username, String password) throws InterruptedException {
        WebElement usernameField = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordField.sendKeys(password);
        Thread.sleep(5000);
        WebElement submitButton = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
        submitButton.click();
    }

    public void verifyLogin(WebDriver driver) throws InterruptedException {
        Thread.sleep(5000);
        String expectedTitle = "Home Page";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }
}
