package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private static WebDriver driver;
    @FindBy(id="loginusername")
    WebElement loginUserName;

    @FindBy(id="loginpassword")
    WebElement loginPassword;

    @FindBy(xpath = "//button[text()='Log in']")
    WebElement loginButton;

    @FindBy(xpath = "//button[text()='Log in']//preceding-sibling::button")
    WebElement closeButton;

    public LoginPage(WebDriver driver)
    {
        LoginPage.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ingresamos la informacion del Login")
    public void login(String un, String pwd) throws Exception
    {

        BasePage.sendKeys(driver, loginUserName, un);
        BasePage.sendKeys(driver, loginPassword, pwd);

        Thread.sleep(2000);
        BasePage.javaScriptClick(driver, loginButton);
        Thread.sleep(2000);
    }



}
