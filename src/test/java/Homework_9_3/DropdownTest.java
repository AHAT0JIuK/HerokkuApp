package Homework_9_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/*
Автоматизировать тесты для приложения: http://the-internet.herokuapp.com/
Каждая страница - отдельный класс и тест.
Dropdown - Взять все элементы дроп-дауна и проверить их наличие.
Выбрать первый, проверить, что он выбран, выбрать второй, проверить, что он выбран.
*/

public class DropdownTest {

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
        driver.get("https://the-internet.herokuapp.com/dropdown");
        // проверяю наличие всех трех элементов в дропдауне
        driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[1]"));
        driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[2]"));
        driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[3]"));
        // выбираю элемент дропдауна Option 1 и проверяю, что она на самом деле выбран
        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByIndex(1);
        // далее работаю с атрибутом selected у элементов дропдауна. у выбранного элемента он равен true, но это строка поэтому перевожу строку в булево значение и затем сравниваю его с true
        boolean selectOption = Boolean.parseBoolean(driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[2]")).getAttribute("selected"));
        Assert.assertTrue(selectOption);
        // выбираю элемент дропдауна Option 2 и проверяю, что она на самом деле выбран
        select.selectByIndex(2);
        selectOption = Boolean.parseBoolean(driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[3]")).getAttribute("selected"));
        Assert.assertTrue(selectOption);
        // закрываю браузер
        driver.quit();
    }
}
