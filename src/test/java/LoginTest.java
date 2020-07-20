import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.WebDriverFactory;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;


public class LoginTest {

    WebDriver driver =null;

    //List of used locators
    String loginURL = "https://jira.hillel.it/secure/Dashboard.jspa";
    String usernameLoginLocator = "login-form-username";
    String userpasswordLoginLocator = "login-form-password";
    String loginButtonLocator = "login";
    String validUsernameTestData = "webinar5";
    String validUserPasswordTestData = "webinar5";
    String userProfileIconLocator = "[alt = 'User profile for webinar5']";
    String loginIconLocator = ".aui-nav-link.login-link";
    String errorLoginNotificationLocator = "usernameerror";
    String canAccessAccountLinkLocator = "forgotpassword";
    String forgotLoginFormLocator = "forgot-login";

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
    }

    @Test
    public void successfulLoginTest(){
        driver.get(loginURL);
        driver.findElement(By.id(usernameLoginLocator)).sendKeys(validUsernameTestData);
        driver.findElement(By.id(userpasswordLoginLocator)).sendKeys(validUserPasswordTestData);
        driver.findElement(By.id(loginButtonLocator)).click();

    // Explicit Wait for element to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        boolean userProfileIconIsPresent = wait.until(presenceOfElementLocated(By.cssSelector(userProfileIconLocator))).isDisplayed();
        assertEquals(userProfileIconIsPresent, true);


    }

    @Test
    public void loginWithBothEmptyFieldsTest(){
        driver.get(loginURL);
        driver.findElement(By.id(loginButtonLocator)).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        boolean errorNotificationMessageIsPresent = wait.until(presenceOfElementLocated(By.id(errorLoginNotificationLocator))).isDisplayed();
        boolean loginIconIsPresent = wait.until(presenceOfElementLocated(By.cssSelector(loginIconLocator))).isDisplayed();
        assertEquals(errorNotificationMessageIsPresent,true);
        assertEquals(loginIconIsPresent,true);

    }

    @Test
    public void loginWithEmptyUserNameTest(){
        driver.get(loginURL);
        driver.findElement(By.id(userpasswordLoginLocator)).sendKeys(validUserPasswordTestData);
        driver.findElement(By.id(loginButtonLocator)).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        boolean errorNotificationMessageIsPresent = wait.until(presenceOfElementLocated(By.id(errorLoginNotificationLocator))).isDisplayed();
        boolean loginIconIsPresent = wait.until(presenceOfElementLocated(By.cssSelector(loginIconLocator))).isDisplayed();
        assertEquals(errorNotificationMessageIsPresent,true);
        assertEquals(loginIconIsPresent,true);

    }

    @Test
    public void logintWithEmptyPasswordTest(){
        driver.get(loginURL);
        driver.findElement(By.id(usernameLoginLocator)).sendKeys(validUsernameTestData);
        driver.findElement(By.id(loginButtonLocator)).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        boolean errorNotificationMessageIsPresent = wait.until(presenceOfElementLocated(By.id(errorLoginNotificationLocator))).isDisplayed();
        boolean loginIconIsPresent = wait.until(presenceOfElementLocated(By.cssSelector(loginIconLocator))).isDisplayed();
        assertEquals(errorNotificationMessageIsPresent,true);
        assertEquals(loginIconIsPresent,true);
    }

    @Test
    public void openResetPasswordPageFromLoginFormTest(){
        driver.get(loginURL);
        driver.findElement(By.id(canAccessAccountLinkLocator)).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        boolean forgotLoginFormIsPresent = wait.until(presenceOfElementLocated(By.id(forgotLoginFormLocator))).isDisplayed();
        assertEquals(forgotLoginFormIsPresent,true);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}