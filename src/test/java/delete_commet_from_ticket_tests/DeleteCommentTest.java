package delete_commet_from_ticket_tests;

import creds_and_pathes.Credentials;
import creds_and_pathes.UrlPathes;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DeleteCommentPage;
import pages.HomePage;
import pages.LoginPage;
import pages.TicketPage;
import test_data.CommentTestData;
import utils.WebDriverFactory;

public class DeleteCommentTest {
    LoginPage loginPage = null;
    HomePage homePage = null;
    TicketPage ticketPage = null;
    DeleteCommentPage deleteCommentPage= null;

    @Parameters({"browserName"})
    @BeforeTest
    public void setUp(String browserName) {
        WebDriverFactory.createInstance(browserName);
        loginPage = new LoginPage(WebDriverFactory.getDriver());
        homePage = new HomePage(WebDriverFactory.getDriver());
        ticketPage = new TicketPage(WebDriverFactory.getDriver());
        deleteCommentPage = new DeleteCommentPage(WebDriverFactory.getDriver());

        loginPage.navigateToLoginPage(UrlPathes.loginUrl);
        loginPage.enterUserName(Credentials.userName);
        loginPage.enterUserPassword(Credentials.userPass);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isUserProfileIconShown());
    }

    @Test
    public void deleteCommentFromTicketTest(){
        ticketPage.navigateToTicketPage(UrlPathes.ticketUrl);
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

        ticketPage.enterComment(CommentTestData.NEW_COMMENT_VALUE);
        Assert.assertTrue(ticketPage.isAddCommentButtonEnabled());

        ticketPage.clickAddCommentButton();
        Assert.assertTrue(ticketPage.isLogIssueAreaShown());
        Assert.assertTrue(ticketPage.isAddedCommentShown());
        Assert.assertTrue(ticketPage.isDeleteIconShown());

        ticketPage.clickDeleteCommentIcon();
        Assert.assertTrue(deleteCommentPage.isDeletePopUpShown());
        Assert.assertTrue(deleteCommentPage.isDeleteButtonShown());
        Assert.assertTrue(deleteCommentPage.isCancelButtonShown());

        deleteCommentPage.clickDeleteButton();
        Assert.assertTrue(deleteCommentPage.isDeletePopUpClosed());
        Assert.assertTrue(ticketPage.isUpdateIssueNotificationShown());
        Assert.assertTrue(ticketPage.isAnyCommentNotAdded());
    }

    @AfterTest
    public void tearDown(){
        WebDriverFactory.getDriver().quit();
    }
}
