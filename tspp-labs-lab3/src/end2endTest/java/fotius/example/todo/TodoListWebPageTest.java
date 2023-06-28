package fotius.example.todo;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.util.List;

class TodoListWebPageTest {

    private WebDriver driver;

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }
    @AfterEach
    void cleanup() {
        // TODO: comment quit method call to keep browser open after test
         driver.quit();
    }

    @Test
    void example1() {
        goToHtmlPage();

        final WebElement todoInput = driver.findElement(By.id("todo"));
        final WebElement addButton = driver.findElement(By.id("add"));

        todoInput.sendKeys("item1");
        addButton.click();
        todoInput.sendKeys("item2");
        addButton.click();
        todoInput.sendKeys("item3");
        addButton.click();

        final List<WebElement> removeButtons = driver.findElements(By.xpath("//li[@class=\"list-group-item\"]/div/div[2]/button"));
        removeButtons.get(0).click();
        removeButtons.get(1).click();

        final List<String> todos = driver.findElements(By.className("list-group-item"))
                .stream()
                .map(li -> li.findElement(By.tagName("div")))
                .map(rowDiv -> rowDiv.findElements(By.tagName("div")).get(0))
                .map(WebElement::getText)
                .toList();

        Assertions.assertEquals(List.of("item3"), todos);
    }
    void goToHtmlPage() {
        driver.get(
            "file://" +
            Paths.get(System.getProperty("user.dir"))
                .resolve("src")
                .resolve("main")
                .resolve("resources")
                .resolve("todo.html")
                .toAbsolutePath()
        );
    }
}
