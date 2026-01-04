package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.PracticeFormPage;

import java.time.Duration;

public abstract class BaseTest {

    protected static WebDriver driver;
    protected PracticeFormPage formPage;

    @BeforeAll
    static void setUpDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--incognito",
                "--start-fullscreen"
        );

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeEach
    void openPracticeForm() {
        formPage = new PracticeFormPage(driver);
        formPage.open();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    protected void sleepFor(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}