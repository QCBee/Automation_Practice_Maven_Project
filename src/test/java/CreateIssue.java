import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.WebDriverFactory;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.assertEquals;

public class CreateIssue {

    WebDriver driver =null;

    //List of used locators
    //Locators for preconditions for login
    String loginURL = "https://jira.hillel.it/secure/Dashboard.jspa";
    String usernameLoginLocator = "login-form-username";
    String userpasswordLoginLocator = "login-form-password";
    String loginButtonLocator = "login";
    String validUsernameTestData = "webinar5";
    String validUserPasswordTestData = "webinar5";

    //Locators for create issue test
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
    String summaryValueOnViewTicketLocator = "//h1[contains(text(),'Test Summary')]";
    


    @BeforeMethod
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
        driver = WebDriverFactory.getDriver();
        driver = WebDriverFactory.getDriver();
        driver.get(loginURL);
        driver.findElement(By.id(usernameLoginLocator)).sendKeys(validUsernameTestData);
        driver.findElement(By.id(userpasswordLoginLocator)).sendKeys(validUserPasswordTestData);
        driver.findElement(By.id(loginButtonLocator)).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        boolean userProfileIconIsPresent = wait.until(presenceOfElementLocated(By.cssSelector(userProfileIconLocator))).isDisplayed();
        assertEquals(userProfileIconIsPresent, true);
    }

    @Test
    public void successfulCreateStoryWithRequiredFieldsTest(){
        driver.findElement(By.id(createIssueButtonOnDashboardLocator)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70).getSeconds());
        boolean createIssuePopUpIsPresent = wait.until(presenceOfElementLocated(By.id(createIssuePopPupLocator))).isEnabled();
        assertEquals(createIssuePopUpIsPresent,true);


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
        wait.until(presenceOfElementLocated(By.id(summaryInputLocator))).isEnabled();
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

        boolean createIssueNotificationIsPresent = wait.until(presenceOfElementLocated(By.id(issueCreatedNotificationLocator))).isEnabled();
        assertEquals(createIssueNotificationIsPresent,true);

        driver.findElement(By.xpath(linkToCreatedIssueLocator)).click();

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
