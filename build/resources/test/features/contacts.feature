@contacts @smoke @newcontact
Feature: Contacts functionalities
  To make smoke testing
  As a contact list app user
  I want to create, update and delete new contacts

  Scenario: Should be able to create new contacts when the user logs in
    Given Sam is logged into the application
    When Sam requested the add contact service
    Then he should see the contact created successfully

  Scenario: Should be able to delete the contact
    Given Sam is logged into the application
    And Sam requested the add contact service
    And he should see the contact created successfully
    When Sam try to delete the contact
    Then he should see the contact was deleted

  Scenario: Should be able to get the recently added contact
    Given Sam is logged into the application
    And Sam requested the add contact service
    #And he should see the newly created contact
    When Sam requested the contact service
    Then he should see the contact requested was successfully

  Scenario: Should be able to get contacts list
    Given Sam is logged into the application
    When Sam requested the list of contacts
    Then he should see a successfully request response

  Scenario: Should be able to update just one attribute of the contact
    Given Sam is logged into the application
    And Sam requested the add contact service
    And he should see the contact created successfully
    When Sam try to update just one attribute of the contact
    Then he should see a successfully response

  Scenario: Should be able to update the contact information
    Given Sam is logged into the application
    And Sam requested the add contact service
    And he should see the contact created successfully
    When Sam try to update the contact
    Then he should see a successfully response
