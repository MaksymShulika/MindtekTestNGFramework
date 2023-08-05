package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LogisticsLoginPage {

    WebDriver driver;

    public LogisticsLoginPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@name='login']")
    public WebElement loginInput;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@class='btn-login']")
    public WebElement loginBtn;

}
