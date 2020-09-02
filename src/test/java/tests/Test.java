package tests;

import PageObject.Main;
import initPoint.CoreTestCase;
import org.junit.Assert;
import org.openqa.selenium.*;

public class Test extends CoreTestCase {

    public Main MainMethods;

    public void setUp() throws Exception
    {
        super.setUp();
        MainMethods = new Main(driver);
    }

    @org.junit.Test
    public void test_find_film()
    {

        search.initSearch();
        search.searchInput("film");
        search.clickFilm("A Serbian Film");

    }

    @org.junit.Test
    public void test_clear_search_input()
    {
        search.initSearch();
        WebElement searchInput = search.searchInput("film");
        Assert.assertEquals("Поле поиска не содержит значение film", "film", searchInput.getText());
        search.clearSearchInput();
        String value =  searchInput.getText();
        Assert.assertEquals("Поле поиска не очистилось", "", value.trim());

    }

    @org.junit.Test
    public void test_double_swipe()
    {
        Main.swipeRight();
        Main.swipeRight();
    }

    @org.junit.Test
    public void test_swipe_to_film()
    {
        swipe.swipeToCurrentElement("Parasite");
    }

    @org.junit.Test
    public void test_orientation()
    {
        Main.changeOrientationToLandscape();
    }
}
