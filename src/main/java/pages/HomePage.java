package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class HomePage {
    WebDriver driver = null;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //List of used locators
    private By userProfileIcon = By.cssSelector(".aui-avatar-inner");
    private By createIssueButton = By.id("create_link");
    private By issueCreatedNotification = By.id("aui-flag-container");
    private By linkToCreatedIssue = By.xpath("//*[contains(text(),' - Test Summary')]");


    public boolean isUserProfileIconShown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).getSeconds());
        return wait.until(presenceOfElementLocated(userProfileIcon)).isDisplayed();
    }

    public void clickCreateIssueButton(){
        driver.findElement(createIssueButton).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isIssueCreateNotificationShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(issueCreatedNotification)).isEnabled();
    }

    public void clickIssueLinkOnNotification(){
        driver.findElement(linkToCreatedIssue).click();
    }


}
