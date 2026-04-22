import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/*
Автоматизировать тесты для приложения: http://the-internet.herokuapp.com/
Каждая страница - отдельный класс и тест.
Notification Messages - кликнуть на кнопку, дождаться появления нотификации, проверить соответствие текста ожиданиям.
*/

public class NotificationMessagesTest {

    @Test
    public void checkAddRemoveElements() {
        // объявляю настройки для тестового браузера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--headless");
        // объявляю тестовый браузер
        WebDriver driver = new ChromeDriver(options);
        // неявное ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // открытие страницы по указанному урлу
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        // кликаю на ссылку Click here
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/p/a")).click();
        // проверяю нотификацию на текст Action successful
        String textInsideNotification = driver.findElement(By.id("flash")).getText();
        // убираю из полученного текста крестик в конце и разделители в виде переноса на новую строку
        String textInsideNotificationModified = textInsideNotification.replace("×", "").trim();
        Assert.assertEquals(textInsideNotificationModified, "Action successful");
        // закрываю браузер
        driver.quit();
    }
}
