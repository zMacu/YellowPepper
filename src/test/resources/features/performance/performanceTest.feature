Feature: Performance testing for Petstore API

  @Performance
  Scenario: Create multiple pets with a given name and status
    Given I run k6 test for creating 1000 pets with name "Fluffy" and status "available"

  @Performance
  Scenario: Update multiple pets' status to "sold"
    Given I run k6 test for updating 1000 pets to status "sold"

  @Performance
  Scenario: Delete multiple pets by ID
    Given I run k6 test for deleting 500 pets

  @Performance @EdgeCase
  Scenario: Create multiple pets without a name (Edge case)
    Given I run k6 test for creating 500 pets without a name

  @Performance @EdgeCase
  Scenario: Update pets with invalid IDs (Edge case)
    Given I run k6 test for updating 500 pets with invalid IDs
