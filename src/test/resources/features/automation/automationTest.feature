Feature: Petstore API automation testing

  # Pet Management Scenarios
  @PetManagement
  Scenario: Create a new pet with a name and status
    Given I create a new pet with name "Fluffy" and status "available"
    Then the pet's name in the response should be "Fluffy"

  @PetManagement
  Scenario: Get pet by ID
    Given I get the pet by ID 101
    Then the pet's name in the response should be "Fluffy"

  @PetManagement
  Scenario: Update a pet's name and status
    Given I update the pet with ID 101 to have name "Buddy" and status "sold"
    Then the pet's name in the response should be "Buddy"

  @PetManagement
  Scenario: Delete a pet by ID
    Given I delete the pet with ID 101
    Then the response status code should be 200

  @PetManagement @EdgeCase
  Scenario: Try to get a pet that does not exist
    Given I try to get a pet that does not exist
    Then the response status code should be 404

  @PetManagement @EdgeCase
  Scenario: Try to create a pet without a name
    Given I try to create a pet without required fields
    Then the response status code should be 400

  @PetManagement @EdgeCase
  Scenario: Try to update a pet that does not exist
    Given I try to update a pet that does not exist
    Then the response status code should be 404

  @PetManagement @EdgeCase
  Scenario: Try to delete a pet that does not exist
    Given I try to delete a pet that does not exist
    Then the response status code should be 404

  # Order Management Scenarios
  @OrderManagement
  Scenario: Place an order for a pet
    Given I place an order for pet with ID 101, quantity 2, ship date "2024-10-21", and status "placed"
    Then the response status code should be 200

  @OrderManagement
  Scenario: Get an order by ID
    Given I get the order by ID 101
    Then the order status in the response should be "placed"

  @OrderManagement
  Scenario: Find pets by status
    Given I find pets by status "available"
    Then the response should contain pets with status "available"

  @OrderManagement
  Scenario: Delete an order by ID
    Given I delete the order with ID 101
    Then the response status code should be 200

  @OrderManagement @EdgeCase
  Scenario: Try to place an order without required fields
    Given I try to place an order without required fields
    Then the response status code should be 400

  @OrderManagement @EdgeCase
  Scenario: Try to get an order that does not exist
    Given I try to get an order that does not exist
    Then the response status code should be 404

  @OrderManagement @EdgeCase
  Scenario: Try to delete an order that does not exist
    Given I try to delete an order that does not exist
    Then the response status code should be 404
