package initPoint;
import PageObject.SearchPageObject;
import PageObject.SwipePageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CoreTestCase extends TestCase {
    public AppiumDriver driver;
    public String AppiumUrl = "http://localhost:4723/wd/hub";
    public SearchPageObject search = new SearchPageObject(driver);
    public SwipePageObject swipe = new SwipePageObject(driver);

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability( "platformName", "android");
        capabilities.setCapability( "deviceName", "testDevice");
        capabilities.setCapability( "platformVersion", "7.1.1");
        capabilities.setCapability( "appPackage", "dev.akat.filmreel");
        capabilities.setCapability( "appActivity", "com.akat.filmreel.ui.MainActivity");
        capabilities.setCapability( "automationName", "Appium");
        capabilities.setCapability( "app", "/home/sweb/IdeaProjects/HW16_AppiumTests/apk/app_debug.apk");
        capabilities.setCapability( "orientation", "PORTRAIT");
        driver = new AndroidDriver(new URL(AppiumUrl), capabilities);



    }

    @Override
    public void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }
}
