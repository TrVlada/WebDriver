package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class YvesRocherCartTest {
    private static final String EXPECTED_QUANTITY = "1";
    private static WebDriver driver;

    private WebDriverWait wait;

    @BeforeMethod
    public void setUpBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.y-r.by/parfyumeriya/parfyumirovannyij-sprej-dlya-tela-i-volos-kokosovyij-orex");
    }

    @Test
    public void testAddProductToCart() throws InterruptedException {
        WebElement buttonAddToCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='price-btn unselectable eye-button ng-tns-c92-0']")));
        buttonAddToCart.click();
        WebElement quantityOfProductsInCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'product-quantity eye-black-bg eye-white-font ng-star-inserted']")));
        Assert.assertEquals(quantityOfProductsInCart.getText(), EXPECTED_QUANTITY);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        driver.quit();
        driver = null;
    }
}
