package PageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class Main {
    public static AppiumDriver driver;
    public Main (AppiumDriver driver)
    {
        this.driver = driver;
    }

    public static WebElement clearInput(By by, String error_message, long timeOutInSecond)
    {
        WebElement element = waitForElement(by, error_message, timeOutInSecond);
        element.clear();
        return element;
    }

    public static Boolean isElementVisible(By locator, long timeOutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static WebElement waitForElement(By by, String error_message, long timeOutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement waitForElement(By by, String error_message)
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement waitForElementByXPath (String xpath, String error_message, long timeOutInSecond)
    {
        return waitForElement(By.xpath(xpath), error_message, timeOutInSecond);
    }

    public static WebElement waitForElementAndSendKeys(By locator, String value, String error_message, long timeOutInSecond)
    {
        WebElement element = waitForElement(locator, error_message, timeOutInSecond);
        element.sendKeys(value);
        return element;
    }

    public static void waitForElementAndClick(By locator, String error_message, long timeOutInSecond)
    {
        WebElement element = waitForElement(locator, error_message, timeOutInSecond);
        element.click();
    }

    public static WebElement waitForElementByXPathAndClick (String xpath, String error_message, long timeOutInSecond)
    {
        WebElement element = waitForElement(By.xpath(xpath), error_message, timeOutInSecond);
        element.click();
        return element;
    }

    public static WebElement waitForElementById (String id, String error_message, long timeOutInSecond)
    {
        return waitForElement(By.id(id), error_message, timeOutInSecond);
    };

    public static WebElement waitForElementByIdAndClick (String id, String error_message, long timeOutInSecond)
    {
        WebElement element = waitForElementById(id, error_message, timeOutInSecond);
        element.click();
        return element;
    }

    public static WebElement waitForElementByXpath(String xpath, String error_message)
    {
        return waitForElement(By.xpath(xpath), error_message, 5);
    }

    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }

        return sb.toString();
    }
    public static String getRandomEmail()
    {
        String candidateChars = "abcdefhijklmnoprstuvwx";
        return generateRandomChars(candidateChars, 8).concat("@mail.ru");
    }
    public static String getRandomPassword(int length)
    {
        String candidateChars = "ABCDEFGHIJKLMNOPRSTUVWXabcdefhijklmnoprstuvwx1234567890";
        return generateRandomChars(candidateChars, length);

    }

    public static void swipe(int fromX, int fromY, int toX, int toY)
    {
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(fromX,fromY)).waitAction().moveTo(PointOption.point(toX,toY)).release().perform();
    }

    public static void swipeUp()
    {
        Dimension size = driver.manage().window().getSize();

        int x = size.width/2;
        int start_y = (int)(size.height*0.8);
        int end_y = (int)(size.height*0.2);

        swipe(x, start_y, x, end_y);
    }

    public static void swipeRight()
    {
        Dimension size = driver.manage().window().getSize();

        int start_x = (int)(size.width*0.8);
        int end_x = (int)(size.width*0.2);
        int y = size.height/2;

        swipe(start_x, y, end_x, y);
    }

    public static void swipeUpToFindElement(By by, String error_message, int max_swipe)
    {
        int already_swipe = 0;
        while (driver.findElements(by).size() == 0){

            if (already_swipe > max_swipe){
                waitForElement(by,"Закончились свапы" + " " + error_message);
                return;
            }

            swipeUp();
            ++ already_swipe;
        }
    }

    public static void changeOrientationToLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

}
