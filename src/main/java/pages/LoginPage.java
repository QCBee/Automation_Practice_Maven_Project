package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverFactory;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LoginPage {
    private final WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //List of used locators - elements for actions
    @FindBy (id = "login-form-username")
    WebElement loginPageUserNameInput;
    @FindBy (id = "login-form-password")
    WebElement loginPageUserPassword;
    @FindBy(id = "login")
    WebElement loginPageLoginButton;
    @FindBy (id = "forgotpassword")
    WebElement loginPageForgotLoginLink;

    //List of used locators - elements for asserts
    private By errorLoginNotification = By.id("usernameerror");
    private By loginIcon = By.cssSelector(".aui-nav-link.login-link");


    public void enterUserName(String userName){
        loginPageUserNameInput.sendKeys(userName);
    }

    public void enterUserPassword(String userPassword){
        loginPageUserPassword.sendKeys(userPassword);
    }

    public void clickLoginButton(){
        loginPageLoginButton.click();
    }

    public void navigateToLoginPage(String loginUrl){
        WebDriverFactory.getDriver().get(loginUrl);
    }

    public void clickCanAccessAccountLink(){
        loginPageForgotLoginLink.click();
    }

    public boolean isErrorNotificationShown(){
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(TimeOutTypes.LOW.getTimeOutInSec()).getSeconds());
        return wait.until(presenceOfElementLocated(errorLoginNotification)).isDisplayed();
    }

    public boolean isLoginIconShown(){
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(TimeOutTypes.LOW.getTimeOutInSec()).getSeconds());
        return wait.until(presenceOfElementLocated(loginIcon)).isDisplayed();
    }
}