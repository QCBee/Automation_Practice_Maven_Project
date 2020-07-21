import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.WebDriverFactory;

public class CreateIssue {
    WebDriver driver = null;
    String loginURL = "https://jira.hillel.it/secure/Dashboard.jspa";
    String usernameLoginLocator = "login-form-username";
    String userpasswordLoginLocator = "login-form-password";
    String loginButtonLocator = "login";
    String validUsernameTestData = "webinar5";
    String validUserPasswordTestData = "webinar5";
    String userProfileIconLocator = "[alt = 'User profile for webinar5']";
    String createIssueButtonOnDashboardLocator = "create_link";
    String createIssuePopPupLocator = "create-issue-dialog";
    String projectInputLocator = "project-field";
    String projectNameValue = "Webinar (WEBINAR)";
    String issueTypeInputLocator = "issuetype-field";
    String issueTypeValue = "Task";
    String summaryInputLocator = "summary";
    String summaryValue = "Test Summary";
    String reporterInputLocator = "reporter-field";
    String reporterValue = "webinar5";
    String createButtonLocator = "create-issue-submit";
    String issueCreatedNotificationLocator = "aui-flag-container";
    String linkToCreatedIssueLocator = "//*[contains(text(),' - Test Summary')]";
    String viewIssuePageLocator = ".issue-navigator";
    String projectValueOnViewTicketLocator = "//*[@id='project-name-val'][@href='/browse/WEBINAR']";
    String summaryValueOnViewTicketLocator = "//h1[contains(text(),'Test Summary')]";
    String reporterValueOnViewJiraTicketLocator = "[alt = 'webinar5']";
    String typeIssueValueOnViewJiraTicketLocator = "[title='Task - A task that needs to be done.']";
    String cancelButtonLocator = "[title = 'Press undefined+` to cancel']";
    String descriptionInputLocator = "tinymce";
    String descriptionValue = "Test description";
    String priorityInputLocator = "priority-single-select";
    String priorityValue = "Lowest";
    String priorityValueOnViewTicketPageLocator = "[title = 'Lowest - Trivial problem with little or no impact on progress.']";
    String descriptionValueOnViewTicketPageLocator = "[contains(text(),'Test description')]";
    String textModeOnDescriptionLocator = "[data-mode = 'source']";

    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        driver.get(loginURL);
        driver.findElement(By.id(usernameLoginLocator)).sendKeys(validUsernameTestData);
        driver.findElement(By.id(userpasswordLoginLocator)).sendKeys(validUserPasswordTestData);
        driver.findElement(By.id(loginButtonLocator)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        boolean userProfileIconIsPresent = (wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(userProfileIconLocator)))).isDisplayed();
        Assert.assertEquals(userProfileIconIsPresent, true);

    }

    @Test
    public void successfulCreateStoryWithRequiredFieldsTest() {
        driver.findElement(By.id(createIssueButtonOnDashboardLocator)).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20).getSeconds());
        boolean createIssuePopUpIsPresent = (wait.until(ExpectedConditions.presenceOfElementLocated(By.id(createIssuePopPupLocator)))).isEnabled();
        Assert.assertEquals(createIssuePopUpIsPresent, true);

        driver.findElement(By.id(projectInputLocator)).clear();
        driver.findElement(By.id(projectInputLocator)).sendKeys(projectNameValue);
        driver.findElement(By.id(projectInputLocator)).sendKeys(Keys.ENTER);
        driver.findElement(By.id(issueTypeInputLocator)).isEnabled();
        driver.findElement(By.id(issueTypeInputLocator)).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id(issueTypeInputLocator)).clear();
        driver.findElement(By.id(issueTypeInputLocator)).sendKeys(issueTypeValue);
        driver.findElement(By.id(issueTypeInputLocator)).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(summaryInputLocator))).isEnabled();
        driver.findElement(By.id(summaryInputLocator)).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id(summaryInputLocator)).sendKeys(summaryValue);
        driver.findElement(By.id(reporterInputLocator)).clear();
        driver.findElement(By.id(reporterInputLocator)).sendKeys(reporterValue);
        driver.findElement(By.id(reporterInputLocator)).sendKeys(Keys.ENTER);
        driver.findElement(By.id(createButtonLocator)).click();

        boolean createIssueNotificationIsPresent = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(issueCreatedNotificationLocator))).isEnabled();
        Assert.assertEquals(createIssueNotificationIsPresent, true);

        driver.findElement(By.xpath(linkToCreatedIssueLocator)).click();
        boolean viewJiraTicketPageIsPresent = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(viewIssuePageLocator))).isDisplayed();
        Assert.assertEquals(viewJiraTicketPageIsPresent,true);

        boolean doProjectValueMatch = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(projectValueOnViewTicketLocator))).isDisplayed();
        Assert.assertEquals(doProjectValueMatch,true);

        boolean doSummaryValueMatch = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(summaryValueOnViewTicketLocator))).isDisplayed();
        Assert.assertEquals(doSummaryValueMatch,true);

        boolean doTypeIssueValueMatch = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(typeIssueValueOnViewJiraTicketLocator))).isDisplayed();
        Assert.assertEquals(doTypeIssueValueMatch,true);

        boolean doReporterValueMatch = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(reporterValueOnViewJiraTicketLocator))).isDisplayed();
        Assert.assertEquals(doReporterValueMatch,true);

    }

    @Test
    public void cancelCreatingNewJiraTicketAfterFillingOutAllRequiredFieldsTest(){
        driver.findElement(By.id(createIssueButtonOnDashboardLocator)).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20).getSeconds());
        boolean createIssuePopUpIsPresent = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(createIssuePopPupLocator))).isEnabled();
        Assert.assertEquals(createIssuePopUpIsPresent, true);

        driver.findElement(By.id(projectInputLocator)).clear();
        driver.findElement(By.id(projectInputLocator)).sendKeys(projectNameValue);
        driver.findElement(By.id(projectInputLocator)).sendKeys(Keys.ENTER);
        driver.findElement(By.id(issueTypeInputLocator)).isEnabled();
        driver.findElement(By.id(issueTypeInputLocator)).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id(issueTypeInputLocator)).clear();
        driver.findElement(By.id(issueTypeInputLocator)).sendKeys(issueTypeValue);
        driver.findElement(By.id(issueTypeInputLocator)).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(summaryInputLocator))).isEnabled();
        driver.findElement(By.id(summaryInputLocator)).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id(summaryInputLocator)).sendKeys(summaryValue);
        driver.findElement(By.id(reporterInputLocator)).clear();
        driver.findElement(By.id(reporterInputLocator)).sendKeys(reporterValue);
        driver.findElement(By.cssSelector(cancelButtonLocator)).click();
        driver.switchTo().alert().accept();

        //TODO Add asserts for showing alert window
        //TODO Add asserts for NOT showinf create issue pop-up

    }

    @Test
    public void returnCreatingNewJiraTicketTest(){
        driver.findElement(By.id(createIssueButtonOnDashboardLocator)).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20).getSeconds());
        boolean createIssuePopUpIsPresent = (wait.until(ExpectedConditions.presenceOfElementLocated(By.id(createIssuePopPupLocator)))).isEnabled();
        Assert.assertEquals(createIssuePopUpIsPresent, true);

        driver.findElement(By.id(projectInputLocator)).clear();
        driver.findElement(By.id(projectInputLocator)).sendKeys(projectNameValue);
        driver.findElement(By.id(projectInputLocator)).sendKeys(Keys.ENTER);
        driver.findElement(By.id(issueTypeInputLocator)).isEnabled();
        driver.findElement(By.id(issueTypeInputLocator)).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id(issueTypeInputLocator)).clear();
        driver.findElement(By.id(issueTypeInputLocator)).sendKeys(issueTypeValue);
        driver.findElement(By.id(issueTypeInputLocator)).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(summaryInputLocator))).isEnabled();
        driver.findElement(By.id(summaryInputLocator)).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id(summaryInputLocator)).sendKeys(summaryValue);
        driver.findElement(By.id(reporterInputLocator)).clear();
        driver.findElement(By.id(reporterInputLocator)).sendKeys(reporterValue);
        driver.findElement(By.cssSelector(cancelButtonLocator)).click();
        driver.switchTo().alert().dismiss();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(createIssuePopPupLocator))).isEnabled();
        Assert.assertEquals(createIssuePopUpIsPresent, true);
        boolean doProjectNameValueMatch = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(projectInputLocator),projectNameValue));
        Assert.assertEquals(doProjectNameValueMatch,true);
        boolean doIssueTypeValueMatch = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(issueTypeInputLocator),issueTypeValue));
        Assert.assertEquals(doIssueTypeValueMatch,true);
        boolean doSummaryValueMatch = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(summaryInputLocator),summaryValue));
        Assert.assertEquals(doSummaryValueMatch,true);
        boolean doReporterValueMatch = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(reporterInputLocator),reporterValue));
        Assert.assertEquals(doReporterValueMatch,true);

    }

    @Test
    public void closeCreateTicketPopUpWithoutEnteringAnyDataTest(){
        driver.findElement(By.id(createIssueButtonOnDashboardLocator)).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20).getSeconds());
        boolean createIssuePopUpIsPresent = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(createIssuePopPupLocator))).isEnabled();
        Assert.assertEquals(createIssuePopUpIsPresent, true);

        driver.findElement(By.cssSelector(cancelButtonLocator)).click();
        boolean createIssuePopUpIsNotPresent = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(createIssuePopPupLocator)));
        Assert.assertEquals(createIssuePopUpIsNotPresent,true);

    }

    @Test
    public void successfulCreateTicketWithAllFieldsTest(){
        driver.findElement(By.id(createIssueButtonOnDashboardLocator)).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20).getSeconds());
        boolean createIssuePopUpIsPresent = (wait.until(ExpectedConditions.presenceOfElementLocated(By.id(createIssuePopPupLocator)))).isEnabled();
        Assert.assertEquals(createIssuePopUpIsPresent, true);

        driver.findElement(By.id(projectInputLocator)).clear();
        driver.findElement(By.id(projectInputLocator)).sendKeys(projectNameValue);
        driver.findElement(By.id(projectInputLocator)).sendKeys(Keys.ENTER);
        driver.findElement(By.id(issueTypeInputLocator)).isEnabled();
        driver.findElement(By.id(issueTypeInputLocator)).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id(issueTypeInputLocator)).clear();
        driver.findElement(By.id(issueTypeInputLocator)).sendKeys(issueTypeValue);
        driver.findElement(By.id(issueTypeInputLocator)).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(summaryInputLocator))).isEnabled();
        driver.findElement(By.id(summaryInputLocator)).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id(summaryInputLocator)).sendKeys(summaryValue);
        driver.findElement(By.id(reporterInputLocator)).clear();
        driver.findElement(By.id(reporterInputLocator)).sendKeys(reporterValue);
        driver.findElement(By.id(reporterInputLocator)).sendKeys(Keys.ENTER);
        //TODO Fix the test
        driver.findElement(By.id(textModeOnDescriptionLocator)).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id(descriptionInputLocator)).sendKeys(descriptionValue);
        driver.findElement(By.id(priorityInputLocator)).clear();
        driver.findElement(By.id(priorityInputLocator)).sendKeys(priorityValue);
        driver.findElement(By.id(createButtonLocator)).click();

        boolean createIssueNotificationIsPresent = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(issueCreatedNotificationLocator))).isEnabled();
        Assert.assertEquals(createIssueNotificationIsPresent, true);

        driver.findElement(By.xpath(linkToCreatedIssueLocator)).click();
        boolean viewJiraTicketPageIsPresent = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(viewIssuePageLocator))).isDisplayed();
        Assert.assertEquals(viewJiraTicketPageIsPresent,true);

        boolean doProjectValueMatch = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(projectValueOnViewTicketLocator))).isDisplayed();
        Assert.assertEquals(doProjectValueMatch,true);

        boolean doSummaryValueMatch = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(summaryValueOnViewTicketLocator))).isDisplayed();
        Assert.assertEquals(doSummaryValueMatch,true);

        boolean doTypeIssueValueMatch = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(typeIssueValueOnViewJiraTicketLocator))).isDisplayed();
        Assert.assertEquals(doTypeIssueValueMatch,true);

        boolean doReporterValueMatch = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(reporterValueOnViewJiraTicketLocator))).isDisplayed();
        Assert.assertEquals(doReporterValueMatch,true);

        boolean doPriorityValueMatch = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(priorityValueOnViewTicketPageLocator))).isDisplayed();
        Assert.assertEquals(doPriorityValueMatch,true);

        boolean doDescriptionValueMatch = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(descriptionValueOnViewTicketPageLocator))).isDisplayed();
        Assert.assertEquals(doDescriptionValueMatch,true);

    }

    @Test
    public void unsuccessfulCreateTicketTest(){
        //TODO Add test for unsuccessful flow (validation for required fields)

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
