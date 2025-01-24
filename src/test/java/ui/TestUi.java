package ui;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestUi {

    private WebDriver driver;
    private Faker faker;

    public TestUi() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");
        faker = new Faker();
    }

    @Test
    public void test() throws InterruptedException {

        // регистрация

        String username = faker.name().username(),
                password = faker.internet().password();
        driver.findElement(By.xpath("//a[@id='signin2']")).click();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.register(username, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        // авторизация

        driver.findElement(By.xpath("//a[@id='login2']")).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // добавление продуктов в корзину

        String[] categories = {"Phones", "Laptops", "Monitors"};

        for (String category : categories) {
            addProduct(category, wait);
        }

        // проверка общей цены

        driver.findElement(By.xpath("//a[@id='cartur']")).click();

        Thread.sleep(1000);

        CartPage cartPage = new CartPage(driver);

        List<WebElement> trs = driver.findElements(By.xpath("//tr[@class='success']"));
        List<WebElement> tds;

        int totalPrice = 0;

        for (int i = 0; i < trs.size(); i++) {
            tds = trs.get(i).findElements(By.xpath("./*"));
            totalPrice += Integer.parseInt(tds.get(2).getText());
        }

        Assertions.assertEquals(cartPage.getTotalPrice(), totalPrice);

        // оформление заказа

        cartPage.placeOrder();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.purchase(faker.name().fullName(), faker.address().country(), faker.address().city(), faker.finance().creditCard(), "12", "2025");

        String textOfSuccessPurchasing = driver.findElement(By.xpath("//p[@class='lead text-muted ']")).getText();

        // проверка даты

        Assertions.assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/M/yyyy")), getDate(textOfSuccessPurchasing));

    }

    private void addProduct(String category, WebDriverWait wait) throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.xpath("//a[text()='" + category + "']")).click();
        Thread.sleep(500);
        WebElement div = driver.findElement(By.xpath("//div[@class='card h-100']"));
        int priceInList = Integer.parseInt(div.findElement(By.xpath(".//h5")).getText().substring(1)); // цена из общего списка
        div.click();
        String priceOnCard = driver.findElement(By.xpath("//h3[@class='price-container']")).getText(); // цена с карточки товара (в виде строки)
        Assertions.assertEquals(priceInList, Integer.parseInt(priceOnCard.substring(1, priceOnCard.indexOf(" "))));
        driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.navigate().to("https://www.demoblaze.com/index.html");
    }

    private static String getDate(String textOfSuccessPurchasing) {
        String[] lines = textOfSuccessPurchasing.split("\\n");
        String date = "";
        for (String line : lines) {
            if (line.startsWith("Date:")) {
                date = line.substring(line.indexOf(":") + 2);
                break;
            }
        }
        return date;
    }

}
