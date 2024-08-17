package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductPage {

    private static WebDriver driver;


    @FindBy(css = "h3.price-container")
    WebElement price;

    @FindBy(xpath = "//a[text()='Add to cart']")
    private WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        ProductPage.driver=driver;

        PageFactory.initElements(driver, this);
    }

    @Step("Obteniendo precio del producto")
    public String getPrice() throws InterruptedException {
        Thread.sleep(2000);
        return price.getText();
    }


    @Step("Agregamos el producto al carrito")
    public AddToCartPage goToAddCart()
    {
        BasePage.javaScriptClick(driver, addToCartButton);
        return new AddToCartPage(driver);
    }


}
