Feature: User Menu
  Scenario: User opens the Calculate Bill
    Given Project is visible
    When User opens Calculate Bill
    Then Calculate Bill should be open

  Scenario: User opens Last Bill
    Given Project is visible
    When User opens Last Bill
    Then Last Bill should be open

  Scenario: User calculates bill with valid data
    Given Project is visible
    When User opens Calculate Bill
    And User enters valid data for bill calculation
    Then Bill should be calculated successfully

  Scenario: User calculates bill with invalid data
    Given Project is visible
    When User opens Calculate Bill
    And User enters invalid data for bill calculation
    Then Invalid input message should be displayed

  Scenario: User checks the Last Bill with no records
    Given Project is visible
    When User opens Last Bill
    And User enters invalid data for Last Bill
    Then No bill found message should be displayed


  Scenario: User checks the Last Bill with records
    Given Project is visible
    When User opens Last Bill
    And User enters valid data for Last Bill
    Then Bill info should be displayed
