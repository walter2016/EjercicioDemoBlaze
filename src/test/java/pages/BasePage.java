package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public static void javaScriptClick(WebDriver driver, WebElement ele)
    {
        explicitWait(driver, 10, ele);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", ele);

    }

    public static void explicitWait(WebDriver driver, int Seconds, WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void sendKeys(WebDriver driver, WebElement element, String text)
    {
        explicitWait(driver, 10, element);
        element.clear();
        element.sendKeys(text);
    }

    public static String handleAlert(WebDriver driver, Alert alert)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        return alertText;
    }

    public static void click(WebDriver driver, WebElement element)
    {
        explicitWait(driver, 10, element);
        element.click();
    }

    public static WebElement obtenerLocalizadorProduct(WebDriver driver, String product)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productLink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(product)));
        return productLink;
    }

}
