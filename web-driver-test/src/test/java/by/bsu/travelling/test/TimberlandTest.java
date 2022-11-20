package by.bsu.travelling.test;

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
    private static WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testAddItemToCart() {
        driver.get("https://www.timberland.com/shop/mens-davis-square-chukka-shoes-blue-a1sf3019");
        String EXPECTED_AMOUNT = "1";

        WebElement buttonSize = driver.findElement(By.xpath("//button[@data-attribute-value='7.5']"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonSize));
        buttonSize.click();

        WebElement buttonAddToCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-add-to-cart-text='Add to Cart']")));
        buttonAddToCart.click();

        WebElement amountOfProductsInCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='topnav-utility']//span[@class='topnav-cart-qty']")));

        WebElement navigatonCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//article[@class='topnav-minicart-content-container shopcart cart-list']")));
        wait.until(ExpectedConditions.visibilityOf(navigatonCart));

        Assert.assertEquals(amountOfProductsInCart.getText(), EXPECTED_AMOUNT);
    }

    @Test
    public void testSearchItemByArticle() {
        driver.get("https://www.timberland.com/homepage.html");
        String EXPECTED_ID = "8330R236";

        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='searchTerm']")));
        searchBox.sendKeys("8330R236");

        WebElement buttonSearch = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Submit Search']")));
        buttonSearch.click();

        WebElement idOfItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='product-style-js']")));
        wait.until(ExpectedConditions.visibilityOf(idOfItem));

        Assert.assertEquals(idOfItem.getText(), EXPECTED_ID);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown()  {
        driver.quit();
        driver = null;
    }
}
