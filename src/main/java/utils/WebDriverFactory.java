package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    private static WebDriver webDriver;

    public static WebDriver getDriver() {
        return webDriver;
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver = driver;
    }

    public static void closeDriver() {
        webDriver.quit();
    }

    public static void createInstance(String browserName) {

        DesiredCapabilities capability = null;
        WebDriver driver = null;

        if (browserName.toLowerCase().contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.toLowerCase().contains("internet")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else if (browserName.toLowerCase().contains("chrome")) {
//      WebDriverManager.chromedriver().version("78.0.3904.70").setup();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.toLowerCase().contains("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        // Implicit Wait. Will wait constant amount of time for every element.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;

         //Simulate slow network speed - network throttle

//        Map<String, String> map = new HashMap<>();
//        map.put("offline", "false");
//        map.put("latency", "10");
//        map.put("download_throughput", "1024");
//        map.put("upload_throughput", "1024");

//        CommandExecutor executor = (() driver).getCommandExecutor();
//        try {
//            Response response = executor.execute(
//                    new Command(((ChromeDriver) driver).getSessionId(), "setNetworkConditions", ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map)))
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        webDriver = driver;

    }

    public static class ScreenShotListeners {
    }
}
