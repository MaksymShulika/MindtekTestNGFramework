package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LogisticsAddCompPage {

    WebDriver driver;

    public LogisticsAddCompPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "#id_company_name")
    public WebElement nameField;

    @FindBy(id = "id_company_type")
    public WebElement companyTypeDropDown;

    @FindBy(id = "id_status")
    public WebElement statusTypeDropDown;

}

