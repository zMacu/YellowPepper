package steps_definitions.performance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.assertTrue;

public class ApiPerformanceTestSteps {
    private int responseStatus;

    @Given("I run k6 test for creating {int} pets with name {string} and status {string}")
    public void i_run_k6_test_for_creating_pets(int numPets, String name, String status) throws Exception {
        // Create the command for running the k6 script
        ProcessBuilder builder = new ProcessBuilder(
                "k6", "run", "src/test/resources/k6scripts/createPet.js",
                "--vus", String.valueOf(numPets), "--name", name, "--status", status
        );
        builder.redirectErrorStream(true);
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        assertTrue("K6 test failed", exitCode == 0);
    }

    @Given("I run k6 test for updating {int} pets to status {string}")
    public void i_run_k6_test_for_updating_pets_status(int numPets, String status) throws Exception {
        // Run the k6 script for updating pet statuses
        ProcessBuilder builder = new ProcessBuilder(
                "k6", "run", "src/test/resources/k6scripts/updatePets.js",
                "--vus", String.valueOf(numPets), "--status", status
        );
        builder.redirectErrorStream(true);
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        assertTrue("K6 test failed", exitCode == 0);
    }

    @Given("I run k6 test for deleting {int} pets")
    public void i_run_k6_test_for_deleting_pets(int numPets) throws Exception {
        // Run the k6 script for deleting pets
        ProcessBuilder builder = new ProcessBuilder(
                "k6", "run", "src/test/resources/k6scripts/deletePet.js",
                "--vus", String.valueOf(numPets)
        );
        builder.redirectErrorStream(true);
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        assertTrue("K6 test failed", exitCode == 0);
    }

    @Given("I run k6 test for creating {int} pets without a name")
    public void i_run_k6_test_for_creating_pets_without_name(int numPets) throws Exception {
        // Run the k6 script for creating pets without a name
        ProcessBuilder builder = new ProcessBuilder(
                "k6", "run", "src/test/resources/k6scripts/createPet.js",
                "--vus", String.valueOf(numPets)
        );
        builder.redirectErrorStream(true);
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        assertTrue("K6 test failed", exitCode == 0);
    }

    @Given("I run k6 test for updating {int} pets with invalid IDs")
    public void i_run_k6_test_for_updating_invalid_pets(int numPets) throws Exception {
        // Run the k6 script for updating pets with invalid IDs
        ProcessBuilder builder = new ProcessBuilder(
                "k6", "run", "src/test/resources/k6scripts/updatePets.js",
                "--vus", String.valueOf(numPets)
        );
        builder.redirectErrorStream(true);
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        assertTrue("K6 test failed", exitCode == 0);
    }

    @When("I run k6 test for creating {int} pets")
    public void run_k6_test_creating_pets(int numPets) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("k6", "run", "src/test/resources/k6scripts/createPet.js");
        builder.redirectErrorStream(true);
        Process process = builder.start();

        // Wait for the process to complete and check the exit code
        int exitCode = process.waitFor();
        responseStatus = (exitCode == 0) ? 200 : 400;
    }

    @When("I run k6 test for updating {int} pets")
    public void run_k6_test_updating_pets(int numPets) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("k6", "run", "src/test/resources/k6scripts/updatePets.js");
        builder.redirectErrorStream(true);
        Process process = builder.start();

        // Wait for the process to complete and check the exit code
        int exitCode = process.waitFor();
        responseStatus = (exitCode == 0) ? 200 : 400;
    }

    @When("I run k6 test for deleting {int} pets")
    public void run_k6_test_deleting_pets(int numPets) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("k6", "run", "src/test/resources/k6scripts/deletePet.js");
        builder.redirectErrorStream(true);
        Process process = builder.start();

        // Wait for the process to complete and check the exit code
        int exitCode = process.waitFor();
        responseStatus = (exitCode == 0) ? 200 : 404;
    }

    @Then("the response status should be {int}")
    public void verify_response_status(int expectedStatus) {
        assertEquals(expectedStatus, responseStatus);
    }
}
