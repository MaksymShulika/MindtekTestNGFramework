package assigments;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;

public class AlertAssignment {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver();
    }

    @Test
    public void JavaScriptConfirmBox(){
        driver.get(ConfigReader.getProperty("SeleniumEasyURL"));

        WebElement bntClickME = driver.findElement(By.xpath(ConfigReader.getProperty("JavaScriptConfirmBox")));

        bntClickME.click();

        Alert alert = driver.switchTo().alert();

        alert.accept();

        Assert.assertEquals(driver.findElement(By.id("confirm-demo")).getText(), "You pressed OK!");

        bntClickME.click();

        alert.dismiss();

        Assert.assertEquals(driver.findElement(By.id("confirm-demo")).getText(), "You pressed Cancel!");


    }

    @Test
    public void JavaScriptAlertBox(){
        driver.get(ConfigReader.getProperty("SeleniumEasyURL"));

        driver.findElement(By.xpath(ConfigReader.getProperty("JavaScriptAlertBox"))).click();

        Alert alert = driver.switchTo().alert();

        alert.accept();

        Assert.assertEquals(driver.findElement(By.id("prompt-demo")).getText(), "You have entered 'Enter name' !");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
