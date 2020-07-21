import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.WebDriverFactory;

import java.time.Duration;

public class CommentForTicket {
    WebDriver driver = null;
    String loginURL = "https://jira.hillel.it/secure/Dashboard.jspa";
    String usernameLoginLocator = "login-form-username";
    String userpasswordLoginLocator = "login-form-password";
    String loginButtonLocator = "login";
    String validUsernameTestData = "webinar5";
    String validUserPasswordTestData = "webinar5";
    String userProfileIconLocator = "[alt = 'User profile for webinar5']";
    String ticketUrl = "https://jira.hillel.it/browse/WEBINAR-12303";
    String commentTabLocator = "comment-tabpanel";
    String addCommentFooterButtonLocator = "footer-comment-button";
    String addCommentAreaLocator = "comment";
    String commentInputLocator = "tinymce";
    String commentValue = "I am a test comment";
    String addCommentButtonLocator = "issue-comment-add-submit";
    String cancelAddCommentButtonLocator = "issue-comment-add-cancel";
    String logsAreaIssueLocator = "issue_actions_container";
    String addedCommentValue = "//*[contains(text(),'I am a test comment')]";
    String textModeLocator = "[data-mode='source']";

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
    public void addCommentToTicketTest(){
        driver.get(ticketUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds());
        boolean isCommentTabSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(commentTabLocator))).isDisplayed();
        Assert.assertEquals(isCommentTabSelected,true);

        boolean isAddCommentFooterButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentFooterButtonLocator))).isEnabled();
        Assert.assertEquals(isAddCommentFooterButtonEnabled,true);

        driver.findElement(By.id(addCommentFooterButtonLocator)).click();
        boolean isAddCommentAreaShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentAreaLocator))).isDisplayed();
        Assert.assertEquals(isAddCommentAreaShown, true);
        boolean isAddCommentFooterButtonDisabled = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(addCommentFooterButtonLocator)));
        Assert.assertEquals(isAddCommentFooterButtonDisabled,true);
        boolean isAddCommentButtonDisabled = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(addCommentButtonLocator)));
        Assert.assertEquals(isAddCommentButtonDisabled,true);
        boolean isCancelButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(cancelAddCommentButtonLocator))).isDisplayed();
        Assert.assertEquals(isCancelButtonEnabled,true);


        driver.findElement(By.id(commentInputLocator)).click();
        driver.findElement(By.cssSelector(textModeLocator)).click();
        boolean isTextModeSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(textModeLocator))).isSelected();
        Assert.assertEquals(isTextModeSelected,true);
        driver.findElement(By.id(commentInputLocator)).sendKeys(commentValue);
        boolean isAddCommentButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentButtonLocator))).isEnabled();
        Assert.assertEquals(isAddCommentButtonEnabled,true);

        driver.findElement(By.id(addCommentButtonLocator)).click();
        boolean isLogIssueAreaShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(logsAreaIssueLocator))).isDisplayed();
        Assert.assertEquals(isLogIssueAreaShown,true);
        boolean isCommentAdded = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addedCommentValue))).isDisplayed();
        Assert.assertEquals(isCommentAdded, true);


    }

//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }
}
