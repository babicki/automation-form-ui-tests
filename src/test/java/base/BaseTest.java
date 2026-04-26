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

/**
 * Base class for tests.
 * Sets up WebDriver and common page objects.
 */
public abstract class BaseTest {

    protected static WebDriver driver;
    protected PracticeFormPage formPage;

    private static final String BASE_URL = "https://demoqa.com/automation-practice-form";
    private static final Duration IMPLICIT_WAIT = Duration.ofSeconds(5);

    /**
     * Sets up the WebDriver before all tests.
     * Uses ChromeDriver with optional headless mode.
     */
    @BeforeAll
    static void setUpDriver() {
        WebDriverManager.chromedriver().setup();

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments(
                "--incognito",
                "--start-maximized"
        );

        if (headless) {
            chromeOptions.addArguments(
                    "--headless=new",
                    "--no-sandbox",
                    "--disable-dev-shm-usage"
            );
        }

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
    }

    /**
     * Opens the practice form before each test.
     */
    @BeforeEach
    void openPracticeForm() {
        formPage = new PracticeFormPage(driver);
        formPage.open(BASE_URL);
    }

    /**
     * Closes the WebDriver after all tests.
     */
    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}