package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutPage {


    private static WebDriver driver;
    @FindBy(id="logout2")
    WebElement logOutLink;

    @FindBy(id="nameofuser")
    WebElement nameUserLink;

    public LogOutPage(WebDriver driver)
    {
        LogOutPage.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Obtenemos el nombre del usuario")
    public String getName(){
        return nameUserLink.getText();
    }


    @Step("Cerramos la sesion con el metodo LogOut")
    public void logOut(){
        logOutLink.click();

    }

}
