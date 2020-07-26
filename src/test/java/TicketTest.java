
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DeleteCommentPage;
import pages.HomePage;
import pages.LoginPage;
import pages.TicketPage;
import utils.WebDriverFactory;



public class TicketTest {
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


    //Locators for expand/collapse tests
//    String detailsTouchAreaLocator = "//*[contains(text(),'Details')]";
//    String descriptionTouchAreaLocator = "//*[contains(text(),'Description')]";
//    String detailsExpandedAreaLocator = "issuedetails";
//    String attachmentTouchAreaLocator = "//*[contains(text(),'Attachments')]";

    @BeforeTest
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
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
    public void openTicketTest(){
        ticketPage.navigateToTicketPage(ticketUrl);
        Assert.assertTrue(ticketPage.isProjectAvatarIconShown());
        Assert.assertTrue(ticketPage.isIssueLinkShown());
        Assert.assertTrue(ticketPage.isEditButtonOnCommandBarShown());
        Assert.assertTrue(ticketPage.isCommentButtonOnCommandBarShown());
        Assert.assertTrue(ticketPage.isAssignButtonOnCommandBarShown());
        Assert.assertTrue(ticketPage.isMoreDropDownButtonOnCommandBarShown());
        Assert.assertTrue(ticketPage.isWorkflowButtonOnCommandBarShown());
        Assert.assertTrue(ticketPage.isDetailsLabelShown());
        Assert.assertTrue(ticketPage.isTypeIssueFieldShown());
        Assert.assertTrue(ticketPage.isPriorityIssueFieldShown());
        Assert.assertTrue(ticketPage.isStatusIssueFieldShown());
        Assert.assertTrue(ticketPage.isResolutionIssueFieldShown());
        Assert.assertTrue(ticketPage.isDescriptionAreaShown());
        Assert.assertTrue(ticketPage.isDescriptionLabelShown());
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

    @Test
    public void deleteCommentFromTicketTest(){
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

    @Test
    public void cancelDeletingCommentTest(){
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

        ticketPage.clickCancelButton();

        ticketPage.acceptAlert();
        Assert.assertTrue(ticketPage.isCommentTabSelected());
        Assert.assertTrue(ticketPage.isAddCommentFooterButtonEnabled());
        Assert.assertTrue(ticketPage.isAnyCommentNotAdded());
        Assert.assertTrue(ticketPage.isUpdateIssueNotificationNotShown());
    }

    @Test
    public void cancelCreatingCommentToTicketWithoutConfirmationTest(){
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
        driver.quit();
    }
}
