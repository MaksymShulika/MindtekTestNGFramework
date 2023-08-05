package assigments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.LocalDateTime;
import java.util.Date;

public class June_3_BlazedemoAssignment {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver();
    }

    @Test
    public void testBlazedemo(){
        driver.get(ConfigReader.getProperty("BlazedemoURL"));

        String fromCity = "San Diego";
        String toCity = "New York";

        WebElement departureDropdown = driver.findElement(By.xpath(ConfigReader.getProperty("DepartureCity")));
        Select departureSelect = new Select(departureDropdown);
        departureSelect.selectByValue(fromCity);

        WebElement destinationDropdown = driver.findElement(By.xpath(ConfigReader.getProperty("DestinationCity")));
        Select destinationSelect = new Select(destinationDropdown);
        destinationSelect.selectByValue(toCity);

        driver.findElement(By.xpath("//input[@type='submit'][@class='btn btn-primary']")).click();
        driver.findElement(By.xpath("(//input[@type='submit'])[1]")).click();

        WebElement name = driver.findElement(By.id("inputName"));
        WebElement address = driver.findElement(By.id("address"));
        WebElement city = driver.findElement(By.id("city"));
        WebElement state = driver.findElement(By.id("state"));
        WebElement zipCode = driver.findElement(By.id("zipCode"));
        WebElement cardType = driver.findElement(By.id("cardType"));
        WebElement creditCardNumber = driver.findElement(By.id("creditCardNumber"));
        WebElement month = driver.findElement(By.id("creditCardMonth"));
        WebElement year = driver.findElement(By.id("creditCardYear"));
        WebElement nameOnCard = driver.findElement(By.id("nameOnCard"));

        String numCard = "mycreditcardnumber";
        String last4Digits = numCard.substring(numCard.length()-4, numCard.length());

        name.sendKeys("David Clark");
        address.sendKeys("123 Washington ave");
        city.sendKeys("Austin");
        state.sendKeys("TX");
        zipCode.sendKeys("12345");
        cardType.sendKeys("American Express");
        creditCardNumber.sendKeys(numCard);
        month.clear();
        month.sendKeys("11");
        year.clear();
        year.sendKeys("2025");
        nameOnCard.sendKeys("David Clark");

        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

        String actualMessage = driver.findElement(By.xpath("//tr[4]/td[2]")).getText();
        String actual4Digits = actualMessage.substring(actualMessage.length()-4, actualMessage.length());
        String actualMessageTime = driver.findElement(By.xpath("//tr[7]/td[2]")).getText();

        Date expectedTime = new Date();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(By.xpath("//h1")).getText(),
                "Thank you for your purchase today!");
        softAssert.assertEquals(actual4Digits, last4Digits);
        softAssert.assertEquals(actualMessageTime,expectedTime.toString());
        softAssert.assertAll();
    }




    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
