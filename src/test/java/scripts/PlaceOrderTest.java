package scripts;

import dataProviders.ProductsData;
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
import pages.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class PlaceOrderTest {


    WebDriver driver;
    String alertTxt;
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


    @Parameters({"productParam","typeParam","nameParam","countryParam","cityParam","cardParam","monthParam","yearParam"})
    @Test()
    public void testBrowsePhones(String product, String type,String name, String country, String city, String card, String month, String year) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.selectTypeProduct(type);
        Thread.sleep(5000);
        ProductPage productPage = homePage.clickProduct(product);
        Thread.sleep(5000);
       AddToCartPage addToCartPage= productPage.goToAddCart();
        Thread.sleep(2000);

        alertTxt = BasePage.handleAlert(driver, alert);

        Assert.assertEquals(alertTxt, "Product added");
      PlaceOrderPage placeOrderPage = addToCartPage.goToCartLink();

     PaymentPage paymentPage = placeOrderPage.goToPaymentPage();
     paymentPage.InsertDetails(name,country,city,card,month,year);
        Assert.assertEquals(paymentPage.getSuccessMsg(), "Thank you for your purchase!");
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
