package Homework_9_4;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

/*
Автоматизировать тесты для приложения: http://the-internet.herokuapp.com/
Каждая страница - отдельный класс и тест.
Inputs - Проверить на возможность ввести различные цифровые и нецифровые значения,
используя Keys.ARROW_UP И Keys.ARROW_DOWN.
*/

public class InputsTest {

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
        driver.get("https://the-internet.herokuapp.com/inputs");
        // попытка ввода текста в поле
        driver.findElement(By.tagName("input")).sendKeys("тест");
        // проверяю, что в инпуте нет введенного текста
        String textInsideInput = driver.findElement(By.tagName("input")).getAttribute("value"); // использую getAttribute т.к. введеное значение хранится в свойстве поля value
        Assert.assertEquals(textInsideInput, "");
        // ввод числа в инпут
        Random random = new Random();
        int randomNumber = random.nextInt(10) + 1;
        driver.findElement(By.tagName("input")).sendKeys(Integer.toString(randomNumber)); // использую Integer.toString потому что webdriver ожидает строку
        // проверяю, что введенное число действительно есть в поле input
        textInsideInput = driver.findElement(By.tagName("input")).getAttribute("value");
        Assert.assertEquals(textInsideInput, String.valueOf(randomNumber)); // с помощью String.valueOf перевожу int в строку для корректного сравнения
        // прожимаю стрелку вверх
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        // проверяю, что введенное число изменилось на + 1
        textInsideInput = driver.findElement(By.tagName("input")).getAttribute("value");
        Assert.assertEquals(textInsideInput, String.valueOf(randomNumber + 1)); // с помощью String.valueOf перевожу int в строку для корректного сравнения
        // прожимаю стрелку вниз
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        // проверяю, что в инпуте снова введенное число
        textInsideInput = driver.findElement(By.tagName("input")).getAttribute("value");
        Assert.assertEquals(textInsideInput, String.valueOf(randomNumber)); // с помощью String.valueOf перевожу int в строку для корректного сравнения
        // закрываю браузер
        driver.quit();
    }
}
