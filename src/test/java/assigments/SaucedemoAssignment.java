package assigments;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SaucedemoCartPage;
import pages.SaucedemoLoginPage;
import pages.SaucedemoMainPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.security.Key;

public class SaucedemoAssignment extends TestBase {


    @DataProvider(name = "loginData")
    public static Object[][] testDataLogin(){
        return new Object[][]{
                {"standard_user", "secret_sauce"},
        };
    }


    @Test(priority = 0, groups = {"regression", "smoke", "magento", "login"})
    public void loginFunctionalityWithInvalidPassword(){
        driver.get(ConfigReader.getProperty("SaucedemoURL"));
        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();

        saucedemoLoginPage.userNameInput.sendKeys("standard_user");
        saucedemoLoginPage.passwordInput.sendKeys("secret_sauce_invalid_pass"
        + Keys.ENTER);

        String expectedMessage = "Epic sadface: Username and password do not match any user in this service";

        Assert.assertEquals(saucedemoLoginPage.errorMessage.getText(), expectedMessage);

    }

    @Test(priority = 1, groups = {"regression", "smoke", "magento", "login"})
    public void  addToCartFunctionality(){
        driver.get(ConfigReader.getProperty("SaucedemoURL"));
        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();

        saucedemoLoginPage.login();

        SaucedemoMainPage saucedemoMainPage = new SaucedemoMainPage();

        saucedemoMainPage.sauceLabsBackpackAddToCart.click();
        saucedemoMainPage.cartBtn.click();

        String expectedMessage = "Sauce Labs Backpack";

        SaucedemoCartPage saucedemoCartPage = new SaucedemoCartPage();

        Assert.assertEquals(saucedemoCartPage.backpackInCartMessage.getText(), expectedMessage);
    }

    @Test(priority = 2, groups = {"regression", "smoke", "magento", "login"})
    public void removeButtonOnCart(){

        driver.get(ConfigReader.getProperty("SaucedemoURL"));
        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();

        saucedemoLoginPage.login();

        SaucedemoMainPage saucedemoMainPage = new SaucedemoMainPage();

        saucedemoMainPage.sauceLabsBackpackAddToCart.click();
        saucedemoMainPage.cartBtn.click();

        String expectedMessage = "Sauce Labs Backpack";

        SaucedemoCartPage saucedemoCartPage = new SaucedemoCartPage();

        saucedemoCartPage.removeBtnBackpack.click();


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(saucedemoCartPage.itemInCart.getText(), "");
        softAssert.assertAll();

    }

}
