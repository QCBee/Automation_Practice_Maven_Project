package login_tests;

import creds_and_pathes.UrlPathes;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ForgotLoginPage;
import pages.LoginPage;
import utils.WebDriverFactory;

public class ResetLoginTest {
    LoginPage loginPage = null;
    ForgotLoginPage forgotLoginPage = null;

    @Parameters({"browserName"})
    @BeforeMethod
    public void setUp(String browserName) {
        System.out.println("Test is started for following browser: " + browserName);
        WebDriverFactory.createInstance(browserName);
        loginPage = new LoginPage(WebDriverFactory.getDriver());
        forgotLoginPage = new ForgotLoginPage(WebDriverFactory.getDriver());
    }

    @Test
    public void openResetPasswordPageFromLoginFormTest(){
        loginPage.navigateToLoginPage(UrlPathes.loginUrl);
        loginPage.clickCanAccessAccountLink();
        Assert.assertTrue(forgotLoginPage.isForgotLoginFormShown());
    }

    @AfterMethod
    public void tearDown(){
        WebDriverFactory.getDriver().quit();
    }

}
