package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object for the Automation Practice Form page.
 * Used to interact with the form and get values.
 */
public class PracticeFormPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

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

    /**
     * Constructor for PracticeFormPage.
     */
    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Opens the specified URL in the browser.
     */
    public void open(String url) {
        driver.get(url);

        // Remove dynamic iframes that may interfere with test stability in this demo environment
        // Applied due to lack of control over third-party content and unstable page structure
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('iframe').forEach(el => el.remove());"
        );
    }

    /**
     * Safe click with fallback for intercepted clicks.
     */
    private void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Fills the first name input.
     */
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    /**
     * Fills the last name input.
     */
    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    /**
     * Fills the email input.
     */
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    /**
     * Selects a gender option by visible label text and provides a method to verify whether the specified gender radio button is selected.
     */
    public void selectGender(String gender) {
        driver.findElement(By.xpath("//label[text()='" + gender + "']")).click();
    }

    public boolean isGenderSelected(String gender) {
        return driver.findElement(
                By.xpath("//input[@value='" + gender + "']")
        ).isSelected();
    }

    /**
     * Opens the date picker for date of birth.
     */
    public void enterMobile(String mobile) {
        driver.findElement(mobileInput).sendKeys(mobile);
    }

    /**
     * Selects a month in the date picker.
     */
    public void openDatePicker() {
        WebElement datePicker = wait.until(
                ExpectedConditions.elementToBeClickable(dateOfBirthInput)
        );
        datePicker.click();
    }

    /**
     * Selects a month in the date picker.
     */
    public void selectMonth(String month) {
        WebElement monthDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(monthSelect)
        );
        new Select(monthDropdown).selectByVisibleText(month);
    }

    /**
     * Selects a day in the date picker.
     */
    public void selectDay(String day) {
        safeClick(day(day));
    }

    /**
     * Selects a year in the date picker.
     */
    public void selectYear(String year) {
        WebElement yearDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(yearSelect)
        );
        new Select(yearDropdown).selectByVisibleText(year);
    }

    /**
     * Adds a subject using autocomplete and presses ENTER.
     */
    public void addSubject(String subject) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(subjectsInput)
        );

        input.sendKeys(String.valueOf(subject.charAt(0)));
        input.sendKeys(subject.substring(1));
        input.sendKeys(Keys.ENTER);
    }

    /**
     * Checks if a subject is added.
     */
    public boolean isSubjectAdded(String subject) {
        By subjectLocator = By.xpath(
                "//div[contains(@class,'subjects-auto-complete__multi-value__label') and text()='" + subject + "']"
        );

        List<WebElement> elements = driver.findElements(subjectLocator);

        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    /**
     * Selects the sports hobby checkbox.
     */
    public void selectSportsHobby() {
        safeClick(sportsLabel);
    }

    /**
     * Selects the reading hobby checkbox.
     */
    public void selectReadingHobby() {
        safeClick(readingLabel);
    }

    /**
     * Selects the music hobby checkbox.
     */
    public void selectMusicHobby() {
        safeClick(musicLabel);
    }

    /**
     * Checks if sports hobby is selected.
     */
    public boolean isSportsSelected() {
        return driver.findElement(sportsCheckbox).isSelected();
    }

    /**
     * Checks if reading hobby is selected.
     */
    public boolean isReadingSelected() {
        return driver.findElement(readingCheckbox).isSelected();
    }

    /**
     * Checks if music hobby is selected.
     */
    public boolean isMusicSelected() {
        return driver.findElement(musicCheckbox).isSelected();
    }

    /**
     * Uploads a picture to the form using a file path.
     */
    public void uploadPicture(String filePath) {
        driver.findElement(uploadPictureInput).sendKeys(filePath);
    }

    /**
     * Fills the current address input.
     */
    public void enterCurrentAddress(String address) {
        driver.findElement(currentAddressInput).sendKeys(address);
    }

    /**
     * Fills the state input and presses ENTER (react-select).
     */
    public void enterState(String stateName) {
        WebElement input = wait.until(
                ExpectedConditions.presenceOfElementLocated(stateInput)
        );

        input.sendKeys(stateName);
        input.sendKeys(Keys.ENTER);

        wait.until(driver -> driver.findElements(cityInput).size() > 0);
    }

    /**
     * Fills the city input and presses ENTER (react-select).
     */
    public void enterCity(String cityName) {

        WebElement input = wait.until(
                ExpectedConditions.presenceOfElementLocated(cityInput)
        );

        driver.findElement(By.id("city")).click();

        wait.until(ExpectedConditions.elementToBeClickable(cityInput));

        input.sendKeys(cityName);
        input.sendKeys(Keys.ENTER);
    }

    /**
     * Submits the form safely using wait.
     */
    public void submitForm() {
        safeClick(submitButton);
    }

    /**
     * Scrolls to the bottom of the page.
     */
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

    /**
     * Gets the modal title after form submission.
     */
    public String getModalTitle(WebDriverWait wait) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(modalTitle)
        ).getText();
    }
}