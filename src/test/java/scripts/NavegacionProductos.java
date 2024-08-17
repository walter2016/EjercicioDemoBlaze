package scripts;

import dataProviders.ProductsData;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class NavegacionProductos {
    WebDriver driver;


    @BeforeMethod
    public void setup() {
        Path driverPath = Paths.get("Driver", "chromedriver.exe").toAbsolutePath();
        System.setProperty("webdriver.chrome.driver", driverPath.toString());
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


    }

   // @Parameters({"productParam","priceParam","typeParam"})
    @Test(dataProvider = "products", dataProviderClass = ProductsData.class)
    public void testBrowsePhones(String product, String price, String type) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.selectTypeProduct(type);
        Thread.sleep(5000);
        ProductPage productPage = homePage.clickProduct(product);
        Thread.sleep(5000);
        if (productPage.getPrice().contains(price)) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();

        }

        Assert.assertEquals(productPage.getPrice(), price);
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

