package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LogisticsMainPage {

    WebDriver driver;

    public LogisticsMainPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "side-link-company")
    public WebElement companiesBtn;

    @FindBy(className = "link-btm-menu")
    public WebElement addCompanyBtn;
}
