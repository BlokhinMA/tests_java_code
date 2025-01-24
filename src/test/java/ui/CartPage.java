package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    private WebDriver driver;

    @FindBy(xpath = "//h3[@id='totalp']")
    private WebElement totalPrice;

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getTotalPrice() {
        return Integer.parseInt(totalPrice.getText());
    }

    public void placeOrder() {
        placeOrderButton.click();
    }

}
