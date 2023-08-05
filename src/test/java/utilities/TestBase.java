package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LogisticsLoginPage;
import pages.LogisticsMainPage;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;

    @BeforeMethod(groups = {"regression", "smoke"})
    public void setUp(){
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod(groups = {"regression", "smoke"})
    public void tearDown(){
        driver.quit();
    }

}
