package org.project.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.project.abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = ".form-group input")
    private WebElement enterCountry;

    @FindBy(css = ".ta-item:nth-of-type(2)")
    private WebElement selectCountry;

    @FindBy(css = ".action__submit")
    private WebElement placeOrder;

    private By results = By.cssSelector(".hero-primary");

    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public void selectCountry(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(enterCountry, countryName).build().perform();
        //waitforElementToAppear(results);
        selectCountry.click();
    }
    public ConfirmationPage placeOrder() {
        placeOrder.click();
        return new ConfirmationPage(driver);
    }


}

