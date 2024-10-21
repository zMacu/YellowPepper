package steps_definitions.automation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import tests.PetStoreTest;

import static org.hamcrest.Matchers.equalTo;

public class ApiTestSteps {

    private PetStoreTest petStoreTest;
    private Response response;
    private int petId; // Store pet ID for reference
    private int orderId; // Store order ID for reference

    public ApiTestSteps() {
        this.petStoreTest = new PetStoreTest();
    }

    // Pet Management
    @Given("I create a new pet with name {string} and status {string}")
    public void create_a_new_pet(String name, String status) {
        response = petStoreTest.createPet(name, status);
        petId = 101; // Set to the ID used in createPet
    }

    @Given("I get the pet by ID {int}")
    public void i_get_the_pet_by_id(int petId) {
        response = petStoreTest.getPetById(petId);
    }

    @Given("I update the pet with ID {int} to have name {string} and status {string}")
    public void i_update_the_pet(int petId, String name, String status) {
        response = petStoreTest.updatePet(petId, name, status);
    }

    @Given("I delete the pet with ID {int}")
    public void i_delete_the_pet(int petId) {
        response = petStoreTest.deletePet(petId);
    }

    @Given("I try to get a pet that does not exist")
    public void i_try_to_get_a_pet_that_does_not_exist() {
        response = petStoreTest.getPetById(999); // Assuming 999 is an ID that doesn't exist
    }

    @Given("I try to create a pet without required fields")
    public void i_try_to_create_a_pet_without_required_fields() {
        response = petStoreTest.createPet("", "available"); // Example of missing name
    }

    @Given("I try to update a pet that does not exist")
    public void i_try_to_update_a_pet_that_does_not_exist() {
        response = petStoreTest.updatePet(999, "NewName", "available"); // Non-existent ID
    }

    @Given("I try to delete a pet that does not exist")
    public void i_try_to_delete_a_pet_that_does_not_exist() {
        response = petStoreTest.deletePet(999); // Non-existent ID
    }

    // Order Management
    @Given("I place an order for pet with ID {int}, quantity {int}, ship date {string}, and status {string}")
    public void i_place_an_order_for_pet(int petId, int quantity, String shipDate, String status) {
        response = petStoreTest.placeOrder(petId, quantity, shipDate, status);
        orderId = 101; // Set to the ID you used in placeOrder
    }

    @Given("I get the order by ID {int}")
    public void i_get_the_order_by_id(int orderId) {
        response = petStoreTest.getOrderById(orderId);
    }

    @Given("I find pets by status {string}")
    public void i_find_pets_by_status(String status) {
        response = petStoreTest.findPetsByStatus(status);
    }

    @Given("I delete the order with ID {int}")
    public void i_delete_the_order(int orderId) {
        response = petStoreTest.deleteOrder(orderId);
    }

    @Given("I try to place an order without required fields")
    public void i_try_to_place_an_order_without_required_fields() {
        response = petStoreTest.placeOrder(0, 0, "", ""); // Example of missing fields
    }

    @Given("I try to get an order that does not exist")
    public void i_try_to_get_an_order_that_does_not_exist() {
        response = petStoreTest.getOrderById(999); // Assuming 999 is an ID that doesn't exist
    }

    @Given("I try to delete an order that does not exist")
    public void i_try_to_delete_an_order_that_does_not_exist() {
        response = petStoreTest.deleteOrder(999); // Non-existent ID
    }

    @Then("the pet's name in the response should be {string}")
    public void the_pet_name_in_response_should_be(String expectedName) {
        response.then().body("name", equalTo(expectedName));
    }
}
