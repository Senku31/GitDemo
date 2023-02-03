package org.project.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.project.abstractComponents.AbstractComponent;

import java.util.List;

public class OrdersPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(xpath = "//tbody/tr/td[2]")
    private List<WebElement> productsOrdered;

    public OrdersPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public boolean verifyOrderProducts(String productName){
        Boolean match = productsOrdered.stream().anyMatch(productOrdered-> productOrdered.getText().equalsIgnoreCase(productName));
        return match;
    }

}
