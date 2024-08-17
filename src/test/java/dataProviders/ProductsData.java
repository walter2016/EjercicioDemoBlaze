package dataProviders;

import org.testng.annotations.DataProvider;

public class ProductsData {
    @DataProvider(name = "products")
    public static Object[][] getProductsData() {
        return new Object[][]{
                {"Samsung galaxy s6", "$360 *includes tax","PHONES"},
                {"Sony vaio i7", "$790 *includes tax","LAPTOP"},
                {"Apple monitor 24", "$400 *includes tax","MONITOR"}
        };
    }
}
