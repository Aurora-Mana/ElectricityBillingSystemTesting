Feature: New Customer Page

  Scenario: Valid information
    Given Project page is visible
    And The NewCustomer page is visible
    When User submits correct info format
    Then The NewCustomer page closes

  Scenario: Invalid information type
    Given Project page is visible
    And The NewCustomer page is visible
    When User submits incorrect information
    Then The NewCustomer page wrong data type warning displays

  Scenario: Empty data fields
    Given Project page is visible
    And The NewCustomer page is visible
    When One or more of the fields are empty
    Then The NewCustomer page empty data field warning displays
