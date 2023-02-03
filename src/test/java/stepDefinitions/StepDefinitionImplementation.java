package stepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.project.pageobject.*;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class StepDefinitionImplementation extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalog productCatalog;
    public CartPage cartPage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {

        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String username, String password){
        productCatalog = landingPage.loginApplication(username,password);
    }

    @Then("{string} message is displayed")
    public void message_is_displayed_on_LoginPage(String string){
        Assert.assertEquals(string,landingPage.getErrorMessage());
        tearDown();
    }

    @When("^I add product (.+) to Cart$")
    public void I_add_product_to_Cart(String productName){
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName){
        cartPage = productCatalog.goToCartPage();
        Boolean match = cartPage.verifyProducts(productName);
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.selectCountry("India");
        confirmationPage = checkoutPage.placeOrder();
    }

    @Then("{string} message is displayed on Confirmation Page")
    public void message_is_displayed_on_Confirmation_Page(String string){
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
        tearDown();
    }
}
