# Automation Form UI Tests

This project contains automated UI tests for a public demo application – [Automation Practice Form](https://demoqa.com/automation-practice-form).

Tests are written in **Java** using **Maven**, **Selenium WebDriver**, and **JUnit 5**, and follow the **Page Object Model (POM)** design pattern.

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
- Explicit waits used (WebDriverWait) for synchronization
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

The project uses the **Page Object Model (POM)** to separate test logic from page interactions.

## CI/CD Pipeline (GitHub Actions)

This project uses **GitHub Actions** to automatically run UI tests.

The pipeline is triggered on:
- push to repository
- pull requests

**CI workflow includes:**
- Maven build
- Execution of Selenium UI tests
- Generation of Allure results
- Uploading test artifacts to GitHub Actions

The workflow is located in:
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

## Allure Reports

Reports can be accessed in two ways:

**1. CI (recommended)**

Report is automatically generated and published via GitHub Pages: https://babicki.github.io/automation-form-ui-tests

**2. Local execution (optional)**

If needed for debugging:
```bash
mvn test && allure serve target/allure-results
```