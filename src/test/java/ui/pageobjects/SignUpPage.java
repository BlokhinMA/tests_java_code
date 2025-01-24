package ui.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='sign-username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='sign-password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement signUpButton;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void register(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signUpButton.click();
    }

}
