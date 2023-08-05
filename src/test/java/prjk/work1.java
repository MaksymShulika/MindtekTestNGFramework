package prjk;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LogisticsAddCompPage;
import utilities.TestBasePrjk____________________;

public class work1 extends TestBasePrjk____________________ {


    @Test(groups = {"regression","smoke"})
    public void logisticsAddCompFieldName50Chars(){
        LogisticsAddCompPage logisticsAddCompPage = new LogisticsAddCompPage();
        String name50Chars = "NorthCarolinaSouthCarolinaCaliforniaIllinoisNewYor";
        Assert.assertTrue(name50Chars.length()==50);
        logisticsAddCompPage.nameField.sendKeys(name50Chars);
        String actualName = logisticsAddCompPage.nameField.getAttribute("value");

        Assert.assertEquals(actualName,name50Chars);
    }

    @Test(groups = {"regression","smoke","logistics"})
    public void logisticsAddCompFieldName51Chars(){
        LogisticsAddCompPage logisticsAddCompPage = new LogisticsAddCompPage();
        String name51Chars = "NorthCarolinaSouthCarolinaCaliforniaIllinoisNewYork";
        Assert.assertTrue(name51Chars.length()==51);
        logisticsAddCompPage.nameField.sendKeys(name51Chars);
        String actualName = logisticsAddCompPage.nameField.getAttribute("value");

        Assert.assertTrue(actualName.length()==50);
    }

    @Test(groups = {"regression","smoke","logistics"})
    public void logisticsAddCompFieldNameEmpty(){

        LogisticsAddCompPage logisticsAddCompPage = new LogisticsAddCompPage();
        String name1Chars = "N";
        logisticsAddCompPage.nameField.sendKeys(name1Chars);
        logisticsAddCompPage.nameField.sendKeys(Keys.BACK_SPACE);
        String actualRes = driver.findElement(By.className("input-errors")).getText();
        String expectedMessage = "This field is required.";

        Assert.assertEquals(actualRes,expectedMessage);

    }

    @Test(groups = {"regression","smoke","logistics"})
    public void logisticsAddCompFieldNameWithNumber() {

        LogisticsAddCompPage logisticsAddCompPage = new LogisticsAddCompPage();
        String nameWithNumb = "Mindtek123";
        logisticsAddCompPage.nameField.sendKeys(nameWithNumb);
        String expectedRes = "Mindtek123";

        Assert.assertEquals(logisticsAddCompPage.nameField.getAttribute("value"),expectedRes);
    }

    @Test(groups = {"regression","smoke","logistics"})
    public void logisticsAddCompFieldName$() {

        LogisticsAddCompPage logisticsAddCompPage = new LogisticsAddCompPage();
        String nameWithInvalidSymb = "Mindtek$";
        logisticsAddCompPage.nameField.sendKeys(nameWithInvalidSymb);
        String actualRes = driver.findElement(By.className("input-errors")).getText();
        String expectedMessage = "Invalid input";

        Assert.assertEquals(actualRes,expectedMessage);
    }

    @Test(groups = {"regression","smoke","logistics"})
    public void logisticsAddCompFieldNameSpace() {

        LogisticsAddCompPage logisticsAddCompPage = new LogisticsAddCompPage();
        String nameWithSpace = "Mind tek";
        logisticsAddCompPage.nameField.sendKeys(nameWithSpace);
        String expectedRes = "Mind tek";
        Assert.assertEquals(logisticsAddCompPage.nameField.getAttribute("value"),expectedRes);

    }

    @Test(groups = {"regression","smoke","logistics"})
    public void logisticsAddCompSelectComp1() {

        LogisticsAddCompPage logisticsAddCompPage = new LogisticsAddCompPage();
        Select selectCompType = new Select(logisticsAddCompPage.companyTypeDropDown);
        selectCompType.selectByIndex(0);
        String actualRes = logisticsAddCompPage.companyTypeDropDown.getAttribute("value");

        Assert.assertEquals(actualRes, "broker company");

    }

    @Test(groups = {"regression","smoke","logistics"})
    public void logisticsAddCompSelectStatus1() {

        LogisticsAddCompPage logisticsAddCompPage = new LogisticsAddCompPage();
        Select selectStatus = new Select(logisticsAddCompPage.statusTypeDropDown);
        selectStatus.selectByVisibleText("Active");
        String actualRes = logisticsAddCompPage.statusTypeDropDown.getAttribute("value");

        Assert.assertEquals(actualRes, "active");

    }

    @Test(groups = {"regression","smoke","logistics"})
    public void logisticsAddCompSelectStatusNonActive() {

        LogisticsAddCompPage logisticsAddCompPage = new LogisticsAddCompPage();
        Select selectStatus = new Select(logisticsAddCompPage.statusTypeDropDown);
        selectStatus.selectByVisibleText("Non-active");
        String actualRes = logisticsAddCompPage.statusTypeDropDown.getAttribute("value");

        Assert.assertEquals(actualRes, "non-active");
    }

    @Test(groups = {"regression","smoke","logistics"})
    public void logisticsAddCompSelectStatusPreclosure() {

        LogisticsAddCompPage logisticsAddCompPage = new LogisticsAddCompPage();
        Select selectStatus = new Select(logisticsAddCompPage.statusTypeDropDown);
        selectStatus.selectByVisibleText("Preclosure");
        String actualRes = logisticsAddCompPage.statusTypeDropDown.getAttribute("value");

        Assert.assertEquals(actualRes, "preclosure");
    }




}

