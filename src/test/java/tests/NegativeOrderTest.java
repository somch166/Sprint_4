package tests;

import org.junit.Test;
import pages.MainPage;
import pages.OrderPage;
import pages.OrderStatusPage;

import static org.junit.Assert.assertTrue;

public class NegativeOrderTest extends BaseTest {

    @Test
    public void testEmptyOrderFormErrors() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButtonTop();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickNextButton(); // Не заполняем поля — ждём ошибок

        // Все ожидания теперь спрятаны внутри методов Page Object
        assertTrue(orderPage.isNameErrorDisplayed());
        assertTrue(orderPage.isSurnameErrorDisplayed());
        assertTrue(orderPage.isAddressErrorDisplayed());
        assertTrue(orderPage.isMetroErrorDisplayed());
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