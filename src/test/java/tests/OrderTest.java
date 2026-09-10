package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import pages.OrderPage;
import pages.RentPage;
import static org.junit.Assert.assertTrue;

//баг в хроме тут. найден

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private final String name, surname, address, station, phone, date, period;

    public OrderTest(String name, String surname, String address, String station, String phone, String date, String period) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.period = period;
    }

    // Два набора данных

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"Иван", "Петров", "ул. Ленина, 1", "Аэропорт", "89991112233", "01.01.2025", "сутки"},
                {"Анна", "Смирнова", "пр. Мира, 2", "Бауманская", "89991112244", "02.01.2025", "двое суток"}
        };
    }

    @Test
    public void orderFlowTopButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButtonTop();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.enterMetroStation(station);
        orderPage.enterPhone(phone);
        orderPage.clickNextButton();

        RentPage rentPage = new RentPage(driver);
        rentPage.enterDate(date);
        rentPage.selectRentalPeriod(period);
        rentPage.checkBlackColor();
        rentPage.clickOrderButton();
        assertTrue("Модальное окно не появилось", rentPage.isOrderModalDisplayed());
    }

    /**
     * проверяю заказ через верхнюю кнопку "Заказать": чёрный цвет самоката,
     * остальные параметры из параметризованного набора данных
     */

    @Test
    public void orderFlowBottomButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButtonBottom();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.enterMetroStation(station);
        orderPage.enterPhone(phone);
        orderPage.clickNextButton();

        RentPage rentPage = new RentPage(driver);
        rentPage.enterDate(date);
        rentPage.selectRentalPeriod(period);
        rentPage.checkGreyColor();
        rentPage.clickOrderButton();
        assertTrue("Модальное окно не появилось", rentPage.isOrderModalDisplayed());
    }
}