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
Checkboxes - проверить, что первый чекбокс unchecked, отметить первый чекбокс, проверить что он checked.
Проверить, что второй чекбокс checked, сделать uncheck, проверить, что он unchecked.
*/

public class CheckboxesTest {

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
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        // проверяю, что первый чекбокс unchecked
        boolean firstCheckboxOff = driver.findElements(By.cssSelector("[type=checkbox]")).get(0).isSelected();
        softAssert.assertFalse(firstCheckboxOff);
        // отмечаю первый чекбокс
        driver.findElements(By.cssSelector("[type=checkbox]")).get(0).click();
        // проверяю, что первый чекбокс checked
        boolean firstCheckboxOn = driver.findElements(By.cssSelector("[type=checkbox]")).get(0).isSelected();
        softAssert.assertTrue(firstCheckboxOn);
        // проверяю, что второй чекбокс checked
        boolean secondCheckboxOn = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        softAssert.assertTrue(secondCheckboxOn);
        // снимаю отметку со второго чекбокса
        driver.findElements(By.cssSelector("[type=checkbox]")).get(1).click();
        // проверяю, что второй чекбокс unchecked
        boolean secondCheckboxOff = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        softAssert.assertFalse(secondCheckboxOff);
        // закрываю браузер
        driver.quit();
        softAssert.assertAll();
    }
}
