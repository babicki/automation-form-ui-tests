package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PracticeFormPage {

    private final WebDriver driver;

    // First name & last name
    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");

    // Email
    private final By emailInput = By.id("userEmail");

    // Gender
    private final By genderRadioLabels = By.cssSelector("label[for^='gender-radio']");
    private final By genderRadioInputs = By.cssSelector("input[name='gender']");

    // Mobile
    private final By mobileInput = By.id("userNumber");

    // Date of birth
    private final By dateOfBirthInput = By.id("dateOfBirthInput");
    private final By monthSelect = By.className("react-datepicker__month-select");
    private final By yearSelect = By.className("react-datepicker__year-select");

    private By day(String day) {
        return By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']");
    }

    // Subjects
    private final By subjectsInput = By.id("subjectsInput");

    // Hobbies
    private final By sportsLabel = By.cssSelector("label[for='hobbies-checkbox-1']");
    private final By readingLabel = By.cssSelector("label[for='hobbies-checkbox-2']");
    private final By musicLabel = By.cssSelector("label[for='hobbies-checkbox-3']");

    private final By sportsCheckbox = By.id("hobbies-checkbox-1");
    private final By readingCheckbox = By.id("hobbies-checkbox-2");
    private final By musicCheckbox = By.id("hobbies-checkbox-3");

    // Picture
    private final By uploadPictureInput = By.id("uploadPicture");

    // Current address
    private final By currentAddressInput = By.id("currentAddress");

    // State & city
    private final By stateInput = By.id("react-select-3-input");
    private final By cityInput = By.id("react-select-4-input");

    private final By selectedStateInput = By.xpath("//div[@id='state']//div[contains(@class,'singleValue')]");
    private final By selectedCityInput = By.xpath("//div[@id='city']//div[contains(@class,'singleValue')]");

    // Submit
    private final By submitButton = By.id("submit");
    private final By modalTitle = By.id("example-modal-sizes-title-lg");

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

    public void selectSportsHobby() {
        driver.findElement(sportsLabel).click();
    }

    public void selectReadingHobby() {
        driver.findElement(readingLabel).click();
    }

    public void selectMusicHobby() {
        driver.findElement(musicLabel).click();
    }

    public boolean isSportsSelected() {
        return driver.findElement(sportsCheckbox).isSelected();
    }

    public boolean isReadingSelected() {
        return driver.findElement(readingCheckbox).isSelected();
    }

    public boolean isMusicSelected() {
        return driver.findElement(musicCheckbox).isSelected();
    }

    public void uploadPicture(String filePath) {
        driver.findElement(uploadPictureInput).sendKeys(filePath);
    }

    public void enterCurrentAddress(String address) {
        driver.findElement(currentAddressInput).sendKeys(address);
    }

    public void enterState(String stateName) {
        WebElement state = driver.findElement(stateInput);
        state.sendKeys(stateName);
        state.sendKeys(Keys.ENTER);
    }

    public void enterCity(String cityName) {
        WebElement city = driver.findElement(cityInput);
        city.clear();
        city.sendKeys(cityName);
        city.sendKeys(Keys.ENTER);
    }

    public void submitForm(WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
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

    public String getUploadedPictureValue() {
        return driver.findElement(uploadPictureInput).getAttribute("value");
    }

    public String getCurrentAddressValue() {
        return driver.findElement(currentAddressInput).getAttribute("value");
    }

    public String getSelectedStateValue() {
        return driver.findElement(selectedStateInput).getText();
    }

    public String getSelectedCityValue() {
        return driver.findElement(selectedCityInput).getText();
    }

    public void selectFirstGender() {
        driver.findElement(genderRadioLabels).click();
    }

    public String getModalTitle(WebDriverWait wait) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(modalTitle)
        ).getText();
    }
}
