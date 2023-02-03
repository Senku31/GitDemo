package Tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.project.pageobject.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider="getData",groups = "Purchase" , retryAnalyzer = Retry.class)
    public void submitOrder(HashMap<String,String> input) throws IOException {

        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.verifyProducts(input.get("productName"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.selectCountry("India");
        ConfirmationPage confirmationPage = checkoutPage.placeOrder();
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
    }
    @Test(dataProvider = "getData", dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest(HashMap<String,String> input){
        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
        OrdersPage ordersPage = productCatalog.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrderProducts(input.get("productName")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/Data/PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
        /*        HashMap<String,String> map1 = new HashMap<String,String>();
        map1.put("email", "Dupa321@gmail.com");
        map1.put("password","Dupa123456");
        map1.put("productName","ZARA COAT 3");
        HashMap<String,String> map2 = new HashMap<String,String>();
        map2.put("email", "rahulshetty@gmail.com");
        map2.put("password","Iamking@000");
        map2.put("productName","Adidas Original");*/

}