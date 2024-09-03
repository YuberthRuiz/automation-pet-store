@general
Feature: API functionalities
  To automate functional testing

  Scenario: Input field validation with missing required fields
    Given a request is prepared with missing required fields
    When the request is sent to the appropriate endpoint
    Then the response status code should be 400

  Scenario: Input field validation with invalid data types
    Given a request is prepared with incorrect data types
    When the request is sent to the appropriate endpoint
    Then the response status code should be 400

  Scenario: Validate response structure
    Given a request is sent to an endpoint
    When the response is received
    Then the response should adhere to the defined JSON schema
    And required fields should be present with correct data types

  Scenario: Status code validation for successful requests
    Given a valid request is prepared
    When the request is sent to the appropriate endpoint
    Then the response status code should be 200 or 201

  Scenario: Status code validation for erroneous requests
    Given an invalid request is prepared
    When the request is sent to the appropriate endpoint
    Then the response status code should be 400, 404, or 500

  Scenario: Data consistency after sequence of operations
    Given a pet is created, updated, and then deleted
    When the appropriate requests are sent in sequence
    Then the final GET request to retrieve the pet should return a 404 status code
