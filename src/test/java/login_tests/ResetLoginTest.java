package login_tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ForgotLoginPage;
import pages.LoginPage;
import utils.WebDriverFactory;

public class ResetLoginTest {
    WebDriver driver = null;
    LoginPage loginPage = null;
    ForgotLoginPage forgotLoginPage = null;

    //Test data locators
    String loginURL = "https://jira.hillel.it/secure/Dashboard.jspa";

    @Parameters({"browserName"})
    @BeforeMethod
    public void setUp(String browserName) {
        System.out.println("Test is started for following browser: " + browserName);
        WebDriverFactory.createInstance(browserName);
        driver = WebDriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        forgotLoginPage = new ForgotLoginPage(driver);
    }

    @Test
    public void openResetPasswordPageFromLoginFormTest(){
        loginPage.navigateToLoginPage(loginURL);

        loginPage.clickCanAccessAccountLink();
        Assert.assertTrue(forgotLoginPage.isForgotLoginFormShown());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
