package ui.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@id='country']")
    private WebElement countryInput;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityInput;

    @FindBy(xpath = "//input[@id='card']")
    private WebElement creditCardInput;

    @FindBy(xpath = "//input[@id='month']")
    private WebElement monthInput;

    @FindBy(xpath = "//input[@id='year']")
    private WebElement yearInput;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void purchase(String name, String country, String city, String creditCard, String month, String year) throws InterruptedException {
        nameInput.sendKeys(name);
        Thread.sleep(50);
        countryInput.sendKeys(country);
        Thread.sleep(50);
        cityInput.sendKeys(city);
        Thread.sleep(50);
        creditCardInput.sendKeys(creditCard);
        Thread.sleep(50);
        monthInput.sendKeys(month);
        Thread.sleep(50);
        yearInput.sendKeys(year);
        Thread.sleep(50);
        purchaseButton.click();
    }

}
