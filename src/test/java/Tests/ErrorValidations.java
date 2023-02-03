package Tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.project.pageobject.CartPage;
import org.project.pageobject.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test(groups = {"ErrorHandling"})
    public void LoginErrorValidation() throws IOException {

        String productName = "ZARA COAT 3";
        landingPage.loginApplication("hgfhfgh@gmail.com", "Dupa1234456");
        Assert.assertEquals("Incorrect emaoil or password.",landingPage.getErrorMessage());
    }
    @Test
    public void ProductErrorValidation() throws IOException {

        String productName = "ZARA COAT 3";
        ProductCatalog productCatalog = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.verifyProducts("ZARA COAT 33");
        Assert.assertFalse(match);

    }
}