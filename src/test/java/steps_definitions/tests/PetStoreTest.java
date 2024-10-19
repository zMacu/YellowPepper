package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreTest {

    public Response createUser(String username, String firstName, String lastName, String email) {
        String requestBody = String.format("{ \"id\": 100, \"username\": \"%s\", \"firstName\": \"%s\", \"lastName\": \"%s\", \"email\": \"%s\" }",
                username, firstName, lastName, email);

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("http://localhost:8080/api/v3/user");

        return response;
    }

    public Response createPet(String name, String status) {
        String requestBody = String.format("{ \"id\": 101, \"name\": \"%s\", \"status\": \"%s\" }", name, status);
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("http://localhost:8080/api/v3/pet");
    }

    public Response getPetById(int petId) {
        return RestAssured
                .given()
                .get("http://localhost:8080/api/v3/pet/" + petId);
    }

    public Response updatePet(int petId, String name, String status) {
        String requestBody = String.format("{ \"id\": %d, \"name\": \"%s\", \"status\": \"%s\" }", petId, name, status);
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put("http://localhost:8080/api/v3/pet");
    }

    public Response deletePet(int petId) {
        return RestAssured
                .given()
                .delete("http://localhost:8080/api/v3/pet/" + petId);
    }

    public Response placeOrder(int petId, int quantity, String shipDate, String status) {
        String requestBody = String.format(
                "{ \"id\": 101, \"petId\": %d, \"quantity\": %d, \"shipDate\": \"%s\", \"status\": \"%s\" }",
                petId, quantity, shipDate, status);
        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("http://localhost:8080/api/v3/store/order");
    }

    public Response getOrderById(int orderId) {
        return RestAssured
                .given()
                .get("http://localhost:8080/api/v3/store/order/" + orderId);
    }

    public Response findPetsByStatus(String status) {
        return RestAssured
                .given()
                .get("http://localhost:8080/api/v3/pet/findByStatus?status=" + status);
    }

    public Response deleteOrder(int orderId) {
        return RestAssured
                .given()
                .delete("http://localhost:8080/api/v3/store/order/" + orderId);
    }

}