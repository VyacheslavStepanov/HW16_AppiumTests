import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.Random;

public class Test {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability( "platformName", "android");
        capabilities.setCapability( "deviceName", "testDevice");
        capabilities.setCapability( "platformVersion", "7.0");
        capabilities.setCapability( "appPackage", "dev.akat.filmreel");
        capabilities.setCapability( "appActivity", "com.akat.filmreel.ui.MainActivity");
        capabilities.setCapability( "automationName", "Appium");
        capabilities.setCapability( "app", "/home/sweb/IdeaProjects/HW16_AppiumTests/apk/app_debug.apk");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @org.junit.Test
    public void FirstTest()
    {
        By button_search_locator = By.xpath("//android.widget.TextView[@content-desc=\"Search\"]");
        waitForElement(
                button_search_locator,
                "Не могу найти кнопку поиска",
                5
        ).click();
        By search_input_locator = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.EditText");
        WebElement element = waitForElementAndSendKeys(
                search_input_locator,
                "film",
                "Не могу найти поле поиска",
                5);
        Assert.assertEquals("Поле поиска не содержит значение film", "film", element.getText());
        clearInput(
                search_input_locator,
                "Не могу найти поле поиска",
                5);
        String value =  element.getText();
        Assert.assertEquals("Поле поиска не очистилось", "", value.trim());

    }

    private void clearInput(By by, String error_message, long timeOutInSecond)
    {
        WebElement element = waitForElement(by, error_message, timeOutInSecond);
        element.clear();
    }

    private Boolean isElementVisible(By locator, long timeOutInSecond)
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

    private WebElement waitForElement (By by, String error_message, long timeOutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by));
    }
    private WebElement waitForElementByXPath (String xpath, String error_message, long timeOutInSecond)
    {
        return waitForElement(By.xpath(xpath), error_message, timeOutInSecond);
    }

    private WebElement waitForElementAndSendKeys (By locator, String value, String error_message, long timeOutInSecond)
    {
        WebElement element = waitForElement(locator, error_message, timeOutInSecond);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementByXPathAndClick (String xpath, String error_message, long timeOutInSecond)
    {
        WebElement element = waitForElement(By.xpath(xpath), error_message, timeOutInSecond);
        element.click();
        return element;
    }

    private WebElement waitForElementById (String id, String error_message, long timeOutInSecond)
    {
        return waitForElement(By.id(id), error_message, timeOutInSecond);
    };

    private WebElement waitForElementByIdAndClick (String id, String error_message, long timeOutInSecond)
    {
        WebElement element = waitForElementById(id, error_message, timeOutInSecond);
        element.click();
        return element;
    }

    private WebElement waitForElementByXpath(String xpath, String error_message)
    {
        return waitForElement(By.xpath(xpath), error_message, 5);
    }

    private String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }

        return sb.toString();
    }
    private String getRandomEmail()
    {
        String candidateChars = "abcdefhijklmnoprstuvwx";
        return generateRandomChars(candidateChars, 8).concat("@mail.ru");
    }
    private String getRandomPassword(int length)
    {
        String candidateChars = "ABCDEFGHIJKLMNOPRSTUVWXabcdefhijklmnoprstuvwx1234567890";
        return generateRandomChars(candidateChars, length);

    }
}
