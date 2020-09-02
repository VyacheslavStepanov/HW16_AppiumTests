package PageObject;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends Main{
    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    private static final String
        searchButton = "//android.widget.TextView[@content-desc=\"Search\"]",
        searchInput = "//*[@resource-id='dev.akat.filmreel:id/search_src_text']",
        currentFilm = "//*[contains(@text, '{substring}')]";

    public String getResultAndReplace(String substring)
    {
        return currentFilm.replace("{substring}", substring);
    }
    public void initSearch()
    {
        this.waitForElementAndClick(By.xpath(searchButton), "Не могу найти кнопку поиска", 5 );
    }

    public WebElement searchInput(String searchValue)
    {
        WebElement input = this.waitForElementAndSendKeys(By.xpath(searchInput), searchValue, "Не могу найти инпут", 5);
        return input;
    }

    public void clickFilm(String substring)
    {
        String qqq = getResultAndReplace(substring);
        this.waitForElementAndClick(By.xpath(qqq), "Не могу найти фильм " + substring, 5);
    }

    public WebElement clearSearchInput()
    {
        return this.clearInput(By.xpath(searchInput),"Не могу найти поле поиска", 5);
    }
}
