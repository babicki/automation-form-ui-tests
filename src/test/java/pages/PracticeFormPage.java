package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Page Object class for the Automation Practice Form page.
 * Provides methods to interact with form elements and retrieve values.
 */
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
    /**
     * Constructor for PracticeFormPage.
     * @param driver WebDriver instance to use for interactions.
     */
    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Opens the specified URL in the browser.
     * @param url The URL to navigate to.
     */
    public void open(String url) {
        driver.get(url);
    }

    /**
     * Enters the first name into the form.
     * @param firstName The first name to enter.
     */
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    /**
     * Enters the last name into the form.
     * @param lastName The last name to enter.
     */
    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    /**
     * Enters the email into the form.
     * @param email The email to enter.
     */
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    /**
     * Returns the list of gender radio button labels.
     * @return List of WebElement representing gender labels.
     */
    public List<WebElement> getGenderRadioLabels() {
        return driver.findElements(genderRadioLabels);
    }

    /**
     * Returns the list of gender radio button inputs.
     * @return List of WebElement representing gender inputs.
     */
    public List<WebElement> getGenderRadioInputs() {
        return driver.findElements(genderRadioInputs);
    }

    /**
     * Enters the mobile number into the form.
     * @param mobile The mobile number to enter.
     */
    public void enterMobile(String mobile) {
        driver.findElement(mobileInput).sendKeys(mobile);
    }

    /**
     * Opens the date picker for date of birth.
     */
    public void openDatePicker() {
        driver.findElement(dateOfBirthInput).click();
    }

    /**
     * Selects the month in the date picker.
     * @param month The month to select.
     */
    public void selectMonth(String month) {
        Select selectMonth = new Select(driver.findElement(monthSelect));
        selectMonth.selectByVisibleText(month);
    }

    /**
     * Selects the day in the date picker.
     * @param day The day to select.
     */
    public void selectDay(String day) {
        driver.findElement(day(day)).click();
    }

    /**
     * Selects the year in the date picker.
     * @param year The year to select.
     */
    public void selectYear(String year) {
        Select selectYear = new Select(driver.findElement(yearSelect));
        selectYear.selectByVisibleText(year);
    }

    /**
     * Adds a subject to the subjects input.
     * @param subject The subject to add.
     */
    public void addSubject(String subject) {
        WebElement input = driver.findElement(subjectsInput);

        input.sendKeys(String.valueOf(subject.charAt(0)));
        input.sendKeys(subject.substring(1));
        input.sendKeys(Keys.ENTER);
    }

    /**
     * Checks if a subject is added.
     * @param subject The subject to check.
     * @return True if the subject is added, false otherwise.
     */
    public boolean isSubjectAdded(String subject) {
        return driver.findElement(
                By.xpath("//div[contains(@class,'subjects-auto-complete__multi-value__label') and text()='" + subject + "']")
        ).isDisplayed();
    }

    /**
     * Selects the sports hobby checkbox.
     */
    public void selectSportsHobby() {
        driver.findElement(sportsLabel).click();
    }

    /**
     * Selects the reading hobby checkbox.
     */
    public void selectReadingHobby() {
        driver.findElement(readingLabel).click();
    }

    /**
     * Selects the music hobby checkbox.
     */
    public void selectMusicHobby() {
        driver.findElement(musicLabel).click();
    }

    /**
     * Checks if sports hobby is selected.
     * @return True if selected, false otherwise.
     */
    public boolean isSportsSelected() {
        return driver.findElement(sportsCheckbox).isSelected();
    }

    /**
     * Checks if reading hobby is selected.
     * @return True if selected, false otherwise.
     */
    public boolean isReadingSelected() {
        return driver.findElement(readingCheckbox).isSelected();
    }

    /**
     * Checks if music hobby is selected.
     * @return True if selected, false otherwise.
     */
    public boolean isMusicSelected() {
        return driver.findElement(musicCheckbox).isSelected();
    }

    /**
     * Uploads a picture to the form.
     * @param filePath The path to the file to upload.
     */
    public void uploadPicture(String filePath) {
        driver.findElement(uploadPictureInput).sendKeys(filePath);
    }

    /**
     * Enters the current address into the form.
     * @param address The address to enter.
     */
    public void enterCurrentAddress(String address) {
        driver.findElement(currentAddressInput).sendKeys(address);
    }

    /**
     * Enters the state into the form.
     * @param stateName The state name to enter.
     */
    public void enterState(String stateName) {
        WebElement state = driver.findElement(stateInput);
        state.sendKeys(stateName);
        state.sendKeys(Keys.ENTER);
    }

    /**
     * Enters the city into the form.
     * @param cityName The city name to enter.
     */
    public void enterCity(String cityName) {
        WebElement city = driver.findElement(cityInput);
        city.clear();
        city.sendKeys(cityName);
        city.sendKeys(Keys.ENTER);
    }

    /**
     * Submits the form using the provided wait.
     * @param wait WebDriverWait instance for waiting.
     */
    public void submitForm(WebDriverWait wait) {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    /**
     * Scrolls to the bottom of the page.
     */
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Getters

    /**
     * Gets the value of the first name input.
     * @return The first name value.
     */
    public String getFirstNameValue() {
        return driver.findElement(firstNameInput).getAttribute("value");
    }

    /**
     * Gets the value of the last name input.
     * @return The last name value.
     */
    public String getLastNameValue() {
        return driver.findElement(lastNameInput).getAttribute("value");
    }

    /**
     * Gets the value of the email input.
     * @return The email value.
     */
    public String getEmailValue() {
        return driver.findElement(emailInput).getAttribute("value");
    }

    /**
     * Gets the value of the mobile input.
     * @return The mobile value.
     */
    public String getMobileValue() {
        return driver.findElement(mobileInput).getAttribute("value");
    }

    /**
     * Gets the selected date of birth.
     * @return The selected date.
     */
    public String getSelectedDateOfBirth() {
        return driver.findElement(dateOfBirthInput).getAttribute("value");
    }

    /**
     * Gets the uploaded picture value.
     * @return The picture file name.
     */
    public String getUploadedPictureValue() {
        return driver.findElement(uploadPictureInput).getAttribute("value");
    }

    /**
     * Gets the current address value.
     * @return The address value.
     */
    public String getCurrentAddressValue() {
        return driver.findElement(currentAddressInput).getAttribute("value");
    }

    /**
     * Gets the selected state value.
     * @return The selected state.
     */
    public String getSelectedStateValue() {
        return driver.findElement(selectedStateInput).getText();
    }

    /**
     * Gets the selected city value.
     * @return The selected city.
     */
    public String getSelectedCityValue() {
        return driver.findElement(selectedCityInput).getText();
    }

    /**
     * Selects the first gender option.
     */
    public void selectFirstGender() {
        driver.findElement(genderRadioLabels).click();
    }

    /**
     * Gets the modal title after form submission.
     * @param wait WebDriverWait instance for waiting.
     * @return The modal title text.
     */
    public String getModalTitle(WebDriverWait wait) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(modalTitle)
        ).getText();
    }
}
