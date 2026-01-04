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

        @Test
        void testFirstAndLastNameInput() {

            PracticeFormPage formPage = new PracticeFormPage(driver);
            formPage.open();

            formPage.enterFirstName("Quentin");
            sleepFor(2000);

            Assertions.assertEquals(
                    "Quentin",
                    formPage.getFirstNameValue()
            );

            formPage.enterLastName("Tarantino");
            sleepFor(2000);

            Assertions.assertEquals(
                    "Tarantino",
                    formPage.getLastNameValue()
            );
        }

        @Test
        void testEmailInput() {

            PracticeFormPage formPage = new PracticeFormPage(driver);
            formPage.open();

            formPage.enterEmail("test@example.com");
            sleepFor(2000);

            Assertions.assertEquals(
                    "test@example.com",
                    formPage.getEmailValue()
            );
        }

    @Test
    void testGenderRadioButtonSelection() {

        PracticeFormPage formPage = new PracticeFormPage(driver);
        formPage.open();

        List<WebElement> genderRadioLabels = formPage.getGenderRadioLabels();
        List<WebElement> genderRadioInputs = formPage.getGenderRadioInputs();

        for (int i = 0; i < genderRadioLabels.size(); i++) {
            genderRadioLabels.get(i).click();
            sleepFor(2000);
            Assertions.assertTrue(genderRadioInputs.get(i).isSelected());
        }
    }

    @Test
    void testMobileInput() {

        PracticeFormPage formPage = new PracticeFormPage(driver);
        formPage.open();

        formPage.enterMobile("1234567890");
        sleepFor(2000);

        Assertions.assertEquals(
                "1234567890",
                formPage.getMobileValue()
        );
    }

    @Test
    void testDateOfBirth() {

        PracticeFormPage formPage = new PracticeFormPage(driver);
        formPage.open();

        formPage.openDatePicker();
        sleepFor(2000);

        formPage.selectMonth("May");
        sleepFor(2000);

        formPage.selectYear("1995");
        sleepFor(2000);

        formPage.selectDay("19");
        sleepFor(2000);

        Assertions.assertEquals(
                "19 May 1995",
                formPage.getSelectedDateOfBirth()
        );
    }

    @Test
    void testSubjectsInput() {

        PracticeFormPage formPage = new PracticeFormPage(driver);
        formPage.open();

        String[] subjects = {
                "Maths",
                "Accounting",
                "Arts",
                "Social Studies",
                "Biology",
                "Chemistry",
                "Computer Science",
                "Commerce",
                "Economics",
                "Civics",
                "Hindi",
                "English",
                "History",
                "Physics"
        };

        for (String subject : subjects) {

            formPage.addSubject(subject);
            sleepFor(500);

            Assertions.assertTrue(
                    formPage.isSubjectAdded(subject),
                    "Subject was not added: " + subject
            );
        }
    }

    @Test
    void testHobbiesCheckboxSelection() {

        PracticeFormPage formPage = new PracticeFormPage(driver);
        formPage.open();

        formPage.scrollToBottom();
        sleepFor(1000);

        formPage.selectSportsHobby();
        sleepFor(1000);
        Assertions.assertTrue(formPage.isSportsSelected());

        formPage.selectReadingHobby();
        sleepFor(1000);
        Assertions.assertTrue(formPage.isReadingSelected());

        formPage.selectMusicHobby();
        sleepFor(1000);
        Assertions.assertTrue(formPage.isMusicSelected());
    }

    @Test
    void testUploadPicture() {

        PracticeFormPage formPage = new PracticeFormPage(driver);
        formPage.open();

        formPage.scrollToBottom();
        sleepFor(1000);

        String pathToImage = Paths.get(
                "src", "test", "resources", "square.png"
        ).toAbsolutePath().toString();

        formPage.uploadPicture(pathToImage);
        sleepFor(2000);

        Assertions.assertTrue(
                formPage.getUploadedPictureValue().contains("square.png")
        );
    }

    @Test
    void testCurrentAddressInput() {

        PracticeFormPage formPage = new PracticeFormPage(driver);
        formPage.open();

        formPage.scrollToBottom();
        sleepFor(1000);

        String address =
                "New Beverly Cinema\n" +
                        "7165 Beverly Blvd\n" +
                        "Los Angeles, CA 90036\n" +
                        "USA";

        formPage.enterCurrentAddress(address);
        sleepFor(2000);

        Assertions.assertEquals(
                address,
                formPage.getCurrentAddressValue()
        );
    }

    @Test
    void testStateAndCityDropdownSelection() {

        PracticeFormPage formPage = new PracticeFormPage(driver);
        formPage.open();

        formPage.scrollToBottom();
        sleepFor(1000);

        Map<String, List<String>> stateCityMap = new HashMap<>();
        stateCityMap.put("NCR", List.of("Delhi", "Gurgaon", "Noida"));
        stateCityMap.put("Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"));
        stateCityMap.put("Haryana", List.of("Karnal", "Panipat"));
        stateCityMap.put("Rajasthan", List.of("Jaipur", "Jaiselmer"));

        for (Map.Entry<String, List<String>> entry : stateCityMap.entrySet()) {

            String stateName = entry.getKey();
            List<String> cityList = entry.getValue();

            formPage.enterState(stateName);
            sleepFor(2000);

            for (String cityName : cityList) {

                formPage.enterCity(cityName);
                sleepFor(2000);

                Assertions.assertEquals(
                        stateName,
                        formPage.getSelectedState()
                );

                Assertions.assertEquals(
                        cityName,
                        formPage.getSelectedCity()
                );
            }
        }
    }

    @Test
    void testFullFormSubmit() {

        PracticeFormPage formPage = new PracticeFormPage(driver);
        formPage.open();
        sleepFor(1000);

        // First name & last name
        formPage.enterFirstName("John");
        sleepFor(1000);

        formPage.enterLastName("Smith");
        sleepFor(1000);

        // Email
        formPage.enterEmail("test@example.com");
        sleepFor(1000);

        // Gender
        formPage.selectFirstGender();
        sleepFor(1000);

        // Mobile
        formPage.enterMobile("1234567890");
        sleepFor(1000);

        // Date of birth
        formPage.openDatePicker();
        sleepFor(1000);

        formPage.selectMonth("January");
        sleepFor(1000);

        formPage.selectYear("1963");
        sleepFor(1000);

        formPage.selectDay("27");
        sleepFor(1000);

        // Subjects
        formPage.addSubject("Maths");
        sleepFor(1000);

        // Scroll
        formPage.scrollToBottom();
        sleepFor(1000);

        // Hobbies
        formPage.selectSportsHobby();
        sleepFor(1000);

        formPage.selectReadingHobby();
        sleepFor(1000);

        // Picture
        formPage.uploadPicture(
                Paths.get("src/test/resources/square.png")
                        .toAbsolutePath()
                        .toString()
        );
        sleepFor(1000);

        // Current address
        formPage.enterCurrentAddress(
                "742 Longhorn Avenue\n" +
                        "Austin, TX 78701\n" +
                        "USA");
        sleepFor(1000);

        // State & city
        formPage.enterState("NCR");
        sleepFor(1000);

        formPage.enterCity("Delhi");
        sleepFor(1000);

        // Submit
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        formPage.submitForm(wait);

        Assertions.assertEquals(
                "Thanks for submitting the form",
                formPage.getModalTitle(wait)
        );

        sleepFor(5000);
    }
}
