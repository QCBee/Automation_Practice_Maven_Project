package login_tests;

import creds_and_pathes.UrlPathes;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import test_data.LoginTestData;
import utils.WebDriverFactory;

public class UnsuccessfulLoginTest {
    LoginPage loginPage = null;

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
                {LoginTestData.VALID_USER_NAME, LoginTestData.INVALID_USER_PASS},
                {LoginTestData.INVALID_USER_NAME, LoginTestData.VALID_USER_PASS},
                {LoginTestData.VALID_USER_NAME, LoginTestData.EMPTY_USER_PASS},
                {LoginTestData.EMPTY_USER_NAME, LoginTestData.VALID_USER_PASS},
                {LoginTestData.EMPTY_USER_NAME, LoginTestData.EMPTY_USER_PASS}
        };
    }

    @Test(dataProvider = "UnsuccessfullLoginsTestDataList")
    public void unsuccessfulLoginTest(String userName, String userPassword){
        loginPage.navigateToLoginPage(UrlPathes.loginUrl);
        loginPage.enterUserName(userName);
        loginPage.enterUserPassword(userPassword);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorNotificationShown());
        Assert.assertTrue(loginPage.isLoginIconShown());
    }

    @AfterMethod
    public void tearDown(){
        WebDriverFactory.getDriver().quit();
    }
}
