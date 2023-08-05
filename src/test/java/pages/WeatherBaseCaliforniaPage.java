package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WeatherBaseCaliforniaPage {

    WebDriver driver;

    public WeatherBaseCaliforniaPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//a[@class='grayglow'])[10]")
    public WebElement celsiusBtn;

    @FindBy(xpath = "(//td[@class='dataunit'])[1]")
    public WebElement celsiusMessage;

    @FindBy(xpath = "(//td[@class='data'])[1]")
    public WebElement AverageTemperatureAnnual;

}
