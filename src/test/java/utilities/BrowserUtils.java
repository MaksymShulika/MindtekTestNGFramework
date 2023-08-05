package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class BrowserUtils {


    /**
     * This method generates random emails.
     * Ex:
     *      .getRandomEmail(); -> returns user45f-12jifiwof-ewiofjw@gmail.com
     */

    public static String getRandomEmail(){
        UUID uuid = UUID.randomUUID();
        return "user" + uuid + "@gmail.com";
    }

    /**
     * This method take screenshots.
     * Ex:
     *      .takeScreenshot("NameofScreenshot");
     */

    public static void takeScreenshot(String testName) throws IOException {
        WebDriver driver = Driver.getDriver();
                        //iner
                        //auter
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path = "src/test/resources/screenshots/"+testName+".png";
        File file = new File(path);
        FileUtils.copyFile(screenshot, file);
    }

    /**
     * This method accepts a WebElement and a String value
     * an option from a dropdown by value.
     * Ex:
     *      .selectDropdownByValue(WebElement dropdown, String value)
     */

    public static void selectDropdownByValue(WebElement dropdown, String value){
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    /**
     * This method will create a WebDriverWait object and wait for certain
     * text to be present in a WebElement.
     * Ex:
     *      .waitForTextToBePresentInElement(WebElement element, String expectedText)
     */

    public static void waitForTextToBePresentInElement(WebElement element, String expectedText){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    /**
     * This method will create a WebDriverWait object and wait for
     * element to be clickable.
     * Ex:
     *      .waitForElementToBeClickable(WebElement element)
     */

    public static void waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This method will create a WebDriverWait object and wait for
     *  element visibility of.
     * Ex:
     *      .waitForElementVisibilityOf(WebElement element)
     */

    public static void waitForElementVisibilityOf(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //HARD ASSERT
    //SOFT ASSERT

}
