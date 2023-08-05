package assigments;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WeatherBaseCaliforniaPage;
import pages.WeatherBaseLocationPage;
import pages.WeatherBaseMainPage;
import pages.WeatherBaseNorthAmericaPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.Arrays;
import java.util.List;

public class WeatherbaseAssignment extends TestBase {

    //Assert.assertEquals(Double.parseDouble(actualCelsius.getText()),expectedCelsius,0.1);


    @Test(priority = 0)
    public void testSearchFunctionalityByZip(){
        driver.get(ConfigReader.getProperty("WeatherBaseURL"));
        String zipCode = "60656";

        WeatherBaseMainPage weatherBaseMainPage = new WeatherBaseMainPage();

        weatherBaseMainPage.searchBar.sendKeys(zipCode + Keys.ENTER);

        WeatherBaseLocationPage weatherBaseLocationPage = new WeatherBaseLocationPage();

        String expectedMessage = "SEARCH RESULTS: enter a city state county country or ZIP60656";

        BrowserUtils.waitForTextToBePresentInElement(weatherBaseLocationPage.messageForZip, expectedMessage);

        Assert.assertEquals(weatherBaseLocationPage.messageForZip.getText(), expectedMessage);

    }

    @Test(priority = 1)
    public void testSearchFunctionalityByInvalidZip(){
        driver.get(ConfigReader.getProperty("WeatherBaseURL"));
        String zipCode = "1234";

        WeatherBaseMainPage weatherBaseMainPage = new WeatherBaseMainPage();

        weatherBaseMainPage.searchBar.sendKeys(zipCode + Keys.ENTER);

        WeatherBaseLocationPage weatherBaseLocationPage = new WeatherBaseLocationPage();

        String expectedMessage = "We're sorry. Your search for 1234 returned no results. Please try the following:";

        BrowserUtils.waitForTextToBePresentInElement(weatherBaseLocationPage.messageForInvalidZip, expectedMessage);

        Assert.assertEquals(weatherBaseLocationPage.messageForInvalidZip.getText(), expectedMessage);

    }

    @Test(priority = 2)
    public void testSumOfStates(){
        driver.get(ConfigReader.getProperty("WeatherBaseURL"));

        WeatherBaseMainPage weatherBaseMainPage = new WeatherBaseMainPage();
        weatherBaseMainPage.northAmericaBtn.click();

        WeatherBaseNorthAmericaPage weatherBaseNorthAmericaPage = new WeatherBaseNorthAmericaPage();
        weatherBaseNorthAmericaPage.USA_Btn.click();

        List<WebElement> statesPart1 = driver.findElements(By.xpath("//li"));
        List<WebElement> statesPart2 = driver.findElements(By.xpath("//li"));

        int sumOfStates = 0;

        for (WebElement s : statesPart1){
            sumOfStates++;
        }

        for (WebElement s : statesPart2){
            sumOfStates++;
        }

        boolean isTrue = true;

        if (sumOfStates<50)isTrue=false;

        Assert.assertTrue(isTrue);
    }

    @Test(priority = 3)
    public void testTemperatureFunctionality(){
        driver.get(ConfigReader.getProperty("WeatherBaseURL"));

        WeatherBaseMainPage weatherBaseMainPage = new WeatherBaseMainPage();
        weatherBaseMainPage.northAmericaBtn.click();

        WeatherBaseNorthAmericaPage weatherBaseNorthAmericaPage = new WeatherBaseNorthAmericaPage();
        weatherBaseNorthAmericaPage.USA_Btn.click();

        //CaliforniaBtn
        driver.findElement(By.xpath("//a[contains(text(),'California')]")).click();
        //SanJoseBtn
        driver.findElement(By.xpath("//a[contains(text(),'San Jose')]")).click();

        WeatherBaseCaliforniaPage weatherBaseCaliforniaPage = new WeatherBaseCaliforniaPage();
        weatherBaseCaliforniaPage.celsiusBtn.click();

        String expectedMessage = "C";

        Assert.assertEquals(weatherBaseCaliforniaPage.celsiusMessage.getText(), expectedMessage);
    }

    @Test(priority = 4)
    public void testTemperatureConversation(){
        driver.get(ConfigReader.getProperty("WeatherBaseURL"));

        WeatherBaseMainPage weatherBaseMainPage = new WeatherBaseMainPage();
        weatherBaseMainPage.northAmericaBtn.click();

        WeatherBaseNorthAmericaPage weatherBaseNorthAmericaPage = new WeatherBaseNorthAmericaPage();
        weatherBaseNorthAmericaPage.USA_Btn.click();

        //CaliforniaBtn
        driver.findElement(By.xpath("//a[contains(text(),'California')]")).click();
        //SanJoseBtn
        driver.findElement(By.xpath("//a[contains(text(),'San Jose')]")).click();

        WeatherBaseCaliforniaPage weatherBaseCaliforniaPage = new WeatherBaseCaliforniaPage();
        double averageCelsiusExp = (Double.parseDouble(weatherBaseCaliforniaPage.AverageTemperatureAnnual.getText()) - 32) * 5 / 9;
        weatherBaseCaliforniaPage.celsiusBtn.click();

        Assert.assertEquals(Double.parseDouble(weatherBaseCaliforniaPage.AverageTemperatureAnnual.getText()), averageCelsiusExp,0.1);

    }

}
