package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='loginusername']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='loginpassword']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement logInButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        logInButton.click();
    }

}
