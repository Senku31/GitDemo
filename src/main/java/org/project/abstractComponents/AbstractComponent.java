package org.project.abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.project.pageobject.CartPage;
import org.project.pageobject.OrdersPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartBtn;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersBtn;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public CartPage goToCartPage(){
        waitforClickableElement(cartBtn);
        cartBtn.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }
    public OrdersPage goToOrdersPage(){
        ordersBtn.click();
        return new OrdersPage(driver);
    }

    public void waitforElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitforWebElementToAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }
    public void waitforElementToDisappear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }
    public void waitforClickableElement(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(findBy));
    }


}
