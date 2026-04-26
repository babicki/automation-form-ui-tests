package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.PracticeFormPage;

/**
 * Base class for tests.
 * Sets up WebDriver and common page objects.
 */
public abstract class BaseTest {

    protected static WebDriver driver;
    protected PracticeFormPage formPage;

    private static final String BASE_URL = "https://demoqa.com/automation-practice-form";

    /**
     * Sets up the WebDriver before all tests.
     */
    @BeforeAll
    static void setUpDriver() {
        WebDriverManager.chromedriver().setup();

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments(
                "--incognito",
                "--disable-gpu",
                "--disable-extensions",
                "--disable-popup-blocking",
                "--disable-infobars",
                "--window-size=1920,1080",
                "--remote-allow-origins=*"
        );

        if (headless) {
            chromeOptions.addArguments(
                    "--headless=new",
                    "--no-sandbox",
                    "--disable-dev-shm-usage"
            );
        }

        driver = new ChromeDriver(chromeOptions);
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