package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.SaucedemoLoginPage;
import pages.SaucedemoMainPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;

import java.lang.module.Configuration;
import java.util.concurrent.TimeUnit;

public class SoucedemoTests extends TestBase {

//    WebDriver driver;

//    @BeforeMethod
//    public void setUp(){
//        driver = Driver.getDriver();
//      Everything below this line is already implemented in the Driver class.

//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//    }

    //@Ignore - ignores the @Test method below
    @Parameters({"username", "password"})
    @Test (priority = 1, groups = {"regression", "smoke", "saucedemo", "login"}, retryAnalyzer = Readable.class)
    public void testStandardUser(String username, String password){

        System.out.println(username);
        System.out.println(password);

 //       SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();
  //      saucedemoLoginPage.login();

  //      WebElement shopCartBtn = driver.findElement(By.id("shopping_cart_container"));

  //      Assert.assertTrue(shopCartBtn.isDisplayed());
    }

    @Test (priority = 2, groups = {"regression", "saucedemo", "login"})
    public void testLockedUser(){
        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();
        saucedemoLoginPage.login();



        // debugging - finding and fixing bugs or failures.
        // good practice to add messages to assertion lives, especially
        //Assert.assertEquals(driver.findElement(By.xpath("//h3")).getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test (priority = 3, groups = {"regression", "saucedemo", "login"})
    public void verifyPriceLowHigh(){
        //WebElement userNameInput = driver.findElement(By.id("user-name"));
        SaucedemoMainPage sMP = new SaucedemoMainPage();
        SaucedemoLoginPage saucedemoLoginPage = new SaucedemoLoginPage();
        saucedemoLoginPage.login();


        WebElement shopCartBtn = driver.findElement(By.id("shopping_cart_container"));

        Assert.assertTrue(shopCartBtn.isDisplayed());


        Select select = new Select(sMP.sortDropdown);

        select.selectByValue("lohi");



        boolean isTrue = true;
        double prices = 0;
        for (WebElement item : sMP.itemPrices){
            if (prices < Double.parseDouble(item.getText().substring(1)) || prices == Double.parseDouble(item.getText().substring(1))){
                prices = Double.parseDouble(item.getText().substring(1));
            }else isTrue = false;
        }
        //Assert.assertEquals(prices, 49.99);
        Assert.assertTrue(isTrue);
    }


//    @AfterMethod
//    public void tearDown() throws InterruptedException {
//        Thread.sleep(3000);
//        driver.quit();
//    }
}
