package delete_commet_from_ticket_tests;

import creds_and_pathes.Credentials;
import creds_and_pathes.UrlPathes;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DeleteCommentPage;
import pages.HomePage;
import pages.LoginPage;
import pages.TicketPage;
import test_data.CommentTestData;
import utils.WebDriverFactory;

public class CancelDeletingCommentTest {
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
    public void cancelDeletingCommentTest(){
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

        deleteCommentPage.clickCancelButton();
        Assert.assertTrue(deleteCommentPage.isDeletePopUpClosed());
        Assert.assertTrue(ticketPage.isUpdateIssueNotificationNotShown());
        Assert.assertTrue(ticketPage.isAddedCommentShown());

        ticketPage.clickDeleteCommentIcon();
        Assert.assertTrue(deleteCommentPage.isDeletePopUpShown());

        deleteCommentPage.clickDeleteButton();
        Assert.assertTrue(ticketPage.isUpdateIssueNotificationShown());
        Assert.assertTrue(ticketPage.isAnyCommentNotAdded());

    }

    @Test
    public void cancelCreatingCommentToTicketWithConfirmationTest(){
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

        ticketPage.clickCancelButton();

        ticketPage.acceptAlert();
        Assert.assertTrue(ticketPage.isCommentTabSelected());
        Assert.assertTrue(ticketPage.isAddCommentFooterButtonEnabled());
        Assert.assertTrue(ticketPage.isAnyCommentNotAdded());
        Assert.assertTrue(ticketPage.isUpdateIssueNotificationNotShown());
    }

    @Test
    public void cancelCreatingCommentToTicketWithoutConfirmationTest(){
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

        ticketPage.clickCancelButton();

        ticketPage.dismissAlert();
        Assert.assertTrue(ticketPage.isAddCommentAreaShown());
        Assert.assertTrue(ticketPage.isTextModeForCommentSelected());
        Assert.assertTrue(ticketPage.isAddCommentButtonEnabled());
        Assert.assertTrue(ticketPage.isCancelButtonEnabled());
        Assert.assertTrue(ticketPage.isUpdateIssueNotificationNotShown());
    }

    @AfterTest
    public void tearDown(){
        WebDriverFactory.getDriver().quit();
    }
}
