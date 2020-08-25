package login_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.WebDriverFactory;

public class SuccessfulLoginTest {
    LoginPage loginPage = null;
    HomePage homePage = null;

    //Test data locators
    String loginURL = "https://jira.hillel.it/secure/Dashboard.jspa";
    String validUsernameTestData = "webinar5";
    String validUserPasswordTestData = "webinar5";

    @Parameters({"browserName"})
    @BeforeMethod
    public void setUp(String browserName) {
        WebDriverFactory.createInstance(browserName);
        System.out.println("Test is started for following browser: " + browserName);
        loginPage = new LoginPage(WebDriverFactory.getDriver());
        homePage = new HomePage(WebDriverFactory.getDriver());
    }

    @DataProvider(name = "SuccessFullLoginsTestDataList")
    public Object[][] createData(){
        return new Object[][]{
                {validUsernameTestData, validUserPasswordTestData},
        };
    }

    @Test(dataProvider = "SuccessFullLoginsTestDataList")
    public void successfulLoginTest(String userName, String userPassword){
        loginPage.navigateToLoginPage(loginURL);
        loginPage.enterUserName(userName);
        loginPage.enterUserPassword(userPassword);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isUserProfileIconShown());
    }

    @AfterMethod
    public void tearDown(){
        WebDriverFactory.closeBrowser();
    }
}