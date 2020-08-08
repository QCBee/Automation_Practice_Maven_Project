package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CreateIssuePage {
    private WebDriver driver= null;

    public CreateIssuePage(WebDriver driver) {
        this.driver = driver;
    }

    //List of used locators
    private By createIssuePopPup = By.id("create-issue-dialog");
    private By projectInput = By.id("project-field");
    private By issueTypeInputEnabled = By.xpath("//*[@id='issuetype-field'][not(@disabled)]");
    private By issueTypeInput = By.id("issuetype-field");
    private By summaryInputEnabled = By.xpath("//*[@id='summary'][not(@disabled)]");
    private By summaryInput = By.id("summary");
    private By reporterInput = By.id("reporter-field");
    private By createButton = By.id("create-issue-submit");
    private By cancelButton = By.cssSelector("[title = 'Press undefined+` to cancel']");
    private By descriptionInput = By.id("tinymce");
    private By priorityInput = By.id("priority-single-select");
    private By textModeForDescription = By.xpath("//*[@data-mode='source']//child::a");

    public boolean isCreateIssuePopUpShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(createIssuePopPup)).isDisplayed();
    }

    public boolean isProjectInputEnabled(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(createIssuePopPup)).isDisplayed();
    }

    public void enterProject(String projectValue){
        driver.findElement(projectInput).clear();
        driver.findElement(projectInput).sendKeys(projectValue);
        driver.findElement(projectInput).sendKeys(Keys.ENTER);
    }

    public boolean isTypeIssueInputEnabled(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(issueTypeInputEnabled)).isEnabled();
    }

    public void enterTypeIssue(String typeValue){
        clickOnElementWithRetry(issueTypeInput,issueTypeInputEnabled,4,60);
        driver.findElement(issueTypeInputEnabled).clear();
        driver.findElement(issueTypeInputEnabled).sendKeys(typeValue);
        driver.findElement(issueTypeInputEnabled).sendKeys(Keys.ENTER);
    }

    public boolean isSummaryInputEnabled(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(summaryInput)).isEnabled();
    }

    public void enterSummary(String summaryValue){
        clickOnElementWithRetry(summaryInput,summaryInputEnabled,4,30);
        driver.findElement(summaryInput).sendKeys(summaryValue);
    }

    public void enterReporter(String reporterValue){
        driver.findElement(reporterInput).clear();
        driver.findElement(reporterInput).sendKeys(reporterValue);
        driver.findElement(reporterInput).sendKeys(Keys.ENTER);
    }

    public void clickCreateButton(){
        driver.findElement(createButton).click();
    }

    public void clickCancelButton(){
        driver.findElement(cancelButton).click();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    public void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }

    public boolean doProjectNameValueMatch(String projectNameValue){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(projectInput,projectNameValue));
    }

    public boolean doTypeIssueValueMatch(String typeIssueValue){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(issueTypeInputEnabled,typeIssueValue));
    }

    public boolean doSummaryValueMatch(String summaryValue){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(summaryInput,summaryValue));
    }

    public boolean doReporterValueMatch(String reporterValue){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(reporterInput,reporterValue));
    }

    public boolean isCreateIssuePopUpNotShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(createIssuePopPup));
    }

    public void selectTextModeForDescriptionInput(){
        driver.findElement(textModeForDescription).click();
    }

    public void enterDescription(String descriptionValue){
        driver.findElement(descriptionInput).sendKeys(descriptionValue);
    }

    public void enterPriorityInput(String priorityValue){
        driver.findElement(priorityInput).clear();
        driver.findElement(priorityInput).sendKeys(priorityValue);
    }

    private void clickOnElementWithRetry(By elementToBeClicked, By successCriteria, int attempts, int timeOutInSec){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
            for (int i = 0; i < attempts ; i++) {
                driver.findElement(elementToBeClicked).click();
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(successCriteria)).isDisplayed();
                    break;
                } catch (TimeoutException e){
                    wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked));
                    driver.findElement(elementToBeClicked).click();
                }
            }
    }
}
