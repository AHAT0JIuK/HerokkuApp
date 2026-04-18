package Homework_9_5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

/*
Автоматизировать тесты для приложения: http://the-internet.herokuapp.com/
Каждая страница - отдельный класс и тест.
Typos - Проверить соответствие параграфа орфографии.
*/

public class TyposTest {

    @Test
    public void checkAddRemoveElements() {
        // объявляю настройки для тестового браузера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--headless");
        // объявляю тестовый браузер
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        // неявное ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // открытие страницы по указанному урлу
        driver.get("https://the-internet.herokuapp.com/typos");
        // 10 раз проверяю состояние текста на странице
        for (int i = 0; i < 10; i++) {
            String textCorrect = driver.findElement(By.xpath("(//p)[2]")).getText();
            softAssert.assertEquals(textCorrect, "Sometimes you'll see a typo, other times you won't.");
            driver.navigate().refresh();
        }
        // закрываю браузер
        driver.quit();
        softAssert.assertAll();
    }
}
