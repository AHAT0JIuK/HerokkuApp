import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/*
Автоматизировать тесты для приложения: http://the-internet.herokuapp.com/
Каждая страница - отдельный класс и тест.
Frames:
- Открыть iFrame;
- Проверить, что текст внутри параграфа равен “Your content goes here.”.
*/

public class FramesTest {

    @Test
    public void checkTextInFrame() {
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
        driver.get("https://the-internet.herokuapp.com/frames");
        // переход на тестовую страницу iFrame
        driver.findElement(By.cssSelector("a[href='/iframe']")).click();
        // переключаюсь на frame с нужным текстом
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("mce_0_ifr")));
        // проверяю, что текст внутри параграфа равен “Your content goes here.”
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tinymce")));
        WebElement p = driver.findElement(By.cssSelector("#tinymce > p"));
        Assert.assertEquals(p.getText(), "Your content goes here.");
        // закрываю браузер
        driver.quit();
    }
}
