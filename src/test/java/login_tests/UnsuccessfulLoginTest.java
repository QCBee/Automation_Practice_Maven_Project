package login_tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.WebDriverFactory;

public class UnsuccessfulLoginTest {
    LoginPage loginPage = null;

    //Test data locators
    String loginURL = "https://jira.hillel.it/secure/Dashboard.jspa";
    String validUsernameTestData = "webinar5";
    String validUserPasswordTestData = "webinar5";
    String invalidUserNameTestData = "invalidName";
    String invalidUserPasswordTestData = "invalidPass";
    String emptyUserNameTestData = "";
    String emptyUserPasswordTestData = "";

    @Parameters({"browserName"})
    @BeforeMethod
    public void setUp(String browserName) {
        WebDriverFactory.createInstance(browserName);
        System.out.println("Test is started for following browser: " + browserName);
        loginPage = new LoginPage(WebDriverFactory.getDriver());
    }

    @DataProvider(name = "UnsuccessfullLoginsTestDataList")
    public Object[][] createData(){
        return new Object[][]{
                {validUsernameTestData, invalidUserPasswordTestData},
                {invalidUserNameTestData, validUserPasswordTestData},
                {validUsernameTestData, emptyUserPasswordTestData},
                {emptyUserNameTestData, validUserPasswordTestData},
                {emptyUserNameTestData, emptyUserPasswordTestData}
        };
    }

    @Test(dataProvider = "UnsuccessfullLoginsTestDataList")
    public void unsuccessfulLoginTest(String userName, String userPassword){
        loginPage.navigateToLoginPage(loginURL);
        loginPage.enterUserName(userName);
        loginPage.enterUserPassword(userPassword);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorNotificationShown());
        Assert.assertTrue(loginPage.isLoginIconShown());
    }

    @AfterMethod
    public void tearDown(){
        WebDriverFactory.closeBrowser();
    }
}
