package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MagentoSingInPage {

    WebDriver driver;

    public MagentoSingInPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input [@name='login[username]']")
    public WebElement emailInput;

    @FindBy(id = "pass")
    public WebElement passInput;

    @FindBy(id = "send2")
    public WebElement singInBtn;
}
