Feature: New Customer Page

  Scenario: Valid information
    Given The values are of the proper type
    When User submits information
    Then The NewCustomer page closes

  Scenario: Invalid information type
    Given The values are entered incorrectly
    When User submits information
    Then The NewCustomer page wrong data type warning displays

    Scenario: Empty data fields
      Given One or more of the fields are empty
      When User submits information
      Then The NewCustomer page empty data field warning displays

      Scenario: User creates new customer and views the customer info
        Given User created a new customer
        When User views the customer information
        Then The information is correct