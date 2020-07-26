package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteCommentPage {
    private WebDriver driver= null;

    public DeleteCommentPage(WebDriver driver) {
        this.driver = driver;
    }

    private By deletePopUp = By.id("delete-comment-dialog");
    private By deleteButton = By.id("comment-delete-submit");
    private By cancelButton = By.id("comment-delete-cancel");

    public void clickDeleteButton(){
        driver.findElement(deleteButton).click();
    }

    public boolean isDeletePopUpShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(deletePopUp)).isEnabled();
    }

    public boolean isDeleteButtonShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(deleteButton)).isDisplayed();
    }

    public boolean isCancelButtonShown(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.presenceOfElementLocated(cancelButton)).isDisplayed();
    }

    public boolean isDeletePopUpClosed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40).getSeconds());
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(deletePopUp));
    }

    public void clickCancelButton(){
        driver.findElement(cancelButton).click();
    }
}
