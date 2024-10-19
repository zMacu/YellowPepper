Feature: API Performance Test for Pet Store

  Scenario: Create pets and test performance
    Given the API is available
    When I run k6 test for creating 1000 pets
    Then I generate a report for the performance test

  Scenario: Update many pets concurrently
    Given the API is available
    When I update 1000 pets
    Then the response time should be under 200 ms
