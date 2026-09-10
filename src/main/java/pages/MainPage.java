package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    private final By orderButtonTop = By.xpath(".//button[text()='Заказать' and not(contains(@class, 'Button_Middle'))]");
    private final By orderButtonBottom = By.xpath(".//button[text()='Заказать' and contains(@class, 'Button_Middle')]");
    private final By orderStatusButton = By.xpath(".//button[text()='Статус заказа']");
    private final By scooterLogo = By.className("Header_LogoScooter__3lsAR");
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public void clickAccordionQuestion(int index) {
        By question = By.id("accordion__heading-" + index);
        driver.findElement(question).click();
    }

    public String getAccordionAnswerText(int index) {
        By answer = By.id("accordion__panel-" + index);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(answer));
        return driver.findElement(answer).getText();
    }
}