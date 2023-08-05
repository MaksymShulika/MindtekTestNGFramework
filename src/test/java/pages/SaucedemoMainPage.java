package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class SaucedemoMainPage {

    WebDriver driver;

    public SaucedemoMainPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product_sort_container")
    public WebElement sortDropdown;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> itemPrices;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement sauceLabsBackpackAddToCart;

    @FindBy(id = "shopping_cart_container")
    public WebElement cartBtn;
}
