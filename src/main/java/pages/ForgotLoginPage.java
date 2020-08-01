package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class ForgotLoginPage {
    WebDriver driver = null;

    public ForgotLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //List of used locators
    private By forgotLoginForm = By.id("forgot-login");

    public boolean isForgotLoginFormShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(forgotLoginForm)).isDisplayed();
    }
}