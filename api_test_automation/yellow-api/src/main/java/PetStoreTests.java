import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class PetStoreTest {

    @Test @HappyPath @CreateUser
    public void testCreateUser() {
        String requestBody = "{ \"id\": 100, \"username\": \"testUser\", \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john@example.com\" }";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("http://localhost:8080/api/v3/user");

        response.then().statusCode(200);
        response.then().body("username", equalTo("testUser"));
    }

    @Test @HappyPath @Login
    public void testUserLogin() {
        Response response = RestAssured
                .given()
                .queryParam("username", "testUser")
                .queryParam("password", "password123")
                .get("http://localhost:8080/api/v3/user/login");

        response.then().statusCode(200);
        response.then().body("message", containsString("logged in"));
    }


    @Test @HappyPath
    public void testCreatePet() {
        String requestBody = "{ \"id\": 12345, \"name\": \"Tommy\", \"status\": \"available\" }";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("http://localhost:8080/api/v3/pet");

        response.then().statusCode(200);
        response.then().body("name", equalTo("Tommy"));
    }

    @Test @HappyPath
    public void testGetPetById() {
        Response response = RestAssured
                .given()
                .get("http://localhost:8080/api/v3/pet/12345");

        response.then().statusCode(404);
        response.then().body("message", containsString("Pet not found"));
    }

    @Test @HappyPath
    public void testUpdatePet() {
        String requestBody = "{ \"id\": 12345, \"name\": \"TommyUpdated\", \"status\": \"sold\" }";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put("http://localhost:8080/api/v3/pet");

        response.then().statusCode(200);
        response.then().body("name", equalTo("TommyUpdated"));
        response.then().body("status", equalTo("sold"));
    }

    @Test @HappyPath
    public void testFindPetsByStatus() {
        Response response = RestAssured
                .given()
                .queryParam("status", "available")
                .get("http://localhost:8080/api/v3/pet/findByStatus");

        response.then().statusCode(200);
        response.then().body("status", everyItem(equalTo("available")));
    }

    @Test @HappyPath
    public void testPlaceOrder() {
        String requestBody = "{ \"id\": 1, \"petId\": 12345, \"quantity\": 2, \"status\": \"placed\" }";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("http://localhost:8080/api/v3/store/order");

        response.then().statusCode(200);
        response.then().body("status", equalTo("placed"));
    }

    @Test @HappyPath
    public void testGetOrderById() {
        Response response = RestAssured
                .given()
                .get("http://localhost:8080/api/v3/store/order/1");

        response.then().statusCode(200);
        response.then().body("id", equalTo(1));
        response.then().body("petId", equalTo(12345));
    }

    @Test @HappyPath
    public void testDeleteOrder() {
        Response response = RestAssured
                .given()
                .delete("http://localhost:8080/api/v3/store/order/1");

        response.then().statusCode(200);
        response.then().body("message", equalTo("Order deleted"));
    }

    @Test @HappyPath
    public void testDeletePet() {
        Response response = RestAssured
                .given()
                .delete("http://localhost:8080/api/v3/pet/12345");

        response.then().statusCode(200);
        response.then().body("message", equalTo("Pet deleted"));
    }

    @Test @EdgeCase
    public void testCreatePetWithMissingFields() {
        String requestBody = "{ \"id\": 12346 }"; // Missing name and status fields

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("http://localhost:8080/api/v3/pet");

        response.then().statusCode(400);
        response.then().body("message", containsString("Invalid input"));
    }

    @Test @EdgeCase
    public void testGetNonExistingPet() {
        Response response = RestAssured
                .given()
                .get("http://localhost:8080/api/v3/pet/99999"); // Pet ID doesn't exist

        response.then().statusCode(404);
        response.then().body("message", containsString("Pet not found"));
    }

    @Test @EdgeCase
    public void testUpdateNonExistingPet() {
        String requestBody = "{ \"id\": 99999, \"name\": \"Ghost\", \"status\": \"available\" }"; // Pet ID doesn't exist

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put("http://localhost:8080/api/v3/pet");

        response.then().statusCode(404);
        response.then().body("message", containsString("Pet not found"));
    }

    @Test @EdgeCase
    public void testDeleteNonExistingPet() {
        Response response = RestAssured
                .given()
                .delete("http://localhost:8080/api/v3/pet/99999"); // Pet ID doesn't exist

        response.then().statusCode(404);
        response.then().body("message", containsString("Pet not found"));
    }

    @Test @EdgeCase
    public void testFindPetsByInvalidStatus() {
        Response response = RestAssured
                .given()
                .queryParam("status", "invalid") // Invalid status
                .get("http://localhost:8080/api/v3/pet/findByStatus");

        response.then().statusCode(400);
        response.then().body("message", containsString("Invalid status value"));
    }

    @Test @EdgeCase
    public void testCreateOrderWithInvalidPet() {
        String requestBody = "{ \"id\": 1, \"petId\": 99999, \"quantity\": 1 }"; // Non-existing pet ID

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("http://localhost:8080/api/v3/store/order");

        response.then().statusCode(404);
        response.then().body("message", containsString("Pet not found"));
    }

    @Test @EdgeCase
    public void testGetNonExistingOrder() {
        Response response = RestAssured
                .given()
                .get("http://localhost:8080/api/v3/store/order/99999"); // Order ID doesn't exist

        response.then().statusCode(404);
        response.then().body("message", containsString("Order not found"));
    }

    @Test @EdgeCase
    public void testDeleteNonExistingOrder() {
        Response response = RestAssured
                .given()
                .delete("http://localhost:8080/api/v3/store/order/99999"); // Order ID doesn't exist

        response.then().statusCode(404);
        response.then().body("message", containsString("Order not found"));
    }

    @Test @EdgeCase
    public void testGetOrderWithInvalidIdFormat() {
        Response response = RestAssured
                .given()
                .get("http://localhost:8080/api/v3/store/order/invalidID"); // Invalid ID format

        response.then().statusCode(400);
        response.then().body("message", containsString("Invalid input"));
    }

}