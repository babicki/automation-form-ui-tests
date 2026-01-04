package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.PracticeFormPage;

import java.nio.file.Paths;
import java.util.List;

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
}
