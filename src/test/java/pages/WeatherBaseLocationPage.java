package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WeatherBaseLocationPage {

    WebDriver driver;

    public WeatherBaseLocationPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//p)[1]")
    public WebElement messageForInvalidZip;

    @FindBy(xpath = "//h1")
    public WebElement messageForZip;


}
