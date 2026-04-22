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
Add/Remove Elements - добавить 2 элемента, удалить элемент,
проверить количество элементов DELETE.
*/

public class AddRemoveElementsTest {

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
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        // нажатие два раза кнопки Add Element
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        // проверяю, что на странице добавилось два элемента Delete
        int countElementsStepOne = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(countElementsStepOne, 2);
        // нажимаю один раз кнопку Delete
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        // проверяю, что на странице остался один элемент Delete
        int countElementsStepTwo = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(countElementsStepTwo, 1);
        // закрываю браузер
        driver.quit();
        softAssert.assertAll();
    }
}
