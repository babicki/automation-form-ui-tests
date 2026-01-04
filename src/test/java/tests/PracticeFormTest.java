package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PracticeFormPage;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PracticeFormTest extends BaseTest {

    // ------------------------- FIRST NAME & LAST NAME TEST -------------------------
    @Test
    void testFirstAndLastNameInput() {
        formPage.enterFirstName("Quentin");
        Assertions.assertEquals("Quentin", formPage.getFirstNameValue());

        formPage.enterLastName("Tarantino");
        Assertions.assertEquals("Tarantino", formPage.getLastNameValue());
    }

    // ------------------------- EMAIL TEST -------------------------
    @Test
    void testEmailInput() {
        formPage.enterEmail("test@example.com");
        Assertions.assertEquals("test@example.com", formPage.getEmailValue());
    }

    // ------------------------- GENDER RADIO BUTTON SELECTION TEST -------------------------
    @Test
    void testGenderRadioButtonSelection() {
        List<WebElement> genderRadioLabels = formPage.getGenderRadioLabels();
        List<WebElement> genderRadioInputs = formPage.getGenderRadioInputs();

        for (int i = 0; i < genderRadioLabels.size(); i++) {
            genderRadioLabels.get(i).click();
            Assertions.assertTrue(genderRadioInputs.get(i).isSelected());
        }
    }

    // ------------------------- MOBILE PHONE TEST -------------------------
    @Test
    void testMobileInput() {
        formPage.enterMobile("1234567890");
        Assertions.assertEquals("1234567890", formPage.getMobileValue());
    }

    // ------------------------- DATE OF BIRTH TEST -------------------------
    @Test
    void testDateOfBirth() {
        PracticeFormPage formPage = new PracticeFormPage(driver);
        formPage.open();

        formPage.openDatePicker();
        formPage.selectMonth("May");
        formPage.selectYear("1995");
        formPage.selectDay("19");

        Assertions.assertEquals("19 May 1995", formPage.getSelectedDateOfBirth());
    }

    // ------------------------- SUBJECTS TEST -------------------------
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
    @Test
    void testCurrentAddressInput() {
        formPage.scrollToBottom();

        String address = "New Beverly Cinema\n" +
                "7165 Beverly Blvd\n" +
                "Los Angeles, CA 90036\n" +
                "USA";

        formPage.enterCurrentAddress(address);
        Assertions.assertEquals(address, formPage.getCurrentAddressValue());
    }

    // ------------------------- STATE & CITY DROPDOWN SELECTION TEST -------------------------
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
    @Test
    void testFullFormSubmit() {
        // First name & last name
        formPage.enterFirstName("John");
        formPage.enterLastName("Smith");

        // Email
        formPage.enterEmail("test@example.com");

        // Gender
        formPage.selectFirstGender();

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
        formPage.submitForm(wait);

        Assertions.assertEquals("Thanks for submitting the form", formPage.getModalTitle(wait));
    }
}