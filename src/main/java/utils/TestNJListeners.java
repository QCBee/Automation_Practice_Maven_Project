package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class TestNJListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println();
        System.out.println("Executing Listener - onTestStart");
        System.out.println("Following test case was started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Executing Listener - onTestSuccess");
        createScreenshot();
        System.out.println("Following test case was passed: " + result.getName());
        System.out.println();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Execution Listener - onTestFailure");
        createScreenshot();
        System.out.println("Following test case was failed: " + result.getName());
        System.out.println();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Following test case was skipped: " + result.getName());
        System.out.println();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Executing Listener - onStart");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Executing Listener - onFinish");

    }

    private File captureScreenshot() {
        return ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
    }

    private void createScreenshot() {
        File screenshotsFolder = new File(System.getProperty("user.dir") + "/screenshots");

        if (!screenshotsFolder.exists()) {
            System.out.println("Creating folder for collecting screenshots...");
            screenshotsFolder.mkdir();
            System.out.println("Folder was created for screenshots");
        }

        System.out.println("Creating screenshot...");
        File screenshot = captureScreenshot();
        Path pathToScreenShot = Paths.get(screenshot.getPath());

        try {
            String screenshotName = screenshotsFolder + "/" + "Screenshot_" + java.time.LocalTime.now().toString().replace(":",".") + ".png";
            Files.copy(pathToScreenShot, Paths.get(screenshotName), StandardCopyOption.COPY_ATTRIBUTES);
            System.out.println("New screenshot for failure was added to screenshots folder");
            System.out.println("Screenshot for failed test case has following name: " + "Screenshot_" + java.time.LocalTime.now().toString().replace(":",".") + ".png");
        } catch (IOException e) {
            System.out.println("Problems with adding screenshot to folder...");
            System.out.println("Screenshot was NOT added to folder");
            e.printStackTrace();
        }
    }
}


