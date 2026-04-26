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
File Upload:
- загрузить файл;
- проверить, что имя файла на странице совпадает с именем загруженного файла.
*/

public class FileUploadTest {

    @Test
    public void checkNameUploadFile() {
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
        driver.get("https://the-internet.herokuapp.com/upload");
        // выбор файла для загрузки
        driver.findElement(By.id("file-upload")).sendKeys("D://Downloads/config.ini");
        // нажимаю на кнопку загрузки файла
        driver.findElement(By.id("file-submit")).click();
        // проверяю, что имя файла на странице совпадает с именем загруженного файла
        Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText(), "config.ini");
        // закрываю браузер
        driver.quit();
    }
}
