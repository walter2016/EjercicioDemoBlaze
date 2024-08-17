package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPage {

    private static WebDriver driver;

    @FindBy(xpath = "//a[text()='Cart']")
    WebElement cartLink;

    public AddToCartPage(WebDriver driver) {
        AddToCartPage.driver=driver;

        PageFactory.initElements(driver, this);
    }

    @Step("Ingresamos a la opcion de Cart")
    public PlaceOrderPage goToCartLink()
    {
        BasePage.click(driver, cartLink);
        return new PlaceOrderPage(driver);
    }

}
