package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class SignUpPage {
    private static WebDriver driver;

    @FindBy(id = "sign-username")
    WebElement textUsername;

    @FindBy(id = "sign-password")
    WebElement textPassword;

    @FindBy(xpath = "//button[text()='Sign up']")
    WebElement signUpBtn;

    public SignUpPage(WebDriver driver)
    {
        SignUpPage.driver=driver;
        PageFactory.initElements(driver, this);

    }

    @Step("Creamos un nuevo usuario")
    public void signUp(String un, String pwd)
    {

       BasePage.sendKeys(driver, textUsername, un);
        BasePage.sendKeys(driver, textPassword, pwd);
        BasePage.javaScriptClick(driver, signUpBtn);

    }


}
