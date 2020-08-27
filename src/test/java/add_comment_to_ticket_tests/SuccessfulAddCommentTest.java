package add_comment_to_ticket_tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DeleteCommentPage;
import pages.HomePage;
import pages.LoginPage;
import pages.TicketPage;
import utils.WebDriverFactory;

public class SuccessfulAddCommentTest {
    WebDriver driver =null;
    LoginPage loginPage = null;
    HomePage homePage = null;
    TicketPage ticketPage = null;
    DeleteCommentPage deleteCommentPage= null;

    //Test data for preconditions
    String loginURL = "https://jira.hillel.it/secure/Dashboard.jspa";
    String validUsernameTestData = "webinar5";
    String validUserPasswordTestData = "webinar5";

    //Test data for view ticket test
    String ticketUrl = "https://jira.hillel.it/browse/WEBINAR-12303";

    //Test data for tests with comments
    String commentValue = "I am a test comment";

    @Parameters({"browserName"})
    @BeforeTest
    public void setUp(String browserName) {
        WebDriverFactory.createInstance(browserName);
        driver = WebDriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        ticketPage = new TicketPage(driver);
        deleteCommentPage = new DeleteCommentPage(driver);

        loginPage.navigateToLoginPage(loginURL);
        loginPage.enterUserName(validUsernameTestData);
        loginPage.enterUserPassword(validUserPasswordTestData);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isUserProfileIconShown());
    }

    @Test
    public void createCommentToTicketTest(){
        ticketPage.navigateToTicketPage(ticketUrl);
        Assert.assertTrue(ticketPage.isCommentTabSelected());
        Assert.assertTrue(ticketPage.isAddCommentFooterButtonEnabled());
        Assert.assertTrue(ticketPage.isAnyCommentNotAdded());

        ticketPage.clickAddCommentFooterButton();
        Assert.assertTrue(ticketPage.isAddCommentAreaShown());
        Assert.assertTrue(ticketPage.isAddCommentFooterButtonDisabled());
        Assert.assertTrue(ticketPage.isAddCommentButtonDisabled());
        Assert.assertTrue(ticketPage.isCancelButtonEnabled());

        ticketPage.selectTextModeForComment();
        Assert.assertTrue(ticketPage.isTextModeForCommentSelected());

        ticketPage.enterComment(commentValue);
        Assert.assertTrue(ticketPage.isAddCommentButtonEnabled());

        ticketPage.clickAddCommentButton();
        Assert.assertTrue(ticketPage.isLogIssueAreaShown());
        Assert.assertTrue(ticketPage.isAddedCommentShown());

        ticketPage.clickDeleteCommentIcon();

        deleteCommentPage.clickDeleteButton();
        Assert.assertTrue(ticketPage.isUpdateIssueNotificationShown());
        Assert.assertTrue(ticketPage.isAnyCommentNotAdded());
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
