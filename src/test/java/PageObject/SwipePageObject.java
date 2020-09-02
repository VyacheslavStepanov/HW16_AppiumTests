package PageObject;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SwipePageObject extends Main {
    public SwipePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    private static final String
        swipeToFindElement = "//*[contains(@text, '{substring}')]";

    public String getResultAndReplace(String substring)
    {
        return swipeToFindElement.replace("{substring}", substring);
    }

    public void swipeToCurrentElement(String element_name)
    {
        String xpath = getResultAndReplace(element_name);
        this.swipeUpToFindElement(By.xpath(xpath), "Не могу найти нужный фильм " + element_name, 10);
    }
}
