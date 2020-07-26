package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class TicketPage {
    private WebDriver driver= null;

    public TicketPage(WebDriver driver) {
        this.driver = driver;
    }

    //List of used locators
    private By projectAvatar = By.id("project-avatar");
    private By linkIssue = By.cssSelector("[href = '/browse/WEBINAR-12303']");
    private By editButtonOnCommandBar = By.id("edit-issue");
    private By commentButtonOnCommandBar = By.id("comment-issue");
    private By assignButtonOnCommandBar = By.id("assign-issue");
    private By moreDropDownButtonOnCommandBar = By.id("opsbar-operations_more");
    private By workflowButtonOnCommandBar = By.id("opsbar-transitions_more");
    private By detailsLabel = By.id("details-module_heading");
    private By typeIssueField = By.id("type-val");
    private By priorityIssueField = By.id("priority-val");
    private By statusIssueField = By.id("status-val");
    private By resolutionIssueField = By.id("resolution-val");
    private By descriptionArea = By.id("description-val");
    private By descriptionLabel = By.id("descriptionmodule_heading");

    private By viewIssuePage = By.cssSelector(".issue-navigator");
    private By projectValue = By.xpath("//*[@id='project-name-val'][@href='/browse/WEBINAR']");
    private By summaryValue = By.xpath("//h1[contains(text(),'Test Summary')]");
    private By reporterValue  = By.cssSelector("[alt = 'webinar5']");
    private By typeIssueValue = By.cssSelector("[title='Task - A task that needs to be done.']");
    private By priorityValue = By.cssSelector("[title = 'Lowest - Trivial problem with little or no impact on progress.']");
    private By descriptionValue = By.xpath("[contains(text(),'Test description')]");

    private By commentTab = By.id("comment-tabpanel");
    private By addCommentFooterButton = By.id("footer-comment-button");
    private By commentInput = By.id("comment");
    private By addCommentButtonEnabled = By.id("issue-comment-add-submit");
    private By addCommentButtonDisabled = By.xpath("//*[@id='issue-comment-add-submit' and @disabled = 'disabled']");
    private By cancelAddCommentButton = By.id("issue-comment-add-cancel");
    private By logsIssueArea = By.id("issue_actions_container");
    private By addedCommentValue = By.xpath("//*[contains(text(),'I am a test comment')]");
    private By textMode = By.xpath("//*[@data-mode='source']//child::a");
    private By anyNotAddedCommentsArea = By.xpath("//*[contains(text(),'There are no comments yet on this issue.')]");
    private By deleteIcon = By.xpath("//*[@title='Delete']");
    private By updateIssueNotification = By.xpath("//*[contains(text(),'WEBINAR-12303 has been updated.')]");


    public void navigateToTicketPage(String ticketUrl){
        driver.get(ticketUrl);
    }

    public boolean isProjectAvatarIconShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(projectAvatar)).isDisplayed();
    }

    public boolean isIssueLinkShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(linkIssue)).isDisplayed();
    }

    public boolean isEditButtonOnCommandBarShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(editButtonOnCommandBar)).isDisplayed();
    }

    public boolean isCommentButtonOnCommandBarShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(commentButtonOnCommandBar)).isDisplayed();
    }

    public boolean isAssignButtonOnCommandBarShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(assignButtonOnCommandBar)).isDisplayed();
    }

    public boolean isMoreDropDownButtonOnCommandBarShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(moreDropDownButtonOnCommandBar)).isDisplayed();
    }

    public boolean isWorkflowButtonOnCommandBarShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(workflowButtonOnCommandBar)).isDisplayed();
    }

    public boolean isDetailsLabelShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(detailsLabel)).isDisplayed();
    }

    public boolean isTypeIssueFieldShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(typeIssueField)).isDisplayed();
    }

    public boolean isPriorityIssueFieldShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(priorityIssueField)).isDisplayed();
    }

    public boolean isStatusIssueFieldShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(statusIssueField)).isDisplayed();
    }

    public boolean isResolutionIssueFieldShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(resolutionIssueField)).isDisplayed();
    }

    public boolean isDescriptionAreaShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(descriptionArea)).isDisplayed();
    }

    public boolean isDescriptionLabelShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(descriptionLabel)).isDisplayed();
    }

    public boolean isViewIssuePageShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(viewIssuePage)).isDisplayed();
    }

    public boolean doProjectValueMatch(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(projectValue)).isDisplayed();
    }

    public boolean doSummaryValueMatch(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(summaryValue)).isDisplayed();
    }

    public boolean doTypeIssueValueMatch(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(typeIssueValue)).isDisplayed();
    }

    public boolean doReporterValueMatch(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(reporterValue)).isDisplayed();
    }

    public boolean doPriorityValueMatch(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(priorityValue)).isDisplayed();
    }

    public boolean doDescriptionValueMatch(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(descriptionValue)).isDisplayed();
    }

    public boolean isCommentTabSelected(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(commentTab)).isDisplayed();
    }

    public boolean isAddCommentFooterButtonEnabled(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(addCommentFooterButton)).isEnabled();
    }

    public boolean isAnyCommentNotAdded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(anyNotAddedCommentsArea)).isDisplayed();
    }

    public void clickAddCommentFooterButton(){
        driver.findElement(addCommentFooterButton).click();
    }

    public boolean isAddCommentAreaShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(commentInput)).isEnabled();
    }

    public boolean isAddCommentFooterButtonDisabled(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(addCommentFooterButton));
    }

    public boolean isAddCommentButtonDisabled(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(addCommentButtonDisabled)).isDisplayed();
    }

    public boolean isCancelButtonEnabled(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(cancelAddCommentButton)).isDisplayed();
    }

    public void selectTextModeForComment(){
        driver.findElement(textMode).click();
    }

    public boolean isTextModeForCommentSelected(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(textMode)).isEnabled();
    }

    public void enterComment(String commentValue){
        driver.findElement(commentInput).sendKeys(commentValue);
    }

    public boolean isAddCommentButtonEnabled(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(addCommentButtonEnabled)).isEnabled();
    }

    public void clickAddCommentButton(){
        driver.findElement(addCommentButtonEnabled).click();
    }

    public boolean isLogIssueAreaShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(logsIssueArea)).isDisplayed();
    }

    public boolean isAddedCommentShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(addedCommentValue)).isDisplayed();
    }

    public void clickDeleteCommentIcon(){
        driver.findElement(deleteIcon).click();
    }

    public boolean isUpdateIssueNotificationShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(updateIssueNotification)).isDisplayed();
    }

    public boolean isDeleteIconShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(deleteIcon)).isDisplayed();
    }

    public boolean isUpdateIssueNotificationNotShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(updateIssueNotification));
    }

    public void clickCancelButton(){
        driver.findElement(cancelAddCommentButton).click();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    public void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }


}
