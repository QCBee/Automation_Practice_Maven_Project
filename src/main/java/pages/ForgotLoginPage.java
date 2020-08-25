package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverFactory;

import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class ForgotLoginPage {
    private final WebDriver driver;
    public ForgotLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //List of used locators - locators for asserts
    private By forgotLoginForm = By.id("forgot-login");

    public boolean isForgotLoginFormShown(){
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(forgotLoginForm)).isDisplayed();
    }
}