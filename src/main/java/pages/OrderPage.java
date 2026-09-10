package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    // Поля первой формы
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    private final By firstStation = By.xpath(".//div[contains(@class, 'select-search__select')]//li[1]");

    // Локаторы ошибок — вынесены в поля класса
    private final By nameError = By.xpath(".//input[@placeholder='* Имя']/following-sibling::div[contains(@class, 'Input_ErrorMessage')]");
    private final By surnameError = By.xpath(".//input[@placeholder='* Фамилия']/following-sibling::div[contains(@class, 'Input_ErrorMessage')]");
    private final By addressError = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']/following-sibling::div[contains(@class, 'Input_ErrorMessage')]");
    private final By phoneError = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']/following-sibling::div[contains(@class, 'Input_ErrorMessage')]");
    private final By metroError = By.className("Order_MetroError__1BtZb");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void enterSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void enterAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void enterMetroStation(String station) {
        driver.findElement(metroInput).sendKeys(station);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(firstStation))
                .click();
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Методы для проверки ошибок — теперь используют поля класса
    public boolean isNameErrorDisplayed() {
        return driver.findElement(nameError).isDisplayed();
    }

    public boolean isSurnameErrorDisplayed() {
        return driver.findElement(surnameError).isDisplayed();
    }

    public boolean isAddressErrorDisplayed() {
        return driver.findElement(addressError).isDisplayed();
    }

    public boolean isPhoneErrorDisplayed() {
        return driver.findElement(phoneError).isDisplayed();
    }

    public boolean isMetroErrorDisplayed() {
        return driver.findElement(metroError).isDisplayed();
    }
}