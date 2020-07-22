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
    String commentInputLocator = "comment";
    String commentValue = "I am a test comment";
    String addCommentButtonEnabledLocator = "issue-comment-add-submit";
    String addCommentButtonDisabledLocator = "//*[@id='issue-comment-add-submit' and @disabled = 'disabled']";
    String cancelAddCommentButtonLocator = "issue-comment-add-cancel";
    String logsAreaIssueLocator = "issue_actions_container";
    String addedCommentValue = "//*[contains(text(),'I am a test comment')]";
    String textModeLocator = "//*[@data-mode='source']//child::a";
    String anyAddedCommentsAreaLocator = "//*[contains(text(),'There are no comments yet on this issue.')]";
    String deleteIconLocator = "//*[@title='Delete']";
    String deletePopUpLocator = "delete-comment-dialog";
    String deleteButtonOnPopUpLocator = "comment-delete-submit";
    String cancelButtonOnPopUpLocator = "comment-delete-cancel";
    String updateIssueNotificationLocator = "//*[contains(text(),'WEBINAR-12303 has been updated.')]";

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
    public void createCommentToTicketTest(){
        driver.get(ticketUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        boolean isCommentTabSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(commentTabLocator))).isDisplayed();
        Assert.assertEquals(isCommentTabSelected,true);
        boolean isAddCommentFooterButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentFooterButtonLocator))).isEnabled();
        Assert.assertEquals(isAddCommentFooterButtonEnabled,true);
        boolean isAnyCommentAdded = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(anyAddedCommentsAreaLocator))).isDisplayed();
        Assert.assertEquals(isAnyCommentAdded,true);

        driver.findElement(By.id(addCommentFooterButtonLocator)).click();
        boolean isAddCommentAreaShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(commentInputLocator))).isEnabled();
        Assert.assertEquals(isAddCommentAreaShown, true);
        boolean isAddCommentFooterButtonDisabled = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(addCommentFooterButtonLocator)));
        Assert.assertEquals(isAddCommentFooterButtonDisabled,true);
        boolean isAddCommentButtonDisabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addCommentButtonDisabledLocator))).isDisplayed();
        Assert.assertEquals(isAddCommentButtonDisabled,true);
        boolean isCancelButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(cancelAddCommentButtonLocator))).isDisplayed();
        Assert.assertEquals(isCancelButtonEnabled,true);

        driver.findElement(By.xpath(textModeLocator)).click();
        boolean isTextModeSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(textModeLocator))).isEnabled();
        Assert.assertTrue(isTextModeSelected);

        driver.findElement(By.id(commentInputLocator)).sendKeys(commentValue);
        boolean isAddCommentButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentButtonEnabledLocator))).isEnabled();
        Assert.assertEquals(isAddCommentButtonEnabled,true);

        driver.findElement(By.id(addCommentButtonEnabledLocator)).click();
        boolean isLogIssueAreaShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(logsAreaIssueLocator))).isDisplayed();
        Assert.assertEquals(isLogIssueAreaShown,true);
        boolean isCommentAdded = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addedCommentValue))).isDisplayed();
        Assert.assertEquals(isCommentAdded, true);

        driver.findElement(By.xpath(deleteIconLocator)).click();

        driver.findElement(By.id(deleteButtonOnPopUpLocator)).click();

        boolean isUpdateIssueNotificationShown = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(updateIssueNotificationLocator)));
        Assert.assertEquals(isUpdateIssueNotificationShown,true);
        Assert.assertEquals(isAnyCommentAdded,true);


    }

    @Test
    public void deleteCommentFromTicketTest(){
        driver.get(ticketUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        boolean isCommentTabSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(commentTabLocator))).isDisplayed();
        Assert.assertEquals(isCommentTabSelected,true);
        boolean isAddCommentFooterButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentFooterButtonLocator))).isEnabled();
        Assert.assertEquals(isAddCommentFooterButtonEnabled,true);
        boolean isAnyCommentAdded = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(anyAddedCommentsAreaLocator))).isDisplayed();
        Assert.assertEquals(isAnyCommentAdded,true);

        driver.findElement(By.id(addCommentFooterButtonLocator)).click();
        boolean isAddCommentAreaShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(commentInputLocator))).isEnabled();
        Assert.assertEquals(isAddCommentAreaShown, true);
        boolean isAddCommentFooterButtonDisabled = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(addCommentFooterButtonLocator)));
        Assert.assertEquals(isAddCommentFooterButtonDisabled,true);
        boolean isAddCommentButtonDisabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addCommentButtonDisabledLocator))).isDisplayed();
        Assert.assertEquals(isAddCommentButtonDisabled,true);
        boolean isCancelButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(cancelAddCommentButtonLocator))).isDisplayed();
        Assert.assertEquals(isCancelButtonEnabled,true);

        driver.findElement(By.xpath(textModeLocator)).click();
        boolean isTextModeSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(textModeLocator))).isEnabled();
        Assert.assertTrue(isTextModeSelected);

        driver.findElement(By.id(commentInputLocator)).sendKeys(commentValue);
        boolean isAddCommentButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentButtonEnabledLocator))).isEnabled();
        Assert.assertEquals(isAddCommentButtonEnabled,true);

        driver.findElement(By.id(addCommentButtonEnabledLocator)).click();
        boolean isLogIssueAreaShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(logsAreaIssueLocator))).isDisplayed();
        Assert.assertEquals(isLogIssueAreaShown,true);
        boolean isCommentAdded = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addedCommentValue))).isDisplayed();
        Assert.assertEquals(isCommentAdded, true);
        boolean isDeleteIconShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deleteIconLocator))).isDisplayed();
        Assert.assertEquals(isDeleteIconShown,true);

        driver.findElement(By.xpath(deleteIconLocator)).click();
        boolean isDeletePopUpShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(deletePopUpLocator))).isEnabled();
        Assert.assertEquals(isDeletePopUpShown,true);
        boolean isDeleteButtonOnPopUpShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(deleteButtonOnPopUpLocator))).isDisplayed();
        Assert.assertEquals(isDeleteButtonOnPopUpShown,true);
        boolean isCancelButtonOnPopUpShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(cancelButtonOnPopUpLocator))).isDisplayed();
        Assert.assertEquals(isCancelButtonOnPopUpShown,true);

        driver.findElement(By.id(deleteButtonOnPopUpLocator)).click();
        boolean isDeletePopUpClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(deletePopUpLocator)));
        Assert.assertEquals(isDeletePopUpClosed,true);
        boolean isUpdateIssueNotificationShown = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(updateIssueNotificationLocator)));
        Assert.assertEquals(isUpdateIssueNotificationShown,true);
        Assert.assertEquals(isAnyCommentAdded,true);

    }

    @Test
    public void cancelDeletingCommentTest(){
        driver.get(ticketUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        boolean isCommentTabSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(commentTabLocator))).isDisplayed();
        Assert.assertEquals(isCommentTabSelected,true);
        boolean isAddCommentFooterButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentFooterButtonLocator))).isEnabled();
        Assert.assertEquals(isAddCommentFooterButtonEnabled,true);
        boolean isAnyCommentAdded = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(anyAddedCommentsAreaLocator))).isDisplayed();
        Assert.assertEquals(isAnyCommentAdded,true);

        driver.findElement(By.id(addCommentFooterButtonLocator)).click();
        boolean isAddCommentAreaShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(commentInputLocator))).isEnabled();
        Assert.assertEquals(isAddCommentAreaShown, true);
        boolean isAddCommentFooterButtonDisabled = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(addCommentFooterButtonLocator)));
        Assert.assertEquals(isAddCommentFooterButtonDisabled,true);
        boolean isAddCommentButtonDisabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addCommentButtonDisabledLocator))).isDisplayed();
        Assert.assertEquals(isAddCommentButtonDisabled,true);
        boolean isCancelButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(cancelAddCommentButtonLocator))).isDisplayed();
        Assert.assertEquals(isCancelButtonEnabled,true);

        driver.findElement(By.xpath(textModeLocator)).click();
        boolean isTextModeSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(textModeLocator))).isEnabled();
        Assert.assertTrue(isTextModeSelected);

        driver.findElement(By.id(commentInputLocator)).sendKeys(commentValue);
        boolean isAddCommentButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentButtonEnabledLocator))).isEnabled();
        Assert.assertEquals(isAddCommentButtonEnabled,true);

        driver.findElement(By.id(addCommentButtonEnabledLocator)).click();
        boolean isLogIssueAreaShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(logsAreaIssueLocator))).isDisplayed();
        Assert.assertEquals(isLogIssueAreaShown,true);
        boolean isCommentAdded = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addedCommentValue))).isDisplayed();
        Assert.assertEquals(isCommentAdded, true);
        boolean isDeleteIconShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(deleteIconLocator))).isDisplayed();
        Assert.assertEquals(isDeleteIconShown,true);

        driver.findElement(By.xpath(deleteIconLocator)).click();
        boolean isDeletePopUpShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(deletePopUpLocator))).isEnabled();
        Assert.assertEquals(isDeletePopUpShown,true);
        boolean isDeleteButtonOnPopUpShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(deleteButtonOnPopUpLocator))).isDisplayed();
        Assert.assertEquals(isDeleteButtonOnPopUpShown,true);
        boolean isCancelButtonOnPopUpShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(cancelButtonOnPopUpLocator))).isDisplayed();
        Assert.assertEquals(isCancelButtonOnPopUpShown,true);

        driver.findElement(By.id(cancelButtonOnPopUpLocator)).click();
        boolean isDeletePopUpClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(deletePopUpLocator)));
        Assert.assertEquals(isDeletePopUpClosed,true);
        boolean isUpdateIssueNotificationNotShown = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(updateIssueNotificationLocator)));
        Assert.assertEquals(isUpdateIssueNotificationNotShown,true);
        Assert.assertEquals(isCommentAdded, true);

    }

    @Test
    public void cancelCreatingCommentToTicketWithConfirmationTest(){
        driver.get(ticketUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        boolean isCommentTabSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(commentTabLocator))).isDisplayed();
        Assert.assertEquals(isCommentTabSelected,true);
        boolean isAddCommentFooterButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentFooterButtonLocator))).isEnabled();
        Assert.assertEquals(isAddCommentFooterButtonEnabled,true);
        boolean isAnyCommentAdded = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(anyAddedCommentsAreaLocator))).isDisplayed();
        Assert.assertEquals(isAnyCommentAdded,true);

        driver.findElement(By.id(addCommentFooterButtonLocator)).click();
        boolean isAddCommentAreaShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(commentInputLocator))).isEnabled();
        Assert.assertEquals(isAddCommentAreaShown, true);
        boolean isAddCommentFooterButtonDisabled = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(addCommentFooterButtonLocator)));
        Assert.assertEquals(isAddCommentFooterButtonDisabled,true);
        boolean isAddCommentButtonDisabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addCommentButtonDisabledLocator))).isDisplayed();
        Assert.assertEquals(isAddCommentButtonDisabled,true);
        boolean isCancelButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(cancelAddCommentButtonLocator))).isDisplayed();
        Assert.assertEquals(isCancelButtonEnabled,true);

        driver.findElement(By.xpath(textModeLocator)).click();
        boolean isTextModeSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(textModeLocator))).isEnabled();
        Assert.assertTrue(isTextModeSelected);

        driver.findElement(By.id(commentInputLocator)).sendKeys(commentValue);
        boolean isAddCommentButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentButtonEnabledLocator))).isEnabled();
        Assert.assertEquals(isAddCommentButtonEnabled,true);

        driver.findElement(By.id(cancelAddCommentButtonLocator)).click();
        driver.switchTo().alert().accept();
        Assert.assertEquals(isCommentTabSelected,true);
        Assert.assertEquals(isAddCommentFooterButtonEnabled,true);
        Assert.assertEquals(isAnyCommentAdded,true);

        boolean isUpdateNotificationNotShown = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(updateIssueNotificationLocator)));
        Assert.assertEquals(isUpdateNotificationNotShown,true);

    }

    @Test
    public void cancelCreatingCommentToTicketWithoutConfirmationTest(){
        driver.get(ticketUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        boolean isCommentTabSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(commentTabLocator))).isDisplayed();
        Assert.assertEquals(isCommentTabSelected,true);
        boolean isAddCommentFooterButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentFooterButtonLocator))).isEnabled();
        Assert.assertEquals(isAddCommentFooterButtonEnabled,true);
        boolean isAnyCommentAdded = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(anyAddedCommentsAreaLocator))).isDisplayed();
        Assert.assertEquals(isAnyCommentAdded,true);

        driver.findElement(By.id(addCommentFooterButtonLocator)).click();
        boolean isAddCommentAreaShown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(commentInputLocator))).isEnabled();
        Assert.assertEquals(isAddCommentAreaShown, true);
        boolean isAddCommentFooterButtonDisabled = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(addCommentFooterButtonLocator)));
        Assert.assertEquals(isAddCommentFooterButtonDisabled,true);
        boolean isAddCommentButtonDisabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addCommentButtonDisabledLocator))).isDisplayed();
        Assert.assertEquals(isAddCommentButtonDisabled,true);
        boolean isCancelButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(cancelAddCommentButtonLocator))).isDisplayed();
        Assert.assertEquals(isCancelButtonEnabled,true);

        driver.findElement(By.xpath(textModeLocator)).click();
        boolean isTextModeSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(textModeLocator))).isEnabled();
        Assert.assertTrue(isTextModeSelected);

        driver.findElement(By.id(commentInputLocator)).sendKeys(commentValue);
        boolean isAddCommentButtonEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(addCommentButtonEnabledLocator))).isEnabled();
        Assert.assertEquals(isAddCommentButtonEnabled,true);

        driver.findElement(By.id(cancelAddCommentButtonLocator)).click();
        driver.switchTo().alert().dismiss();
        Assert.assertEquals(isAddCommentAreaShown, true);
        Assert.assertEquals(isTextModeSelected,true);
        Assert.assertEquals(isAddCommentButtonEnabled,true);
        Assert.assertEquals(isCancelButtonEnabled,true);
        boolean isUpdateNotificationNotShown = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(updateIssueNotificationLocator)));
        Assert.assertEquals(isUpdateNotificationNotShown,true);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
