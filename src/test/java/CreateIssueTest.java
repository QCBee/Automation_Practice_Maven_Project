import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CreateIssuePage;
import pages.HomePage;
import pages.LoginPage;
import pages.TicketPage;
import utils.WebDriverFactory;

public class CreateIssueTest {
    WebDriver driver = null;
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
        driver = WebDriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        ticketPage = new TicketPage(driver);
        createIssuePage = new CreateIssuePage(driver);

        loginPage.navigateToLoginPage(loginURL);
        loginPage.enterUserName(validUsernameTestData);
        loginPage.enterUserPassword(validUserPasswordTestData);
        loginPage.clickLoginButton();
        Assert.assertTrue(homePage.isUserProfileIconShown());
    }

    @Test
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

        createIssuePage.acceptAlert();
//        createIssuePage.isCreateIssuePopUpNotShown();
        Assert.assertTrue(createIssuePage.isCreateIssuePopUpNotShown());


        //TODO Add asserts for NOT showinf create issue pop-up - wait with for+time

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

        createIssuePage.dismissAlert();
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

//    @Test
//    public void unsuccessfulCreateTicketTest(){
//        //TODO Add test for unsuccessful flow (validation for required fields)
//
//    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}