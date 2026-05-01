import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

/*
Автоматизировать тесты для приложения: http://the-internet.herokuapp.com/
Каждая страница - отдельный класс и тест.
File Download (с зорачкай):
- Изучить https://www.swtestacademy.com/download-file-in-selenium/;
- Скачать файл;
- Проверить наличие файла на файловой системе.
*/

public class FileDownloadTest {

    @Test
    public void checkExistDownloadFile() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1); // разрешение на автоматическое скачивание
        chromePrefs.put("download.default_directory", "D:\\Downloads"); // указываю путь для загрузки файлов
        chromePrefs.put("download.prompt_for_download", false); // отключение диалогового окна с выбором папки для загрузки
        // объявляю настройки для тестового браузера
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs); // применяю настройки их хэш-мапы
        options.addArguments("--start-maximized");
        // объявляю тестовый браузер
        WebDriver driver = new ChromeDriver(options);
        // неявное ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // открытие страницы по указанному урлу
        driver.get("https://the-internet.herokuapp.com/download");
        // нажимаю на ссылку для скачки файла
        driver.findElement(By.cssSelector("a[href='download/sample-zip-file.zip']")).click();
        // перед поиском файла жду, что он на самом деле скачан
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> new File("D:\\Downloads\\sample-zip-file.zip").exists());
        // поиск файла в файловой системе согласно инструкции
        File folder = new File("D:\\Downloads");
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File f = null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                System.out.println("File " + listOfFile.getName());
                if (fileName.matches("sample-zip-file.zip")) {
                    f = new File("D:\\Downloads\\sample-zip-file.zip");
                    found = true;
                }
            }
        }
        // удаление скачанного файла
        f.deleteOnExit();
        // закрываю браузер
        driver.quit();
    }
}
