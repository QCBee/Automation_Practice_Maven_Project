package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LoginPage {
    private WebDriver driver= null;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //List of used locators
    private By userNameInput = By.id("login-form-username");
    private By userPasswordInput = By.id("login-form-password");
    private By loginButton = By.id("login");
    private By errorLoginNotification = By.id("usernameerror");
    private By canAccessAccountLink = By.id("forgotpassword");
    private By loginIcon = By.cssSelector(".aui-nav-link.login-link");

    public void enterUserName(String userName){
        driver.findElement(userNameInput).clear();
        driver.findElement(userNameInput).sendKeys(userName);
    }

    public void enterUserPassword(String userPassword){
        driver.findElement(userPasswordInput).sendKeys(userPassword);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void navigateToLoginPage(String loginUrl){
        driver.get(loginUrl);
    }

    public void clickCanAccessAccountLink(){
        driver.findElement(canAccessAccountLink).click();
    }

    public boolean isErrorNotificationShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(errorLoginNotification)).isDisplayed();
    }

    public boolean isLoginIconShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(loginIcon)).isDisplayed();
    }
}
