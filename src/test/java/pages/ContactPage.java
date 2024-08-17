package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

    private static WebDriver driver;

    @FindBy(id = "recipient-email")
    WebElement textEmail;

    @FindBy(id = "recipient-name")
    WebElement textName;

    @FindBy(id = "message-text")
    WebElement textMessage;

    @FindBy(css = "#exampleModal .btn-primary")
    WebElement btnEnviar;


    public ContactPage(WebDriver driver)
    {
        ContactPage.driver=driver;
        PageFactory.initElements(driver, this);

    }

    @Step("Ingresamos la informacion de contacto")
    public void contactForm(String email, String name, String message)
    {
        BasePage.sendKeys(driver, textEmail, email);
        BasePage.sendKeys(driver, textName, name);
        BasePage.sendKeys(driver, textMessage, message);

        BasePage.javaScriptClick(driver, btnEnviar);

    }

}
