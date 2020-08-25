package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    private static final ThreadLocal <WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public ThreadLocal<WebDriver> getWebDriver() {
        return webDriver;
    }

    public WebDriverFactory() {
    }

    public static void createInstance(String browserName) {
        WebDriver driver;
        driver = null;

        if (browserName.toLowerCase().contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.toLowerCase().contains("internet")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else if (browserName.toLowerCase().contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.toLowerCase().contains("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            System.out.println("chrome");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
        webDriver.set(driver);

    }

    public static void closeBrowser(){
        getDriver().quit();
    }
}
