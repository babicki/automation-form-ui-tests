package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PracticeFormPage {

    private WebDriver driver;

    // First name & last name
    private By firstNameInput = By.id("firstName");
    private By lastNameInput = By.id("lastName");

    // Email
    private By emailInput = By.id("userEmail");

    // Gender
    private By genderRadioLabels = By.cssSelector("label[for^='gender-radio']");
    private By genderRadioInputs = By.cssSelector("input[name='gender']");

    // Mobile
    private By mobileInput = By.id("userNumber");

    // Date of birth
    private By dateOfBirthInput = By.id("dateOfBirthInput");
    private By monthSelect = By.className("react-datepicker__month-select");
    private By yearSelect = By.className("react-datepicker__year-select");
    private By day(String day) {
        return By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']");
    }

    // Subjects
    private By subjectsInput = By.id("subjectsInput");

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

    public List<WebElement> getGenderRadioLabels() {
        return driver.findElements(genderRadioLabels);
    }

    public List<WebElement> getGenderRadioInputs() {
        return driver.findElements(genderRadioInputs);
    }

    public void enterMobile(String mobile) {
        driver.findElement(mobileInput).sendKeys(mobile);
    }

    public void openDatePicker() {
        driver.findElement(dateOfBirthInput).click();
    }

    public void selectMonth(String month) {
        Select selectMonth = new Select(driver.findElement(monthSelect));
        selectMonth.selectByVisibleText(month);
    }

    public void selectDay(String day) {
        driver.findElement(day(day)).click();
    }

    public void selectYear(String year) {
        Select selectYear = new Select(driver.findElement(yearSelect));
        selectYear.selectByVisibleText(year);
    }

    public void addSubject(String subject) {
        WebElement input = driver.findElement(subjectsInput);

        input.sendKeys(String.valueOf(subject.charAt(0)));
        input.sendKeys(subject.substring(1));
        input.sendKeys(Keys.ENTER);
    }

    public boolean isSubjectAdded(String subject) {
        return driver.findElement(
                By.xpath("//div[contains(@class,'subjects-auto-complete__multi-value__label') and text()='" + subject + "']")
        ).isDisplayed();
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

    public String getMobileValue() {
        return driver.findElement(mobileInput).getAttribute("value");
    }

    public String getSelectedDateOfBirth() {
        return driver.findElement(dateOfBirthInput).getAttribute("value");
    }
}
