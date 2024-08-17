package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

    private static WebDriver driver;

    @FindBy(id = "name")
    WebElement name;

    @FindBy(id = "country")
    WebElement country;

    @FindBy(id="city")
    WebElement city;

    @FindBy(id = "card")
    WebElement card;

    @FindBy(id = "month")
    WebElement month;


    @FindBy(id = "year")
    WebElement year;

    @FindBy(xpath="//button[text()='Purchase']")
    WebElement purchaseBtn;

    public PaymentPage(WebDriver driver)
    {
        PaymentPage.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ingresamos los datos del pago")
    public void InsertDetails(String nameText, String countryText, String cityText, String cardText,String monthText, String yearText)
    {

        BasePage.sendKeys(driver, name, nameText);
        BasePage.sendKeys(driver, country, countryText);
        BasePage.sendKeys(driver, city, cityText);
        BasePage.sendKeys(driver, card, cardText);
        BasePage.sendKeys(driver, month, monthText);
        BasePage.sendKeys(driver, year, yearText);
        BasePage.javaScriptClick(driver, purchaseBtn);

    }



    public String getSuccessMsg()
    {
        String msg = driver.findElement(By.xpath("//h2[text()='Thank you for your purchase!']")).getText();

        return msg;

    }
}
