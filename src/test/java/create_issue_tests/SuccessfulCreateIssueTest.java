package create_issue_tests;

import creds_and_pathes.Credentials;
import creds_and_pathes.UrlPathes;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CreateIssuePage;
import pages.HomePage;
import pages.LoginPage;
import pages.TicketPage;
import utils.WebDriverFactory;

public class SuccessfulCreateIssueTest {
    LoginPage loginPage = null;
    HomePage homePage = null;
    TicketPage ticketPage = null;
    CreateIssuePage createIssuePage = null;

    //Test data for create issue tests
    String projectNameValue = "Webinar (WEBINAR)";
    String issueTypeValue = "Task";
    String summaryValue = "Test Summary";
    String reporterValue = "webinar5";
    String descriptionValue = "Test description";
    String priorityValue = "Lowest";

    @Parameters({"browserName"})
    @BeforeMethod
    public void setUp(String browserName) {
        WebDriverFactory.createInstance(browserName);
        System.out.println("Test is started for following browser: " + browserName);
        loginPage = new LoginPage(WebDriverFactory.getDriver());
        homePage = new HomePage(WebDriverFactory.getDriver());
        ticketPage = new TicketPage(WebDriverFactory.getDriver());
        createIssuePage = new CreateIssuePage(WebDriverFactory.getDriver());

        loginPage.navigateToLoginPage(UrlPathes.loginUrl);
        loginPage.enterUserName(Credentials.userName);
        loginPage.enterUserPassword(Credentials.userPass);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isUserProfileIconShown());
    }

    @Test()
    public void createTicketWithRequiredFieldsTest() {
        homePage.clickCreateIssueButton();
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpShown());
        Assert.assertTrue(createIssuePage.isProjectInputEnabled());
        Assert.assertTrue(createIssuePage.isTypeIssueInputEnabled());
        Assert.assertTrue(createIssuePage.isSummaryInputEnabled());

        createIssuePage.enterProject(projectNameValue);
        createIssuePage.enterTypeIssue(issueTypeValue);
        createIssuePage.enterSummary(summaryValue);
        createIssuePage.enterReporter(reporterValue);
        createIssuePage.clickCreateButton();
        Assert.assertTrue(homePage.isIssueCreateNotificationShown());

        homePage.clickIssueLinkOnNotification();
        Assert.assertTrue(ticketPage.isViewIssuePageShown());

        Assert.assertTrue(ticketPage.doProjectValueMatch());
        Assert.assertTrue(ticketPage.doSummaryValueMatch());
        Assert.assertTrue(ticketPage.doTypeIssueValueMatch());
        Assert.assertTrue(ticketPage.doReporterValueMatch());
    }

    @Test
    public void createTicketWithAllFieldsTest(){
        homePage.clickCreateIssueButton();
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpShown());

        createIssuePage.enterProject(projectNameValue);
        Assert.assertTrue(createIssuePage.isTypeIssueInputEnabled());

        createIssuePage.enterTypeIssue(issueTypeValue);
        Assert.assertTrue(createIssuePage.isSummaryInputEnabled());

        createIssuePage.enterSummary(summaryValue);

        createIssuePage.enterReporter(reporterValue);

        createIssuePage.selectTextModeForDescriptionInput();

        createIssuePage.enterDescription(descriptionValue);

        createIssuePage.enterPriorityInput(priorityValue);

        createIssuePage.clickCreateButton();
        Assert.assertTrue(homePage.isIssueCreateNotificationShown());

        homePage.clickIssueLinkOnNotification();
        Assert.assertTrue(ticketPage.isViewIssuePageShown());
        Assert.assertTrue(ticketPage.doProjectValueMatch());
        Assert.assertTrue(ticketPage.doSummaryValueMatch());
        Assert.assertTrue(ticketPage.doTypeIssueValueMatch());
        Assert.assertTrue(ticketPage.doReporterValueMatch());
        Assert.assertTrue(ticketPage.doPriorityValueMatch());
        Assert.assertTrue(ticketPage.doDescriptionValueMatch());
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.getDriver().quit();
    }
}
