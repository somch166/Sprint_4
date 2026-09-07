package tests;

import org.junit.Test;
import pages.MainPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogoTest extends BaseTest {

    @Test
    public void testScooterLogoLeadsToMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButtonTop();
        mainPage.clickScooterLogo();
        assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

    @Test
    public void testYandexLogoOpensNewWindow() {
        MainPage mainPage = new MainPage(driver);
        String originalWindow = driver.getWindowHandle();
        mainPage.clickYandexLogo();

        // Переключаюмь на новое окно
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        assertTrue(driver.getCurrentUrl().contains("dzen.ru") || driver.getCurrentUrl().contains("yandex.ru"));
        driver.close();
        driver.switchTo().window(originalWindow);
    }
}