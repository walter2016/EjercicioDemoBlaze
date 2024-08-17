package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private static WebDriver driver;

    public HomePage(WebDriver driver) {
        HomePage.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "a.nav-link[data-target='#exampleModal']")
    private WebElement contactLink;

    // Definimos los elementos con @FindBy utilizando los identificadores únicos
    @FindBy(xpath = "//a[@onclick=\"byCat('phone')\"]")
    private WebElement phonesLink;

    @FindBy(xpath = "//a[@onclick=\"byCat('notebook')\"]")
    private WebElement laptopsLink;

    @FindBy(xpath = "//a[@onclick=\"byCat('monitor')\"]")
    private WebElement monitorsLink;

    @FindBy(id = "signin2")
    WebElement signUpLink;

    @FindBy(id = "login2")
    WebElement loginLink;


    public void selectTypeProduct(String type) {


        switch(type.toUpperCase()) {
            case "PHONES":
                BasePage.click(driver,phonesLink);

                break;
            case "LAPTOP":
                BasePage.click(driver,laptopsLink);

                break;
            case "MONITOR":
                BasePage.click(driver,monitorsLink);

                break;
            default:
                throw new IllegalArgumentException("Type not supported: " + type);
        }
    }



    @Step("Haciendo click en el producto")
    public ProductPage clickProduct(String product){
        WebElement productLink= BasePage.obtenerLocalizadorProduct(driver,product);
        BasePage.click(driver,productLink);
        return new ProductPage(driver);
    }

    @Step("Ingresamos a la opcion de SignUp")
    public SignUpPage goToSignUpPage()
    {
        BasePage.click(driver,signUpLink);
        return new SignUpPage(driver);
    }

    @Step("Ingresamos a la opcion de LogIn")
    public LoginPage goToLoginPage()
    {
        BasePage.javaScriptClick(driver, loginLink);
        return new LoginPage(driver);
    }

    @Step("Ingresamos a la opcion de Contact")
    public ContactPage goToContactPage()
    {
        BasePage.click(driver,contactLink);
        return new ContactPage(driver);
    }

}
