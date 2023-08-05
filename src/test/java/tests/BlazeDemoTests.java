package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BlazedemoFlightPage;
import pages.BlazedemoMainPage;
import pages.BlazedemoReservePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BlazeDemoTests extends TestBase {

 //   WebDriver driver;

 //   @BeforeMethod
  //  public void setUp(){
   //     WebDriverManager.chromedriver().setup();
   //     driver =new ChromeDriver();
   //     driver.manage().window().maximize();
   //     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    //}

    @Test(groups = {"regression", "blazedemo"})
    public void verifyFIndFlights(){
//        driver.get("https://blazedemo.com/");
        driver.get(ConfigReader.getProperty("BlazedemoURL"));

        String fromCity = "San Diego";
        String toCity = "Cairo";

        WebElement departureDropdown = driver.findElement(By.xpath("//select[@name='fromPort']"));
        BrowserUtils.selectDropdownByValue(departureDropdown, fromCity);

        WebElement destinationDropdown = driver.findElement(By.xpath("//select[@name='toPort']"));
        BrowserUtils.selectDropdownByValue(destinationDropdown, toCity);

        driver.findElement(By.xpath("//input[@type='submit'][@class='btn btn-primary']")).click();

        WebElement actualFlightsText = driver.findElement(By.xpath("//h3[contains(text(), 'Flights from')]"));
        String expectedFlightsText = "Flights from "+ fromCity +" to "+toCity+":";

        Assert.assertEquals(actualFlightsText.getText(), expectedFlightsText);

    }

    @Test(groups = {"regression", "smoke", "blazedemo"})
    public void verifyDestinationOfTheWeek(){
        driver.get(ConfigReader.getProperty("BlazedemoURL"));
        driver.findElement(By.xpath("//a[@href='vacation.html']")).click();
        String actualDestinationText = driver.findElement(By.xpath("//div[contains(text(),'Destination')]")).getText();
        Assert.assertEquals(actualDestinationText, "Destination of the week: Hawaii !");
    }

    @Test(groups = {"regression", "blazedemo"})
    public void verifyFlightInfo(){
        driver.get(ConfigReader.getProperty("BlazedemoURL"));
        BlazedemoMainPage bMP = new BlazedemoMainPage();
        BlazedemoFlightPage bFP = new BlazedemoFlightPage();
        BlazedemoReservePage bRP = new BlazedemoReservePage();

        String fromCity = "San Diego";
        String toCity = "New York";


//        Select departureSelect = new Select(bMP.departureDropdown);
//        departureSelect.selectByValue(fromCity);

        BrowserUtils.selectDropdownByValue(bMP.departureDropdown, fromCity);

//        Select destinationSelect = new Select(bMP.destinationDropdown);
//        destinationSelect.selectByValue(toCity);

        BrowserUtils.selectDropdownByValue(bMP.destinationDropdown, toCity);

        bMP.findFlightsBtn.click();

        String flightNumberStr = "Flight Number: " + bFP.flightNumber.getText();
        String flightAirline = "Airline: " + bFP.flightAirline.getText();
        String flightSum = "Price: " + bFP.flightSum.getText();



        bFP.chooseFlightBtn.click();

//        SoftAssert softAssert = new SoftAssert();
//
//        softAssert.assertEquals(bRP.expectedFlight.getText(), flightNumberStr);
//        softAssert.assertEquals(bRP.expectedAirline.getText(), flightAirline);
//        softAssert.assertEquals(bRP.expectedSum.getText(), flightSum);
//
//        softAssert.assertAll();
    }

    //@AfterMethod
   // public void tearDown() throws InterruptedException {
   //     Thread.sleep(1000);
   //     driver.quit();
   // }
}
