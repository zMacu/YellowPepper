package steps_definitions.performance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.assertTrue;

public class ApiPerformanceTestSteps {
    private int responseStatus;

    @Given("I create a new pet with name {string} and status {string}")
    public void create_new_pet(String name, String status) {
        // Logic to create a new pet using REST Assured or another method
    }

    @Given("I attempt to update a pet with an invalid ID")
    public void attempt_to_update_invalid_pet() {
        // Logic to handle invalid pet update
    }

    @Given("I create a new pet without a name")
    public void create_pet_without_name() {
        // Logic to create a new pet without a name
    }

    @Given("I update the pet with ID {int} to have status {string}")
    public void update_pet_status(int petId, String status) {
        // Logic to update pet status (this can also be done via k6)
    }

    @Given("I delete the pet with ID {int}")
    public void delete_pet(int petId) {
        // Logic to delete a pet (this can also be done via k6)
    }

    @When("I run k6 test for creating {int} pets")
    public void run_k6_test_creating_pets(int numPets) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("k6", "run", "src/test/resources/k6scripts/create_pets_test.js");
        builder.redirectErrorStream(true);
        Process process = builder.start();

        // Wait for the process to complete and check the exit code
        int exitCode = process.waitFor();
        responseStatus = (exitCode == 0) ? 200 : 400; // Adjust based on your k6 script output
    }

    @When("I run k6 test for updating {int} pets")
    public void run_k6_test_updating_pets(int numPets) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("k6", "run", "src/test/resources/k6scripts/update_pets_test.js");
        builder.redirectErrorStream(true);
        Process process = builder.start();

        // Wait for the process to complete and check the exit code
        int exitCode = process.waitFor();
        responseStatus = (exitCode == 0) ? 200 : 400; // Adjust based on your k6 script output
    }

    @When("I run k6 test for deleting {int} pets")
    public void run_k6_test_deleting_pets(int numPets) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("k6", "run", "src/test/resources/k6scripts/delete_pets_test.js");
        builder.redirectErrorStream(true);
        Process process = builder.start();

        // Wait for the process to complete and check the exit code
        int exitCode = process.waitFor();
        responseStatus = (exitCode == 0) ? 200 : 404; // Adjust based on your k6 script output
    }

    @Then("the response status should be {int}")
    public void verify_response_status(int expectedStatus) {
        assertEquals(expectedStatus, responseStatus);
    }
}
