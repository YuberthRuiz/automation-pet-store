@users
Feature: Users functionalities
  To automate functional testing

  @createUser
  Scenario: Create a new user
    Given valid user details
    When a POST request is sent to user
    Then the create user response status code should be 200
    And the user should be retrievable using a GET request

  @getUser
  Scenario: Get user by username
    Given a user exists with username
    When a GET request is sent to user endpoint
    Then the user get response status code should be 200
    And the response should contain the correct user details

  @userNonValid
  Scenario: Get user by non-existing username
    Given no user exists with username
    When a GET request is sent to user endpoint
    Then the user get response status code should be 404

  @updateUser
  Scenario: Update an existing user
    Given a user is created
    When a PUT request is sent to user endpoint
    Then the update user response status code should be 200
    And a GET request to user endpoint should return the updated details

  Scenario: Delete a user
    Given a user exists with username
    When a DELETE request is sent to user endpoint
    Then the delete user response status code should be 200
    And a subsequent GET request to user should return a 404 status code

  Scenario: User login
    Given a user exists with username and password
    When a GET request is sent to user login with valid credentials
    Then the login response status code should be 200
    And a session token should be returned

  Scenario: User login with invalid credentials
    Given no user exists with the provided credentials
    When a GET request is sent to user endpoint
    Then the user get response status code should be 400

  Scenario: User logout
    Given a user is logged in
    When a GET request is sent to logout endpoint
    Then the logout response status code should be 200
    And the user session should be invalidated

