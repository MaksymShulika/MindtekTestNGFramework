package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;


public class SaucedemoLoginPage {

    WebDriver driver;


    public SaucedemoLoginPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    public WebElement userNameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(xpath = "//h3")
    public WebElement errorMessage;

    public void login(){
        driver.get(ConfigReader.getProperty("SaucedemoURL"));
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys(ConfigReader.getProperty("SaucedameUserNamePositive"));
        driver.findElement(By.id("password")).sendKeys(ConfigReader.getProperty("SaucedamePassword") + Keys.ENTER);
    }

}
