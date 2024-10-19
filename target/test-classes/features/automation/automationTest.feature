Feature: Pet and Order Management

  Scenario: Create a new pet
    Given I create a new pet with name "Fluffy" and status "available"
    Then the pet's name in the response should be "Fluffy"

  Scenario: Get pet by ID
    Given I create a new pet with name "Fluffy" and status "available"
    When I get the pet by ID 101
    Then the response status code should be 200
    And the pet's name in the response should be "Fluffy"

  Scenario: Update existing pet
    Given I create a new pet with name "Fluffy" and status "available"
    When I update the pet with ID 101 to have name "Fido" and status "available"
    Then the response status code should be 200

  Scenario: Place an order for a pet
    Given I place an order for pet with ID 1, quantity 2, ship date "2024-10-19T10:00:00Z", and status "placed"
    Then the response status code should be 200

  Scenario: Get order by ID
    Given I place an order for pet with ID 1, quantity 2, ship date "2024-10-19T10:00:00Z", and status "placed"
    When I get the order by ID 101
    Then the response status code should be 200

  Scenario: Delete order by ID
    Given I place an order for pet with ID 1, quantity 2, ship date "2024-10-19T10:00:00Z", and status "placed"
    When I delete the order with ID 101
    Then the response status code should be 200

  Scenario: Delete a pet
    Given I create a new pet with name "Fluffy" and status "available"
    When I delete the pet with ID 101
    Then the response status code should be 200

  Scenario: Get non-existent pet
    Given I try to get a pet that does not exist
    Then the response status code should be 404

  Scenario: Create pet without required fields
    Given I try to create a pet without required fields
    Then the response status code should be 400

  Scenario: Update non-existent pet
    Given I try to update a pet that does not exist
    Then the response status code should be 404

  Scenario: Delete non-existent pet
    Given I try to delete a pet that does not exist
    Then the response status code should be 404

  Scenario: Find pets by status
    Given I have created several pets with different statuses
    When I find pets by status "available"
    Then the response status code should be 200

  Scenario: Place order without required fields
    Given I try to place an order without required fields
    Then the response status code should be 400

  Scenario: Get non-existent order
    Given I try to get an order that does not exist
    Then the response status code should be 404

  Scenario: Delete non-existent order
    Given I try to delete an order that does not exist
    Then the response status code should be 404
