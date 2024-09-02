@users @smoke @newuser
Feature: Users functionalities
  To make smoke testing
  As a contact list app user
  I want to create new users

  Scenario: Should be able to create new users when the user logs in
    Given Sam is logged into the application
    When he requested the add user service
    Then he should see user created successfully

  Scenario: Should be able to delete a user
    Given Sam is logged into the application
    When Sam requested the delete user service
    Then he should see the user was deleted successfully

  Scenario: Should be able to get the profile information
    Given Sam is logged into the application
    And Sam requested the profile service
    Then he should see the profile response successfully

  Scenario: Should be able to logout from the application
    Given Sam is logged into the application
    When Sam requested the logout service
    Then he should exit the app

  Scenario: Should be able to update a user
    Given Sam is logged into the application
    When Sam requested the update user service
    Then he should see the updated user successfully response