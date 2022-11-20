package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimberlandItemPage extends AbstractPage{
    public static String TIMBERLAND_ITEM_PAGE_URL = "https://www.timberland.com/shop/mens-davis-square-chukka-shoes-blue-a1sf3019";

    @FindBy(xpath = "//button[@data-attribute-value='7.5']")
    private WebElement buttonSize;

    @FindBy(xpath = "//button[@data-add-to-cart-text='Add to Cart']")
    private WebElement addToCartButton;

    public TimberlandItemPage(WebDriver driver) {
        super(driver);
    }

    public TimberlandItemPage clickSizeButton() {
        new WebDriverWait(driver, waitWebDriver)
                .until(ExpectedConditions.elementToBeClickable(buttonSize));
        buttonSize.click();
        return this;
    }

    public TimberlandItemPage addItemToCart() {
        new WebDriverWait(driver, waitWebDriver)
                .until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        return this;
    }

    public String getAmountOfProducts() {
        WebElement navigationCart = new WebDriverWait(driver, waitWebDriver)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//article[@class='topnav-minicart-content-container shopcart cart-list']")));
        WebElement amountOfProductsInCart = new WebDriverWait(driver, waitWebDriver)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='topnav-utility']//span[@class='topnav-cart-qty']")));
        new WebDriverWait(driver, waitWebDriver)
                .until(ExpectedConditions.visibilityOf(navigationCart));
        return amountOfProductsInCart.getText();
    }

    @Override
    public TimberlandItemPage openPage() {
        driver.get(TIMBERLAND_ITEM_PAGE_URL);
        return this;
    }
}