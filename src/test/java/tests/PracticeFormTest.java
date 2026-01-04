package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.PracticeFormPage;

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
}
