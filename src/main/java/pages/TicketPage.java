package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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


}
