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
    private static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

//    @Test
//    public void testAddItemToCart() {
//        driver.get("https://www.timberland.com/shop/mens-davis-square-chukka-shoes-blue-a1sf3019");
//        String EXPECTED_AMOUNT = "1";
//
//        WebElement buttonSize = new WebDriverWait(driver, Duration.ofSeconds(20))
//                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-attribute-value='7.5']")));
//
//        //        Thread.sleep(3000);
//        buttonSize.click();
//
//        WebElement buttonAddToCart = new WebDriverWait(driver, Duration.ofSeconds(5))
//                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-add-to-cart-text='Add to Cart']")));
//        buttonAddToCart.click();
//
//        WebElement amountOfProductsInCart = new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='topnav-utility']//span[@class='topnav-cart-qty']")));
//
//        //        Thread.sleep(3000);
//        Assert.assertEquals(amountOfProductsInCart.getText(), EXPECTED_AMOUNT);
//    }

    @Test
    public void testAddItemToCartWithThreadSleep() throws InterruptedException {
        driver.get("https://www.timberland.com/shop/mens-davis-square-chukka-shoes-blue-a1sf3019");
        String EXPECTED_AMOUNT = "1";
        WebElement buttonSize = driver.findElement(By.xpath("//button[@data-attribute-value='7.5']"));
        WebElement buttonAddToCart =  driver.findElement(By.xpath("//button[@data-add-to-cart-text='Add to Cart']"));
        WebElement amountOfProductsInCart =   driver.findElement(By.xpath("//ul[@class='topnav-utility']//span[@class='topnav-cart-qty']"));

        Thread.sleep(3000);
        buttonSize.click();
        buttonAddToCart.click();
        Thread.sleep(3000);
        Assert.assertEquals(amountOfProductsInCart.getText(), EXPECTED_AMOUNT);
    }

    @Test
    public void testSearchItemByArticle() {
        driver.get("https://www.timberland.com/homepage.html");
        String EXPECTED_ID = "8330R236";

        WebElement searchBox = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='searchTerm']")));
        searchBox.sendKeys("8330R236");

        WebElement buttonSearch = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Submit Search']")));
        buttonSearch.click();

        WebElement idOfItem = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='product-style-js']")));

        Assert.assertEquals(idOfItem.getText(), EXPECTED_ID);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown()  {
        driver.quit();
        driver = null;
    }
}
