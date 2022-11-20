package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected int waitTimeOutInSeconds;

    public AbstractPage(WebDriver driver, int waitTimeOutInSeconds) {
        this.driver = driver;
        this.waitTimeOutInSeconds = waitTimeOutInSeconds;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, waitTimeOutInSeconds);
        PageFactory.initElements(factory, this);
    }
}