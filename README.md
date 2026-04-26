# Automation Form UI Tests

This project contains automated UI tests for a public demo application – [Automation Practice Form](https://demoqa.com/automation-practice-form).

Tests are written in Java using Maven, Selenium WebDriver, and JUnit 5, and follow the Page Object Model (POM) design pattern.

This project focuses on verifying form functionality, including field validation, UI interactions, and full form submission.

## Tech Stack

- **Java** – programming language
- **Maven** – dependency management and build tool
- **JUnit 5** – testing framework
- **Selenium WebDriver** – browser automation tool
- **WebDriverManager** – automatic driver management
- **Google Chrome** – browser used for test execution

## Browser Configuration

Tests are executed in **Google Chrome** with the following configuration:

- Incognito mode enabled
- Full-screen window
- Implicit wait set to 5 seconds
- ChromeDriver is managed automatically using WebDriverManager (no manual setup required)

## Project Structure
```
├── .github
│   └── workflows
│       └── ui-tests.yml
├── src
│   └── test
│       ├── java
│       │   ├── base
│       │   │   └── BaseTest.java
│       │   ├── pages
│       │   │   └── PracticeFormPage.java
│       │   └── tests
│       │       └── PracticeFormTest.java
│       └── resources
│           └── square.png
├── pom.xml
└── README.md
```

Project uses the Page Object Model (POM) to separate test logic from page interactions.

## CI/CD Pipeline (GitHub Actions)

Project uses GitHub Actions to automatically run UI tests.

Pipeline is triggered on:
- push to repository
- pull requests

**CI Workflow includes:**
- Maven build
- Execution of Selenium UI tests
- Generation of Allure results
- Uploading test artifacts to GitHub Actions

You can find the workflow in:
`.github/workflows/ui-tests.yml`

## Test Coverage

The test suite covers the following functional areas of the form:

- Name fields validation (first name, last name)
- Email validation
- Gender selection (radio buttons)
- Mobile number input validation
- Date of birth selection using a date picker
- Subjects handling (adding and verifying multiple values)
- Hobbies selection (checkboxes)
- File upload functionality
- Current address input and validation
- State and city selection with dependency handling
- End-to-end form submission and confirmation message verification

## Running Tests

Tests can be executed either from an **IDE (e.g. IntelliJ)** or using **Maven:**

```bash
mvn test
```

## Running Allure Reports

Allure reports can be generated after executing the tests.

**macOS (Homebrew)**

If Allure is not installed:
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
brew install allure
```

**Windows (PowerShell, winget):**

If Allure is not installed:
```bash
winget install --id=Qameta.Allure -e --source winget
```

Run tests and open report:
```bash
mvn test && allure serve target/allure-results
```