package tests;

import data.UsersDataProvider;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import models.UserRequest;
import models.UserResponse;
import org.testng.annotations.Test;
import steps.UsersSteps;

import java.util.List;

import static org.testng.Assert.*;

public class UsersApiTest extends BaseTest {

    private final UsersSteps usersSteps = new UsersSteps();

    @Test(dataProvider = "getAllUsersData", dataProviderClass = UsersDataProvider.class)
    public void testGetAllUsers_Positive(UserRequest request, List<UserResponse> expectedUsers) {
        Response response = usersSteps.getUsers(request);

        assertEquals(response.statusCode(), 200);
        assertEquals(response.as(new TypeRef<List<UserResponse>>() {}), expectedUsers);
    }

    @Test(dataProvider = "filterByAgeData", dataProviderClass = UsersDataProvider.class)
    public void testFilterByAge_Positive(UserRequest request, List<UserResponse> expectedUsers) {
        Response response = usersSteps.getUsers(request);

        assertEquals(response.statusCode(), 200);
        assertEquals(response.as(new TypeRef<List<UserResponse>>() {}), expectedUsers);
    }

    @Test(dataProvider = "filterByGenderData", dataProviderClass = UsersDataProvider.class)
    public void testFilterByGender_Positive(UserRequest request, List<UserResponse> expectedUsers) {
        Response response = usersSteps.getUsers(request);

        assertEquals(response.statusCode(), 200);
        assertEquals(response.as(new TypeRef<List<UserResponse>>() {}), expectedUsers);
    }

    @Test(dataProvider = "invalidAgeData", dataProviderClass = UsersDataProvider.class)
    public void testInvalidAge_Negative(UserRequest request, int expectedStatusCode) {
        Response response = usersSteps.getUsers(request);

        assertEquals(response.statusCode(), expectedStatusCode);
    }

    @Test(dataProvider = "invalidGenderData", dataProviderClass = UsersDataProvider.class)
    public void testInvalidGender_Negative(UserRequest request, int expectedStatusCode) {
        Response response = usersSteps.getUsers(request);

        assertEquals(response.statusCode(), expectedStatusCode);
    }

    @Test(dataProvider = "internalServerErrorData", dataProviderClass = UsersDataProvider.class)
    public void testInternalServerError_Negative(UserRequest request, int expectedStatusCode) {
        Response response = usersSteps.getUsers(request);

        assertEquals(response.statusCode(), expectedStatusCode);
    }
}
