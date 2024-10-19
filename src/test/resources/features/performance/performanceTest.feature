Feature: API Performance Test for Pet Store

  Scenario: Create multiple pets successfully
    Given I create a new pet with name "Fluffy" and status "available"
    When I run k6 test for creating 10 pets
    Then the response status should be 200

  Scenario: Create pet with duplicate name
    Given I create a new pet with name "Fluffy" and status "available"
    When I run k6 test for creating 10 pets with the same name
    Then the response status should be 400

  Scenario: Create pet without required fields
    Given I create a new pet without a name
    When I run k6 test for creating 1 pet
    Then the response status should be 400

  Scenario: Update existing pet's status
    Given I create a new pet with name "Fluffy" and status "available"
    When I update the pet with ID 1 to have status "sold"
    Then the response status should be 200

  Scenario: Update a pet that does not exist
    Given I attempt to update a pet with an invalid ID
    When I run k6 test for updating 1 pet
    Then the response status should be 404

  Scenario: Update pet with invalid data
    Given I create a new pet with name "Fluffy" and status "available"
    When I update the pet with ID 1 to have an invalid status
    Then the response status should be 400

  Scenario: Delete existing pet
    Given I create a new pet with name "Fluffy" and status "available"
    When I delete the pet with ID 1
    Then the response status should be 200

  Scenario: Delete a pet that does not exist
    Given I attempt to delete a pet with an invalid ID
    When I run k6 test for deleting 1 pet
    Then the response status should be 404
