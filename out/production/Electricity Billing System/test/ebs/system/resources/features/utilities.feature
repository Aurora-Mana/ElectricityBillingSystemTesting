Feature: Use Utilities in the Electric Billing System

  Scenario: Open Notepad
    Given the EBS app is open
    When the user clicks on Utility menu
    And the user clicks on Notepad
    Then Notepad should be executed

  Scenario: Open Calculator
    Given the EBS app is open
    When the user clicks on Utility menu
    And the user clicks on Calculator
    Then Calculator should be executed

  Scenario: Open Web Browser
    Given the EBS app is open
    When the user clicks on Utility menu
    And the user clicks on Web Browser
    Then Web Browser should be executed