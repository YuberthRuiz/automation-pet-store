@store
Feature: Store functionalities
  To automate functional testing

  @placeAnOrder
  Scenario: Place a new order
    Given valid order details
    When a POST request is sent to store order
    Then the create order response status code should be 200
    And the response should match the order details

  @getOrderById
  Scenario: Get order by ID
    Given an order exists with ID
    When a GET request is sent to store order by ID
    Then the get order response status code should be 200
    And the response should contain the correct order details

 @getNonOrder
  Scenario: Get order by non-existing ID
    Given no order exists with ID 3002
    When a GET request is sent to store order by ID
    Then the get order response status code should be 404

  @deleteOrder
  Scenario: Delete an order
    Given an order is created
    When a DELETE request is sent to store order
    Then the delete order response status code should be 200
    And a subsequent GET request to with the order ID should return a 404 status code

  @checkInventory
  Scenario: Check inventory
    Given multiple pets exist in different statuses
    When a GET request is sent to store inventory
    Then the get inventory response status code should be 200
    And the response should accurately reflect the counts of pets by status