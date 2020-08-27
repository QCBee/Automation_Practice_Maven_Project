package view_ticket_tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DeleteCommentPage;
import pages.HomePage;
import pages.LoginPage;
import pages.TicketPage;
import utils.WebDriverFactory;

public class ViewTicketTest {
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
    public void openTicketTest(){
        //Open ticket by url
        ticketPage.navigateToTicketPage(ticketUrl);
        Assert.assertTrue(ticketPage.isProjectAvatarIconShown());
        Assert.assertTrue(ticketPage.isIssueLinkShown());

        //Verify all available buttons on Command Bar Section
        Assert.assertTrue(ticketPage.isEditButtonOnCommandBarShown());
        Assert.assertTrue(ticketPage.isCommentButtonOnCommandBarShown());
        Assert.assertTrue(ticketPage.isAssignButtonOnCommandBarShown());
        Assert.assertTrue(ticketPage.isMoreDropDownButtonOnCommandBarShown());
        Assert.assertTrue(ticketPage.isWorkflowButtonOnCommandBarShown());
        Assert.assertTrue(ticketPage.isBacklogButtonOnCommandBarShown());
        Assert.assertTrue(ticketPage.isSelectedForDevelopmentButtonShown());
        Assert.assertTrue(ticketPage.isShareButtonOncommandBarShown());
        Assert.assertTrue(ticketPage.isExportButtonOnCommandBarShown());

        //Verify Details section
        Assert.assertTrue(ticketPage.isDetailsLabelShown());
        Assert.assertTrue(ticketPage.isTypeIssueFieldShown());
        Assert.assertTrue(ticketPage.isPriorityIssueFieldShown());
        Assert.assertTrue(ticketPage.isLabelsIssueFieldShown());
        Assert.assertTrue(ticketPage.isStatusIssueFieldShown());
        Assert.assertTrue(ticketPage.isResolutionIssueFieldShown());

        //Verify Description section
        Assert.assertTrue(ticketPage.isDescriptionAreaShown());
        Assert.assertTrue(ticketPage.isDescriptionLabelShown());

        //Verify Attachments section
        Assert.assertTrue(ticketPage.isAttachmentLabelShown());
        Assert.assertTrue(ticketPage.isAttachmentAreaShown());
        Assert.assertTrue(ticketPage.isBrowseButtonShownOnAttachmentArea());
        Assert.assertTrue(ticketPage.isMoreButtonForAttachmentSectionShown());

        //Verify Activity section
        Assert.assertTrue(ticketPage.isActivitySectionShown());
        Assert.assertTrue(ticketPage.isAllTabNotSelectedShown());
        Assert.assertTrue(ticketPage.isCommentTabSelected());
        Assert.assertTrue(ticketPage.isAddCommentFooterButtonEnabled());
        Assert.assertTrue(ticketPage.isWorkLogTabNotSelectedShown());
        Assert.assertTrue(ticketPage.isHistoryTabNotSelectedShown());
        Assert.assertTrue(ticketPage.isActivityTabNotSelectedShown());
        Assert.assertTrue(ticketPage.isJigitTabNotSelectedShown());
        //TODO Add verifications for People section
        //TODO Add verification for Dates section
        //TODO Add verification for Development section
        //TODO Add verification for Agile section
    }

    //TODO Add test for collapse all section
    //TODO Add test for expanding all section
    //TODO Add test for viewing All tab
    //TODO Add test for viewing Work Log tab
    //TODO Add test for viewing history tab
    //TODO Add test for viewing activity tab
    //TODO Add test for viewing Jigit tab



    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}


