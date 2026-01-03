package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PracticeFormPage {

    private WebDriver driver;

    // First name & last name
    private By firstNameInput = By.id("firstName");
    private By lastNameInput = By.id("lastName");

    // Email
    private By emailInput = By.id("userEmail");

    // Constructor
    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://demoqa.com/automation-practice-form");
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    // Getters
    public String getFirstNameValue() {
        return driver.findElement(firstNameInput).getAttribute("value");
    }

    public String getLastNameValue() {
        return driver.findElement(lastNameInput).getAttribute("value");
    }

    public String getEmailValue() {
        return driver.findElement(emailInput).getAttribute("value");
    }
}
