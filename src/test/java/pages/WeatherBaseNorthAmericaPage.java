package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WeatherBaseNorthAmericaPage {

    WebDriver driver;

    public WeatherBaseNorthAmericaPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'United')]")
    public WebElement USA_Btn;

}
