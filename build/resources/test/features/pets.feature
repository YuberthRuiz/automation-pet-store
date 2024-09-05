@pets
Feature: Pets functionalities
  To automate functional testing

  @createpet
  Scenario: Create a new pet
    Given a pet with valid details
    When a POST request is sent to pet endpoint
    Then the response status code should be successful
    And the pet should be retrievable using a GET request with the petId

  @createPetWrong
  Scenario Outline: Create a new pet without requiered fields
    Given a pet with valid details
    When a POST request is sent to pet endpoint without "<missing_attribute>"
    Then the create status code should be <expected_response>
    Examples:
      | missing_attribute | expected_response |
      | name              | 400               |
      | photoUrls         | 400               |

  @getPetById
  Scenario: Get pet by ID
    Given a pet exists with ID
    When a GET request is sent to pet endpoint
    Then the response status code should be 200
    And the response should contain the correct pet details

  @wrongId
  Scenario: Get pet by non-existing ID
    Given no pet exists with ID 1111
    When a GET request is sent to pet endpoint
    Then the response status code should be 404

  @updatePet
  Scenario: Update an existing pet
    Given a pet exists with ID
    When a PUT request is sent to pet endpoint
    Then the response status should be 200
    And a GET request to pet endpoint should return the updated details

  @deletePet
  Scenario Outline: Delete a pet
    Given a pet exists with ID
    When a DELETE request is sent to pet ID <id_pet> endpoint
    Then the delete response status code should be <expected_status>
    And a subsequent GET request to pet with ID <id_pet> should return a 404 status code
    Examples:
      | id_pet | expected_status |
      | 10     | 200             |
      | 112    | 400             |

  @findByStatus
  Scenario Outline: Find pets by status
    Given pets exist with different statuses
    When a GET request is sent to pet findByStatus with "<status>"
    Then the search status code should be 200
    And all returned pets should have the status "<status>"
    Examples:
      | status    |
      | available |
      | pending   |
      | sold      |


  @findByTag
  Scenario Outline: Find pets by tag
    Given pets exist with different tags
    When a GET request is sent to pet findByTags with "<tag>"
    Then the tags search status code should be 200
    And all returned pets should have the tags "<tag>"
    Examples:
      | tag  |
      | tag1 |
      | tag2 |
      | tag3 |

  @uploadPetPhoto
  Scenario: Successfully upload a photo of a pet
    Given a pet exists
    When a POST request is sent to pet uploadImage with the photo file
    Then the upload photo response status code should be 200
    And the response should indicate that the photo was uploaded successfully