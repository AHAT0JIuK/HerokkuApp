package Homework_9_1;

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
        // неявное ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // открытие страницы по указанному урлу
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        // нажатие два раза кнопки Add Element
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        // проверяю, что на странице добавилось два элемента Delete
        int countElementsStepOne = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        Assert.assertEquals(countElementsStepOne, 2);
        // нажимаю один раз кнопку Delete
        driver.findElement(By.xpath("//button[text()='Delete']")).click();
        // проверяю, что на странице остался один элемент Delete
        int countElementsStepTwo = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        Assert.assertEquals(countElementsStepTwo, 1);
        // закрываю браузер
        driver.quit();
    }
}
