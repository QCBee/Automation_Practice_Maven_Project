package login_tests;

import creds_and_pathes.UrlPathes;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import test_data.LoginTestData;
import utils.WebDriverFactory;

public class SuccessfulLoginTest {
    LoginPage loginPage = null;
    HomePage homePage = null;

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
                {LoginTestData.VALID_USER_NAME,LoginTestData.VALID_USER_PASS},
        };
    }

    @Test(dataProvider = "SuccessFullLoginsTestDataList")
    public void successfulLoginTest(String userName, String userPassword){
        loginPage.navigateToLoginPage(UrlPathes.loginUrl);
        loginPage.enterUserName(userName);
        loginPage.enterUserPassword(userPassword);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isUserProfileIconShown());
    }

    @AfterMethod
    public void tearDown(){
        WebDriverFactory.getDriver().quit();
    }
}