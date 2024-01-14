Feature: Login Page

  Scenario: Valid Login
    Given the login page is open
    When user enters valid credentials and clicks on login
    Then the main project page should be visible

  Scenario: Invalid Login
    Given the login page is open
    When user enters invalid credentials and clicks on login
    Then user should see an error message