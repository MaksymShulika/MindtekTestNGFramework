package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MagentoMainPage;
import pages.MagentoSignupPage;
import pages.MagentoSingInPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.Objects;
import java.util.UUID;

public class MagentoTests extends TestBase {

    // DDT - Data-Driven Testing - Testing with multiple sets of data

    @DataProvider(name = "createAccountData")// The test will rum once from every array in the DataProvider
    public static Object[][] testData(){    // multidimensional array of Object
        return new  Object[][]{
                {"Stivanth", "Kasireddy", "qQ@123QOOOQ787878uUuUu"},
//                {"Mo", "King", "qQ@123QOOOQ787878uUuUu"},
//                {"Mary", "Clark", "qQ@123QOOOQ787878uUuUu"}
        };
    }

    String uuidEmail;
    String userPassword;
    String userFName;
    String userLName;

    @Test(groups = {"regression", "smoke", "magento", "login"}, dataProvider = "createAccountData")
    public void verifyCreateAccount(String firstName, String lastName,  String userPwd) {
        driver.get(ConfigReader.getProperty("MagenroURL"));
        MagentoMainPage magentoMainPage = new MagentoMainPage();
        magentoMainPage.createAccountLink.click();

        MagentoSignupPage magentoSignupPage = new MagentoSignupPage();

        magentoSignupPage.firstNameInput.sendKeys(firstName);
        magentoSignupPage.lastNameInput.sendKeys(lastName);

        //Random randomNum = new Random();
        //int num = randomNum.nextInt(10);
        //String randEmail = "Harsh"+num+"@gmail.com";

//        UUID uuid = UUID.randomUUID();
//        uuidEmail = "user" + uuid + "@gmail.com";        this method is now in BrowserUtils class

        uuidEmail = BrowserUtils.getRandomEmail();

        this.userPassword=userPwd;
        this.userFName=firstName;
        this.userLName=lastName;

        System.out.println(uuidEmail);
        //String userPwd = "qQ@123QOOOQ787878uUuUu";
        System.out.println(userPwd);

        magentoSignupPage.emailInput.sendKeys(uuidEmail);
        magentoSignupPage.passwordInput.sendKeys(userPwd);
        magentoSignupPage.passwordConfirmInput.sendKeys(userPwd);

        magentoSignupPage.createAnAccountBtn.click();

        Assert.assertEquals(driver.getTitle(), "My Account");
    }

    @Test(groups = {"regression", "smoke", "magento", "login"}, dependsOnMethods = {"verifyCreateAccount"})
    public void verifySingIn(){
        driver.get(ConfigReader.getProperty("MagenroURL"));
        MagentoMainPage magentoMainPage = new MagentoMainPage();
        MagentoSingInPage magentoSingInPage = new MagentoSingInPage();

        magentoMainPage.singInLink.click();

        magentoSingInPage.emailInput.sendKeys(uuidEmail);
        magentoSingInPage.passInput.sendKeys(userPassword);
        magentoSingInPage.singInBtn.click();

        String expectedMessage = "Welcome, " + userFName + " " + userLName + "!";

//        WebDriverWait wait = new WebDriverWait(driver, 15);
//        wait.until(ExpectedConditions.textToBePresentInElement(magentoMainPage.welcomeMessage, expectedMessage));

        BrowserUtils.waitForTextToBePresentInElement(magentoMainPage.welcomeMessage, expectedMessage);


        Assert.assertEquals(magentoMainPage.welcomeMessage.getText(), expectedMessage);
        
    }
}
