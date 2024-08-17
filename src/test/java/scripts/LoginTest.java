package scripts;

import io.qameta.allure.Attachment;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogOutPage;
import pages.LoginPage;
import pages.SignUpPage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    Alert alert;
    LogOutPage logOutPage;


    @BeforeMethod
    public void setup() {
        Path driverPath = Paths.get("Driver", "chromedriver.exe").toAbsolutePath();
        System.setProperty("webdriver.chrome.driver", driverPath.toString());
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));




    }

    @Parameters({"userNameParam","passwordParam"})
    @Test
    public void loginTest(String userName, String password) throws Exception
    {
        HomePage homePage = new HomePage(driver);

        Thread.sleep(5000);

        LoginPage loginPage = homePage.goToLoginPage();;
        Thread.sleep(5000);
                loginPage.login(userName, password);
        Thread.sleep(5000);
        logOutPage = new LogOutPage(driver);
        String nombreUsuario = logOutPage.getName();
        String expectedMessage = "Welcome "+userName;
        //validamos el mensaje de confirmacion de que se agrego el producto
        Assert.assertEquals(nombreUsuario,expectedMessage,"No se logueo el usuario");

        logOutPage.logOut();
        Thread.sleep(5000);
    }


    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Attachment(type = "image/png")
    @AfterMethod(alwaysRun = true)
    public byte[] takeScreenshot() throws Exception {
        byte[] image = new byte[0];
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            image = screenshot.getScreenshotAs(OutputType.BYTES);
            System.out.println("Successfully captured a screenshot");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
        return image;
    }

}
