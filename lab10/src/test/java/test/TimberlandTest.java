package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.TimberlandHomePage;
import page.TimberlandItemPage;

public class TimberlandTest {
    public static String EXPECTED_ID = "8330R236";
    public static String EXPECTED_AMOUNT = "1";

    private static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAddItemToCart() {
        String idOfItem = new TimberlandItemPage(driver)
                .openPage()
                .clickSizeButton()
                .addItemToCart()
                .getAmountOfProducts();
        Assert.assertEquals(idOfItem, EXPECTED_AMOUNT);
    }

    @Test
    public void testSearchItemByArticle() {
        String idOfItem = new TimberlandHomePage(driver)
                .openPage()
                .searchForTerms(EXPECTED_ID)
                .getArticleOfItem();
        Assert.assertEquals(idOfItem, EXPECTED_ID);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown()  {
        driver.quit();
        driver = null;
    }
}