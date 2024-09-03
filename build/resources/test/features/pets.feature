@pets
Feature: Pets functionalities
  To automate functional testing
  @createpet
  Scenario: Create a new pet
    Given a pet with valid details
    When a POST request is sent to pet endpoint
    Then the response status code should be successful
    And the pet should be retrievable using a GET request with the petId

  @getpetbyid
  Scenario: Get pet by ID
    Given a pet exists with ID
    When a GET request is sent to pet endpoint
    Then the response status code should be 200
    And the response should contain the correct pet details

  Scenario: Get pet by non-existing ID
    Given no pet exists with ID {petId}
    When a GET request is sent to /pet/{petId}
    Then the response status code should be 404

  Scenario: Update an existing pet
    Given a pet exists with ID {petId}
    And new pet details are provided
    When a PUT request is sent to /pet
    Then the response status code should be 200
    And a GET request to /pet/{petId} should return the updated details

  Scenario: Delete a pet
    Given a pet exists with ID {petId}
    When a DELETE request is sent to /pet/{petId}
    Then the response status code should be 200
    And a subsequent GET request to /pet/{petId} should return a 404 status code

  Scenario: Find pets by status
    Given pets exist with different statuses
    When a GET request is sent to /pet/findByStatus?status=available
    Then the response status code should be 200
    And all returned pets should have the status 'available'
