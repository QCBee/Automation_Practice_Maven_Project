package create_issue_tests;

import creds_and_pathes.Credentials;
import creds_and_pathes.UrlPathes;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;
import test_data.IssueTestData;
import utils.WebDriverFactory;

public class CancelCreateIssueTest {
    LoginPage loginPage = null;
    HomePage homePage = null;
    CreateIssuePage createIssuePage = null;

    @Parameters({"browserName"})
    @BeforeMethod
    public void setUp(String browserName) {
        WebDriverFactory.createInstance(browserName);
        System.out.println("Test is started for following browser: " + browserName);
        loginPage = new LoginPage(WebDriverFactory.getDriver());
        homePage = new HomePage(WebDriverFactory.getDriver());
        createIssuePage = new CreateIssuePage(WebDriverFactory.getDriver());

        loginPage.navigateToLoginPage(UrlPathes.loginUrl);
        loginPage.enterUserName(Credentials.userName);
        loginPage.enterUserPassword(Credentials.userPass);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isUserProfileIconShown());
    }


    @Test
    public void cancelCreatingTicketAfterFillingOutAllRequiredFieldsTest(){
        homePage.clickCreateIssueButton();
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpShown());

        createIssuePage.enterProject(IssueTestData.PROJECT_NAME_VALUE);
        Assert.assertTrue(createIssuePage.isTypeIssueInputEnabled());

        createIssuePage.enterTypeIssue(IssueTestData.ISSUE_TYPE_VALUE);
        Assert.assertTrue(createIssuePage.isSummaryInputEnabled());

        createIssuePage.enterSummary(IssueTestData.SUMMARY_VALUE);

        createIssuePage.enterReporter(IssueTestData.REPORTER_VALUE);

        createIssuePage.clickCancelButton();

        createIssuePage.acceptAlertWithRetry(AttempsType.SHORT_ATTEMPT.getAttemptAmount(), TimeOutTypes.LOW.getTimeOutInSec());
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpNotShown());
    }

    @Test
    public void returnCreatingTicketTest(){
        homePage.clickCreateIssueButton();
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpShown());

        createIssuePage.enterProject(IssueTestData.PROJECT_NAME_VALUE);
        Assert.assertTrue(createIssuePage.isTypeIssueInputEnabled());

        createIssuePage.enterTypeIssue(IssueTestData.ISSUE_TYPE_VALUE);
        Assert.assertTrue(createIssuePage.isSummaryInputEnabled());

        createIssuePage.enterSummary(IssueTestData.SUMMARY_VALUE);

        createIssuePage.enterReporter(IssueTestData.REPORTER_VALUE);

        createIssuePage.clickCancelButton();

        createIssuePage.dismissAlertWithRetry(AttempsType.SHORT_ATTEMPT.getAttemptAmount(), TimeOutTypes.LOW.getTimeOutInSec());
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpShown());
        Assert.assertTrue(createIssuePage.doProjectNameValueMatch(IssueTestData.PROJECT_NAME_VALUE));
        Assert.assertTrue(createIssuePage.doTypeIssueValueMatch(IssueTestData.ISSUE_TYPE_VALUE));
        Assert.assertTrue(createIssuePage.doSummaryValueMatch(IssueTestData.SUMMARY_VALUE));
        Assert.assertTrue(createIssuePage.doReporterValueMatch(IssueTestData.REPORTER_VALUE));
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
        WebDriverFactory.getDriver().quit();
    }
}
