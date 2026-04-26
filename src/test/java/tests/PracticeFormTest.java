package tests;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test class for the Practice Form page.
 * Tests different form functionalities.
 */
@Epic("Automation Practice Form")
@Feature("Form Input and Validation")
public class PracticeFormTest extends BaseTest {

    // ------------------------- FIRST NAME & LAST NAME TEST -------------------------
    /**
     * Enters first and last name and checks if they appear correctly.
     */
    @Story("First and Last Name Input")
    @Test
    void testFirstAndLastNameInput() {
        formPage.enterFirstName("Quentin");
        Assertions.assertEquals("Quentin", formPage.getFirstNameValue());

        formPage.enterLastName("Tarantino");
        Assertions.assertEquals("Tarantino", formPage.getLastNameValue());
    }

    // ------------------------- EMAIL TEST -------------------------
    /**
     * Enters email and checks if it is displayed correctly.
     */
    @Story("Email Input")
    @Test
    void testEmailInput() {
        formPage.enterEmail("test@example.com");
        Assertions.assertEquals("test@example.com", formPage.getEmailValue());
    }

    // ------------------------- GENDER RADIO BUTTON SELECTION TEST -------------------------
    /**
     * Selects each gender option and checks if selection works.
     */
    @Story("Gender Selection")
    @Test
    void testGenderSelection() {
        formPage.selectGender("Male");
        Assertions.assertTrue(formPage.isGenderSelected("Male"));

        formPage.selectGender("Female");
        Assertions.assertTrue(formPage.isGenderSelected("Female"));

        formPage.selectGender("Other");
        Assertions.assertTrue(formPage.isGenderSelected("Other"));
    }

    // ------------------------- MOBILE PHONE TEST -------------------------
    /**
     * Enters mobile number and checks if it appears correctly.
     */
    @Story("Mobile Input")
    @Test
    void testMobileInput() {
        formPage.enterMobile("1234567890");
        Assertions.assertEquals("1234567890", formPage.getMobileValue());
    }

    // ------------------------- DATE OF BIRTH TEST -------------------------
    /**
     * Selects date of birth and checks if it is displayed correctly.
     */
    @Story("Date of Birth Selection")
    @Test
    void testDateOfBirth() {
        formPage.openDatePicker();
        formPage.selectMonth("May");
        formPage.selectYear("1995");
        formPage.selectDay("19");

        Assertions.assertEquals("19 May 1995", formPage.getSelectedDateOfBirth());
    }

    // ------------------------- SUBJECTS TEST -------------------------
    /**
     * Adds subjects and checks if they are added correctly.
     */
    @Story("Subjects Addition")
    @Test
    void testSubjectsInput() {
        String[] subjects = {
                "Maths", "Accounting", "Arts", "Social Studies", "Biology",
                "Chemistry", "Computer Science", "Commerce", "Economics",
                "Civics", "Hindi", "English", "History", "Physics"
        };

        for (String subject : subjects) {
            formPage.addSubject(subject);
            Assertions.assertTrue(formPage.isSubjectAdded(subject), "Subject was not added: " + subject);
        }
    }

    // ------------------------- HOBBIES CHECKBOX SELECTION TEST -------------------------
    /**
     * Selects hobbies and checks if they are marked correctly.
     */
    @Story("Hobbies Selection")
    @Test
    void testHobbiesCheckboxSelection() {
        formPage.scrollToBottom();

        formPage.selectSportsHobby();
        Assertions.assertTrue(formPage.isSportsSelected());

        formPage.selectReadingHobby();
        Assertions.assertTrue(formPage.isReadingSelected());

        formPage.selectMusicHobby();
        Assertions.assertTrue(formPage.isMusicSelected());
    }

    // ------------------------- UPLOAD PICTURE TEST -------------------------
    /**
     * Uploads a picture and checks if it is added.
     */
    @Story("Picture Upload")
    @Test
    void testUploadPicture() {
        formPage.scrollToBottom();

        String pathToImage = Paths.get("src", "test", "resources", "square.png")
                .toAbsolutePath()
                .toString();

        formPage.uploadPicture(pathToImage);
        Assertions.assertTrue(formPage.getUploadedPictureValue().contains("square.png"));
    }

    // ------------------------- CURRENT ADDRESS TEST -------------------------
    /**
     * Enters current address and checks if it is displayed correctly.
     */
    @Story("Current Address Input")
    @Test
    void testCurrentAddressInput() {
        formPage.scrollToBottom();

        String address = """
                New Beverly Cinema
                7165 Beverly Blvd
                Los Angeles, CA 90036
                USA""";

        formPage.enterCurrentAddress(address);
        Assertions.assertEquals(address, formPage.getCurrentAddressValue());
    }

    // ------------------------- STATE & CITY DROPDOWN SELECTION TEST -------------------------
    /**
     * Selects state and city from dropdowns and checks selections.
     */
    @Story("State and City Selection")
    @Test
    void testStateAndCityDropdownSelection() {
        formPage.scrollToBottom();

        Map<String, List<String>> stateCityMap = new HashMap<>();
        stateCityMap.put("NCR", List.of("Delhi", "Gurgaon", "Noida"));
        stateCityMap.put("Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"));
        stateCityMap.put("Haryana", List.of("Karnal", "Panipat"));
        stateCityMap.put("Rajasthan", List.of("Jaipur", "Jaiselmer"));

        for (Map.Entry<String, List<String>> entry : stateCityMap.entrySet()) {
            String stateName = entry.getKey();
            List<String> cityList = entry.getValue();

            formPage.enterState(stateName);

            for (String cityName : cityList) {
                formPage.enterCity(cityName);
                Assertions.assertEquals(stateName, formPage.getSelectedStateValue());
                Assertions.assertEquals(cityName, formPage.getSelectedCityValue());
            }
        }
    }

    // ------------------------- FULL FORM SUBMIT TEST -------------------------
    /**
     * Fills the whole form and submits it.
     * Checks the confirmation message.
     */
    @Story("Full Form Submission")
    @Test
    void testFullFormSubmit() {
        // First name & last name
        formPage.enterFirstName("John");
        formPage.enterLastName("Smith");

        // Email
        formPage.enterEmail("test@example.com");

        // Gender
        formPage.selectGender("Male");

        // Mobile
        formPage.enterMobile("1234567890");

        // Date of birth
        formPage.openDatePicker();
        formPage.selectMonth("January");
        formPage.selectYear("1963");
        formPage.selectDay("27");

        // Subjects
        formPage.addSubject("Maths");

        // Scroll
        formPage.scrollToBottom();

        // Hobbies
        formPage.selectSportsHobby();
        formPage.selectReadingHobby();

        // Picture
        formPage.uploadPicture(Paths.get("src/test/resources/square.png").toAbsolutePath().toString());

        // Current address
        formPage.enterCurrentAddress("742 Longhorn Avenue\nAustin, TX 78701\nUSA");

        // State & city
        formPage.enterState("NCR");
        formPage.enterCity("Delhi");

        // Submit
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        formPage.submitForm();

        Assertions.assertEquals("Thanks for submitting the form", formPage.getModalTitle(wait));
    }
}

