package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.LogisticsLoginPage;
import pages.LogisticsMainPage;

import java.util.concurrent.TimeUnit;

public class Driver {

    /**
     * This method getDriver() sets up Webdriver type
     * based on the provided browser type in the
     * Configuration.properties file.
     */

    static WebDriver driver;

    public static WebDriver getDriver() {
        String browser = ConfigReader.getProperty("browser");

        //inner brasses  (())  outer brasses

        //* IF (we have driver set up |or| open session id(unique session ID))
        if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    options.addArguments(
                            //"--headless",
                            "--incognito",
                            "--disable-popup-blocking");
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    driver = new OperaDriver();
                    break;
            }
        } else {
            return driver;
        }
        return driver;
    }

}
