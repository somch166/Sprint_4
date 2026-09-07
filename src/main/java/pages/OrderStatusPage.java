package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderStatusPage {
    private final WebDriver driver;

    private final By orderNumberInput = By.xpath(".//input[contains(@class, 'Track_Input__1g7lq')]");
    private final By viewButton = By.xpath(".//button[text()='Посмотреть']");
    private final By notFoundBlock = By.className("Track_NotFound__6oaoY");

    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterOrderNumber(String number) {
        driver.findElement(orderNumberInput).sendKeys(number);
    }

    public void clickViewButton() {
        driver.findElement(viewButton).click();
    }

    public boolean isNotFoundDisplayed() {
        return driver.findElement(notFoundBlock).isDisplayed();
    }
}