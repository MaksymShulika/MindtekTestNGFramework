package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LogisticsLoginPage;
import pages.LogisticsMainPage;

public class TestBasePrjk____________________ {

    protected WebDriver driver;

    @BeforeMethod(groups = {"regression", "smoke"})
    public void setUp(){
        driver =Driver.getDriver();
        driver.get(ConfigReader.getProperty("Work1Url"));
        LogisticsLoginPage logisticsLoginPage = new LogisticsLoginPage();
        logisticsLoginPage.loginInput.sendKeys(ConfigReader.getProperty("LogisticsLogin"));
        logisticsLoginPage.passwordInput.sendKeys(ConfigReader.getProperty("LogisticsPassword"));
        logisticsLoginPage.loginBtn.click();
        LogisticsMainPage logisticsMainPage = new LogisticsMainPage();
        logisticsMainPage.companiesBtn.click();
        logisticsMainPage.addCompanyBtn.click();
    }

    @AfterMethod(groups = {"regression", "smoke"})
    public void tearDown(){
        driver.quit();
    }
}
