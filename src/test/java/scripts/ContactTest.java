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
import pages.BasePage;
import pages.ContactPage;
import pages.HomePage;
import pages.SignUpPage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class ContactTest {

    WebDriver driver;
    Alert alert;

    @BeforeMethod
    public void setup() {
        Path driverPath = Paths.get("Driver", "chromedriver.exe").toAbsolutePath();
        System.setProperty("webdriver.chrome.driver", driverPath.toString());
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


    }


    @Parameters({"emailParam","nameParam","messageParam"})
    @Test()
    public void testSignUp(String email, String name, String message) throws InterruptedException {
        HomePage homePage = new HomePage(driver);

        Thread.sleep(5000);
        ContactPage contactPage = homePage.goToContactPage();;
        Thread.sleep(5000);
        contactPage.contactForm(email,name,message);


        String txt = BasePage.handleAlert(driver, alert);	// Handling expected alert

        try
        {
            Assert.assertEquals(txt,"Thanks for the message!!","No se pudo acceder a la alerta");		// Validating pop up's message
            System.out.println("New User successfully created");
        }
        catch(Exception e)
        {
            System.out.println("Failed to create new User");
        }




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
