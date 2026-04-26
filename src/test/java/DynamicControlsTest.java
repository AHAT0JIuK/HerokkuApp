import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

/*
Автоматизировать тесты для приложения: http://the-internet.herokuapp.com/
Каждая страница - отдельный класс и тест.
Dynamic Controls:
- нажать на кнопку Remove около чекбокса;
- дождаться надписи “It’s gone”;
- проверить, что чекбокса нет;
- найти инпут;
- проверить, что он disabled;
- нажать на кнопку;
- дождаться надписи “It's enabled!”;
- проверить, что инпут enabled.
*/

public class DynamicControlsTest {

    @Test
    public void checkDynamicChangeWebElement() {
        SoftAssert softAssert = new SoftAssert();
        // объявляю настройки для тестового браузера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--headless");
        // объявляю тестовый браузер
        WebDriver driver = new ChromeDriver(options);
        // неявное ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // открытие страницы по указанному урлу
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        // нажимаю на кнопку Remove около чекбокса
        driver.findElement(By.cssSelector("form#checkbox-example button")).click();
        // дожидаюсь надпись “It’s gone”
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "It's gone!");
        // проверяю, что чекбокса нет
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        int numberOfElements = driver.findElements(By.id("checkbox")).size();
        softAssert.assertEquals(numberOfElements, 0);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // нахожу инпут и проверяю, что он disabled
        softAssert.assertFalse(driver.findElement(By.cssSelector("form#input-example input")).isEnabled());
        // нажать на кнопку Enable
        driver.findElement(By.cssSelector("form#input-example button")).click();
        // дожидаюсь надпись “It’s gone”
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        softAssert.assertEquals(driver.findElement(By.id("message")).getText(), "It's enabled!");
        // проверяю, что инпут Enabled
        softAssert.assertTrue(driver.findElement(By.cssSelector("form#input-example input")).isEnabled());
        // закрываю браузер
        driver.quit();
        softAssert.assertAll();
    }
}
