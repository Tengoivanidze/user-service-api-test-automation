package steps;

import io.restassured.response.Response;
import models.UserRequest;

import static io.restassured.RestAssured.given;

public class UsersSteps {

    public Response getUsers(UserRequest request) {
        return given()
                .queryParams(request.toQueryParams())
                .when()
                .get("/users");
    }
}
