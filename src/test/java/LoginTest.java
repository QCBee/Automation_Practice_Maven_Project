
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ForgotLoginPage;
import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverFactory;

public class LoginTest {
    WebDriver driver = null;
    LoginPage loginPage = null;
    HomePage homePage = null;
    ForgotLoginPage forgotLoginPage = null;

    //Test data locators
    String loginURL = "https://jira.hillel.it/secure/Dashboard.jspa";
    String validUsernameTestData = "webinar5";
    String validUserPasswordTestData = "webinar5";

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        forgotLoginPage = new ForgotLoginPage(driver);
    }

    @Test
    public void successfulLoginTest(){
        loginPage.navigateToPage(loginURL);
        loginPage.enterUserName(validUsernameTestData);
        loginPage.enterUserPassword(validUserPasswordTestData);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isUserProfileIconShown());
    }

    @Test
    public void loginWithBothEmptyFieldsTest(){
        loginPage.navigateToPage(loginURL);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorNotificationShown());
        Assert.assertTrue(loginPage.isLoginIconShown());
    }

    @Test
    public void loginWithEmptyUserNameTest(){
        loginPage.navigateToPage(loginURL);
        loginPage.enterUserPassword(validUserPasswordTestData);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorNotificationShown());
        Assert.assertTrue(loginPage.isLoginIconShown());
    }

    @Test
    public void loginWithEmptyPasswordTest(){
        loginPage.navigateToPage(loginURL);
        loginPage.enterUserName(validUsernameTestData);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorNotificationShown());
        Assert.assertTrue(loginPage.isLoginIconShown());
    }

    @Test
    public void openResetPasswordPageFromLoginFormTest(){
        loginPage.navigateToPage(loginURL);
        loginPage.clickCanAccessAccountLink();
        Assert.assertTrue(forgotLoginPage.isForgotLoginFormShown());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}