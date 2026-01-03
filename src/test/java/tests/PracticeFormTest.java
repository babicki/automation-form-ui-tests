package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

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
}
