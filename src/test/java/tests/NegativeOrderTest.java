package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.OrderPage;
import pages.OrderStatusPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class NegativeOrderTest extends BaseTest {

    @Test
    public void testEmptyOrderFormErrors() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButtonTop();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickNextButton(); // Не заполняем поля — ждём ошибок

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Ожидание и проверка ошибки для поля "Имя"
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//input[@placeholder='* Имя']/following-sibling::div[contains(@class, 'Input_ErrorMessage')]")
        ));
        assertTrue(orderPage.isNameErrorDisplayed());

        // Ожидание и проверка ошибки для поля "Фамилия"
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//input[@placeholder='* Фамилия']/following-sibling::div[contains(@class, 'Input_ErrorMessage')]")
        ));
        assertTrue(orderPage.isSurnameErrorDisplayed());

        // Ожидание и проверка ошибки для поля "Адрес"
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']/following-sibling::div[contains(@class, 'Input_ErrorMessage')]")
        ));
        assertTrue(orderPage.isAddressErrorDisplayed());

        // Ожидание и проверка ошибки для поля "Станция метро"
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("Order_MetroError__1BtZb")
        ));
        assertTrue(orderPage.isMetroErrorDisplayed());

        // Ожидание и проверка ошибки для поля "Телефон"
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']/following-sibling::div[contains(@class, 'Input_ErrorMessage')]")
        ));
        assertTrue(orderPage.isPhoneErrorDisplayed());
    }

    @Test
    public void testInvalidOrderNumberShowsNotFound() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderStatusButton();

        OrderStatusPage statusPage = new OrderStatusPage(driver);
        statusPage.enterOrderNumber("999999");
        statusPage.clickViewButton();

        assertTrue("Сообщение об ошибке не появилось", statusPage.isNotFoundDisplayed());
    }
}