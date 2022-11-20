package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimberlandHomePage extends AbstractPage {
    public static String TIMBERLAND_HOME_PAGE_URL = "https://www.timberland.com/homepage.html";

    @FindBy(xpath = "//input[@name='searchTerm']")
    private WebElement searchBox;

    public TimberlandHomePage(WebDriver driver) {
        super(driver);
    }

    public TimberlandHomePage openPage() {
        driver.get(TIMBERLAND_HOME_PAGE_URL);
        new WebDriverWait(driver, waitWebDriver)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='searchTerm']")));
        return this;
    }

    public TimberlandHomePage searchForTerms(String searchQuery) {
       new WebDriverWait(driver, waitWebDriver)
                .until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.sendKeys(searchQuery);
        searchBox.sendKeys(Keys.ENTER);
        return new TimberlandHomePage(driver);
    }

    public String getArticleOfItem() {
        WebElement idOfItem =  new WebDriverWait(driver, waitWebDriver)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='product-style-js']")));
        new WebDriverWait(driver, waitWebDriver)
                .until(ExpectedConditions.visibilityOf(idOfItem));
        return idOfItem.getText();
    }

}