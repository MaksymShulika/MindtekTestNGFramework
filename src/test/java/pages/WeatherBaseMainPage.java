package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import utilities.Driver;

public class WeatherBaseMainPage {

    WebDriver driver;

    public WeatherBaseMainPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='query']")
    public WebElement searchBar;

    @FindBy(xpath = "//a[contains(text(),'North America')]")
    public WebElement northAmericaBtn;



}
