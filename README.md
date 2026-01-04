# Automation Practice Form Tests

This project contains **automated tests** for the [Automation Practice Form](https://demoqa.com/automation-practice-form) website.

The tests are written in **Java** using **Maven**, **Selenium WebDriver**, and **JUnit 5**, following the **Page Object Model (POM)** design pattern.  
The goal of the project is to verify the correctness of all form fields – from simple inputs to full form submission.

## Technologies & Tools

- **Java**
- **Maven** – dependency management and test execution
- **JUnit 5** – testing framework
- **Selenium WebDriver** – browser automation
- **WebDriverManager** – automatic Chrome driver management
- **Google Chrome** – browser used for testing

## Browser Configuration

Tests are executed in **Google Chrome** with the following settings:

- Incognito mode
- Full-screen window
- Implicit wait: 5 seconds
- Chrome driver is managed automatically by WebDriverManager – no manual download required

## Project Structure
```
src
└── test
├── java
│ ├── base
│ │ └── BaseTest.java
│ ├── pages
│ │ └── PracticeFormPage.java
│ └── tests
│ └── PracticeFormTest.java
└── resources
└── square.png
```
## Test Coverage

The project contains tests for the following functionalities:

1. **First Name & Last Name**
    - Entering data
    - Verifying the entered value

2. **Email**
    - Validating the input email

3. **Gender (radio buttons)**
    - Selecting each option

4. **Mobile Phone**
    - Verifying the entered number

5. **Date of Birth**
    - Selecting day, month, and year using the date picker

6. **Subjects**
    - Adding all available subjects
    - Verifying each subject is added

7. **Hobbies (checkboxes)**
    - Selecting all available options

8. **Upload Picture**
    - Uploading a file from `src/test/resources`

9. **Current Address**
    - Entering multi-line text
    - Verifying the input value

10. **State & City**
    - Selecting a state
    - Selecting a city assigned to the selected state

11. **Full Form Submission**
    - Filling in all required fields
    - Clicking **Submit**
    - Verifying the confirmation message

## Running Tests

Tests can be executed:
- **From IDE** (e.g., IntelliJ) – run as JUnit tests
- **From Maven**:
```bash
mvn test
