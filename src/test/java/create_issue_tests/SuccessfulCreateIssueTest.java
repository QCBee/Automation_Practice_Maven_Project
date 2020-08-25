package create_issue_tests;

import org.openqa.selenium.WebDriver;
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

    //Test data for preconditions
    String loginURL = "https://jira.hillel.it/secure/Dashboard.jspa";
    String validUsernameTestData = "webinar5";
    String validUserPasswordTestData = "webinar5";

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

        loginPage.navigateToLoginPage(loginURL);
        loginPage.enterUserName(validUsernameTestData);
        loginPage.enterUserPassword(validUserPasswordTestData);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isUserProfileIconShown());
    }

    @DataProvider(name = "IssueTypesTestData")
    public Object[][] createData() {
        return new Object[][]{
                {projectNameValue, "Task", summaryValue, reporterValue, "Task"},
                {projectNameValue, "Bug", summaryValue, reporterValue, "Bug"},
                {projectNameValue, "Epic", summaryValue, reporterValue, "Epic"},
                {projectNameValue, "User Story", summaryValue, reporterValue, "User Story"},
                {reporterValue, "Test", summaryValue, reporterValue, "Test"},
                {reporterValue, "Story", summaryValue, reporterValue, "Story"}
        };
    }

    @Test(dataProvider = "IssueTypesTestData")
    public void createTicketWithRequiredFieldsTest(String projectName, String issueType, String summary, String reporter, String createdType) {
        homePage.clickCreateIssueButton();
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpShown());
        Assert.assertTrue(createIssuePage.isProjectInputEnabled());
        Assert.assertTrue(createIssuePage.isTypeIssueInputEnabled());
        Assert.assertTrue(createIssuePage.isSummaryInputEnabled());

        createIssuePage.enterProject(projectName);
        createIssuePage.enterTypeIssue(issueType);
        createIssuePage.enterSummary(summary);
        createIssuePage.enterReporter(reporter);
        createIssuePage.clickCreateButton();
        Assert.assertTrue(homePage.isIssueCreateNotificationShown());

        homePage.clickIssueLinkOnNotification();
        Assert.assertTrue(ticketPage.isViewIssuePageShown());

        Assert.assertTrue(ticketPage.doProjectValueMatch());
        Assert.assertTrue(ticketPage.doSummaryValueMatch());
        Assert.assertTrue(ticketPage.doTypeIssueValueMatch());
        Assert.assertTrue(ticketPage.doReporterValueMatch());
    }

    @DataProvider



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
        WebDriverFactory.closeBrowser();
    }
}
