package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TimberlandTest {
    private static WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void testAddItemToCart() {
        webDriver.get("https://www.timberland.com/shop/mens-davis-square-chukka-shoes-blue-a1sf3019");
        String EXPECTED_AMOUNT = "1";

        WebElement buttonSize = webDriver.findElement(By.xpath("//button[@data-attribute-value='7.5']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonSize));
        buttonSize.click();

        WebElement buttonAddToCart = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-add-to-cart-text='Add to Cart']")));
        buttonAddToCart.click();

        WebElement amountOfProductsInCart = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='topnav-utility']//span[@class='topnav-cart-qty']")));

        WebElement navigatonCart = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//article[@class='topnav-minicart-content-container shopcart cart-list']")));
        webDriverWait.until(ExpectedConditions.visibilityOf(navigatonCart));

        Assert.assertEquals(amountOfProductsInCart.getText(), EXPECTED_AMOUNT);
    }

    @Test
    public void testSearchItemByArticle() {
        webDriver.get("https://www.timberland.com/homepage.html");
        String EXPECTED_ID = "8330R236";

        WebElement searchBox = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='searchTerm']")));
        searchBox.sendKeys("8330R236");

        WebElement buttonSearch = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Submit Search']")));
        buttonSearch.click();

        WebElement idOfItem = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='product-style-js']")));
        webDriverWait.until(ExpectedConditions.visibilityOf(idOfItem));

        Assert.assertEquals(idOfItem.getText(), EXPECTED_ID);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown()  {
        webDriver.quit();
        webDriver = null;
    }
}
