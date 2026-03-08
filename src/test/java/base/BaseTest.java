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
 * Base class for test setup and teardown.
 * Provides WebDriver initialization and common test page objects.
 */
public abstract class BaseTest {

    protected static WebDriver driver;
    protected PracticeFormPage formPage;

    private static final String BASE_URL = "https://demoqa.com/automation-practice-form";
    private static final Duration IMPLICIT_WAIT = Duration.ofSeconds(5);

    /**
     * Sets up the WebDriver before all tests.
     * Configures ChromeDriver with incognito and fullscreen options.
     */
    @BeforeAll
    static void setUpDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--incognito",
                "--start-fullscreen"
        );

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
    }

    /**
     * Initializes the practice form page before each test.
     * Opens the form URL.
     */
    @BeforeEach
    void openPracticeForm() {
        formPage = new PracticeFormPage(driver);
        formPage.open(BASE_URL);
    }

    /**
     * Tears down the WebDriver after all tests.
     * Quits the driver if initialized.
     */
    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}