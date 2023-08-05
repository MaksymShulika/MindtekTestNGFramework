package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MagentoSignupPage {

    WebDriver driver;

    public MagentoSignupPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstname")
    public WebElement firstNameInput;

    @FindBy(id = "lastname")
    public WebElement lastNameInput;

    @FindBy(id = "email_address")
    public WebElement emailInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "password-confirmation")
    public WebElement passwordConfirmInput;

    @FindBy(xpath = "//button[@title='Create an Account']")
    public WebElement createAnAccountBtn;
}
