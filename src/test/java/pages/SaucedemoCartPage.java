package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SaucedemoCartPage {

    WebDriver driver = Driver.getDriver();

    public SaucedemoCartPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "remove-sauce-labs-backpack")
    public WebElement removeBtnBackpack;

    @FindBy(xpath = "//div[contains(text(),'Sauce Labs Backpack')]")
    public WebElement backpackInCartMessage;

    @FindBy(className = "shopping_cart_link")
    public WebElement itemInCart;
}
