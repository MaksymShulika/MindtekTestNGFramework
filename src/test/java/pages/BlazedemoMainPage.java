package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BlazedemoMainPage {

    WebDriver driver;

    public BlazedemoMainPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@name='fromPort']")
    public WebElement departureDropdown;

    @FindBy(xpath = "//select[@name='toPort']")
    public WebElement destinationDropdown;

    @FindBy(xpath = "//input[@type='submit'][@class='btn btn-primary']")
    public WebElement findFlightsBtn;
}
