package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrderPage {

    private static WebDriver driver;


    @FindBy(xpath = "//button[text()='Place Order']")
    WebElement placeOrderBtn;

    public PlaceOrderPage(WebDriver driver) {
        PlaceOrderPage.driver=driver;

        PageFactory.initElements(driver, this);
    }

    @Step("Ingresamos a la pagina de pago")
    public PaymentPage goToPaymentPage()
    {

        BasePage.click(driver, placeOrderBtn);

        return new PaymentPage(driver);
    }
}
