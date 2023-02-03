package org.project.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.project.abstractComponents.AbstractComponent;

import java.util.List;

public class ProductCatalog extends AbstractComponent {

    WebDriver driver;
    public ProductCatalog(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

    @FindBy(css=".mb-3")
    List<WebElement> products;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastContainer = By.cssSelector("#toast-container");
    By loadingIcon = By.cssSelector(".ng-animating");

    public List<WebElement> getProductList(){
        waitforElementToAppear(productsBy);
        return products;
    }
    public WebElement getProductByName(String productName){
        WebElement prod = getProductList().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return prod;
    }
    public void addProductToCart(String productName){
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitforElementToAppear(toastContainer);
        waitforElementToDisappear(loadingIcon);
    }
}
