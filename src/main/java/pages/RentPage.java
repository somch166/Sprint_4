package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RentPage {
    private final WebDriver driver;

    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-control");
    private final By blackColor = By.id("black");
    private final By greyColor = By.id("grey");
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[text()='Заказать']");
    private final By orderModal = By.className("Order_Modal__YZ-d3");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterDate(String date) {
        driver.findElement(dateInput).sendKeys(date);
        // Костыль: кликаю по кнопкам, чтобы календарь закрылся
        driver.findElement(By.className("Order_Buttons__1xGrp")).click();
    }

    public void selectRentalPeriod(String period) {
        driver.findElement(rentalPeriodDropdown).click();
        By option = By.xpath(".//div[contains(@class, 'Dropdown-option') and text()='" + period + "']");
        driver.findElement(option).click();
    }

    public void checkBlackColor() {
        driver.findElement(blackColor).click();
    }

    public void checkGreyColor() {
        driver.findElement(greyColor).click();
    }

    public void enterComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    // Проверяю что после заказа появилось модальное окно с подтверждением
    public boolean isOrderModalDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderModal));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}