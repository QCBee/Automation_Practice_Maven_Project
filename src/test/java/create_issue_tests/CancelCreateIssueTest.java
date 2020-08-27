package create_issue_tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;
import utils.WebDriverFactory;

public class CancelCreateIssueTest {
    LoginPage loginPage = null;
    HomePage homePage = null;
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


    @Parameters({"browserName"})
    @BeforeMethod
    public void setUp(String browserName) {
        WebDriverFactory.createInstance(browserName);
        System.out.println("Test is started for following browser: " + browserName);
        loginPage = new LoginPage(WebDriverFactory.getDriver());
        homePage = new HomePage(WebDriverFactory.getDriver());
        createIssuePage = new CreateIssuePage(WebDriverFactory.getDriver());

        loginPage.navigateToLoginPage(loginURL);
        loginPage.enterUserName(validUsernameTestData);
        loginPage.enterUserPassword(validUserPasswordTestData);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isUserProfileIconShown());
    }


    @Test
    public void cancelCreatingTicketAfterFillingOutAllRequiredFieldsTest(){
        homePage.clickCreateIssueButton();
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpShown());

        createIssuePage.enterProject(projectNameValue);
        Assert.assertTrue(createIssuePage.isTypeIssueInputEnabled());

        createIssuePage.enterTypeIssue(issueTypeValue);
        Assert.assertTrue(createIssuePage.isSummaryInputEnabled());

        createIssuePage.enterSummary(summaryValue);

        createIssuePage.enterReporter(reporterValue);

        createIssuePage.clickCancelButton();

        createIssuePage.acceptAlertWithRetry(AttempsType.SHORT_ATTEMPT.getAttemptAmount(), TimeOutTypes.LOW.getTimeOutInSec());
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpNotShown());
    }

    @Test
    public void returnCreatingTicketTest(){
        homePage.clickCreateIssueButton();
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpShown());

        createIssuePage.enterProject(projectNameValue);
        Assert.assertTrue(createIssuePage.isTypeIssueInputEnabled());

        createIssuePage.enterTypeIssue(issueTypeValue);
        Assert.assertTrue(createIssuePage.isSummaryInputEnabled());

        createIssuePage.enterSummary(summaryValue);

        createIssuePage.enterReporter(reporterValue);

        createIssuePage.clickCancelButton();

        createIssuePage.dismissAlertWithRetry(AttempsType.SHORT_ATTEMPT.getAttemptAmount(), TimeOutTypes.LOW.getTimeOutInSec());
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpShown());
        Assert.assertTrue(createIssuePage.doProjectNameValueMatch(projectNameValue));
        Assert.assertTrue(createIssuePage.doTypeIssueValueMatch(issueTypeValue));
        Assert.assertTrue(createIssuePage.doSummaryValueMatch(summaryValue));
        Assert.assertTrue(createIssuePage.doReporterValueMatch(reporterValue));
    }

    @Test
    public void closeCreateTicketPopUpWithoutEnteringAnyDataTest(){
        homePage.clickCreateIssueButton();
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpShown());

        createIssuePage.clickCancelButton();
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpNotShown());
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.closeBrowser();
    }
}
