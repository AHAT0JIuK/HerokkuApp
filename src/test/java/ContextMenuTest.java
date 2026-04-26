import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/*
Автоматизировать тесты для приложения: http://the-internet.herokuapp.com/
Каждая страница - отдельный класс и тест.
Context Menu:
- правый клик по элементу;
- валидация текста на алерте;
- закрытие алерта.
*/

public class ContextMenuTest {

    @Test
    public void checkContextAction() {
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
        driver.get("https://the-internet.herokuapp.com/context_menu");
        // правый клик внутри пунктирного квадрата
        Actions action = new Actions(driver);
        action.contextClick(driver.findElement(By.id("hot-spot"))).perform();
        // валидация текста на алерте
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "You selected a context menu");
        // закрытие алерта
        alert.accept();
        // закрываю браузер
        driver.quit();
    }
}
