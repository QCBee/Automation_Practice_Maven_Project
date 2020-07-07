import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.WebDriverFactory;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

public class ViewJiraTicket {
    WebDriver driver =null;

    //List of used locators
    //Locators for preconditions for login
    String loginURL = "https://jira.hillel.it/secure/Dashboard.jspa";
    String usernameLoginLocator = "login-form-username";
    String userpasswordLoginLocator = "login-form-password";
    String loginButtonLocator = "login";
    String validUsernameTestData = "webinar5";
    String validUserPasswordTestData = "webinar5";

    //Locators for view Jira Ticket
    String userProfileIconLocator = "[alt = 'User profile for webinar5']";
    String issueLinkLocator = "[data-issue-key = 'WEBINAR-11541']";
    String projectAvatarLocator = "project-avatar";
    String linkIssueLocator = "[href = '/browse/WEBINAR-11541']";
    String editIssueButtonLocator = "edit-issue";
    String commentIssueButtonLocator = "comment-issue";
    String assignIssueButtonLocator = "assign-issue";
    String moreDropDownButtonLocator = "opsbar-operations_more";
    String workflowButtonLocator = "opsbar-transitions_more";
    String detailsLabelLocator = "details-module_heading";
    String typeIssueLocator = "type-val";
    String priorityIssueLocator = "priority-val";
    String statusIssueLocator = "status-val";
    String resolutionLocator = "resolution-val";
    String descriptionAreaLocator = "description-val";
    String descriptionLabelLocator = "descriptionmodule_heading";

    //Locators for expand/collapse tests
//    String detailsTouchAreaLocator = "//*[contains(text(),'Details')]";
//    String descriptionTouchAreaLocator = "//*[contains(text(),'Description')]";
//    String detailsExpandedAreaLocator = "issuedetails";
//    String attachmentTouchAreaLocator = "//*[contains(text(),'Attachments')]";

    @BeforeTest
    public void setUp() {
        WebDriverFactory.createInstance("Chrome");
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
    public void viewJiraTicketTest(){
        driver.findElement(By.cssSelector(issueLinkLocator)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        boolean projectAvatarIconIsPresent = wait.until(presenceOfElementLocated(By.id(projectAvatarLocator))).isDisplayed();
        boolean linkIssueIsPresent = wait.until(presenceOfElementLocated(By.cssSelector(linkIssueLocator))).isDisplayed();
        boolean editIssueButtonIsPresent = wait.until(presenceOfElementLocated(By.id(editIssueButtonLocator))).isDisplayed();
        boolean commentIssueButtonIsPresent = wait.until(presenceOfElementLocated(By.id(commentIssueButtonLocator))).isDisplayed();
        boolean assignIssueButtonIsPresent = wait.until(presenceOfElementLocated(By.id(assignIssueButtonLocator))).isDisplayed();
        boolean moreDropDownButtonIsPresent = wait.until(presenceOfElementLocated(By.id(moreDropDownButtonLocator))).isDisplayed();
        boolean workflowButtonIsPresent = wait.until(presenceOfElementLocated(By.id(workflowButtonLocator))).isDisplayed();
        boolean detailsLabelIsPresent = wait.until(presenceOfElementLocated(By.id(detailsLabelLocator))).isDisplayed();
        boolean typeIssueIsPresent = wait.until(presenceOfElementLocated(By.id(typeIssueLocator))).isDisplayed();
        boolean priorityIssueIsPresent = wait.until(presenceOfElementLocated(By.id(priorityIssueLocator))).isDisplayed();
        boolean statusIssueIsPresent = wait.until(presenceOfElementLocated(By.id(statusIssueLocator))).isDisplayed();
        boolean resolutionIsPresent = wait.until(presenceOfElementLocated(By.id(resolutionLocator))).isDisplayed();
        boolean descriptionLabelIsPresent = wait.until(presenceOfElementLocated(By.id(descriptionLabelLocator))).isDisplayed();
        boolean descriptionAreaIsPresent = wait.until(presenceOfElementLocated(By.id(descriptionAreaLocator))).isDisplayed();


        assertEquals(projectAvatarIconIsPresent,true);
        assertEquals(linkIssueIsPresent,true);
        assertEquals(editIssueButtonIsPresent,true);
        assertEquals(commentIssueButtonIsPresent,true);
        assertEquals(assignIssueButtonIsPresent,true);
        assertEquals(moreDropDownButtonIsPresent,true);
        assertEquals(workflowButtonIsPresent,true);
        assertEquals(detailsLabelIsPresent,true);
        assertEquals(typeIssueIsPresent,true);
        assertEquals(priorityIssueIsPresent,true);
        assertEquals(statusIssueIsPresent,true);
        assertEquals(resolutionIsPresent,true);
        assertEquals(descriptionLabelIsPresent,true);
        assertEquals(descriptionAreaIsPresent,true);

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
