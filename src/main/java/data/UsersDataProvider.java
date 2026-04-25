package data;

import models.UserRequest;
import models.UserResponse;
import org.testng.annotations.DataProvider;

import java.util.List;

public class UsersDataProvider {

    @DataProvider(name = "getAllUsersData")
    public Object[][] getAllUsersData() {
        return new Object[][]{
                {UserRequest.builder().build(), List.of(
                        UserResponse.builder().id(1).name("Alice").age(30).gender("female").build(),
                        UserResponse.builder().id(2).name("Bob").age(25).gender("male").build()
                )}
        };
    }

    @DataProvider(name = "filterByAgeData")
    public Object[][] filterByAgeData() {
        return new Object[][]{
                {UserRequest.builder().age(30).build(), List.of(
                        UserResponse.builder().id(1).name("Alice").age(30).gender("female").build()
                )}
        };
    }

    @DataProvider(name = "filterByGenderData")
    public Object[][] filterByGenderData() {
        return new Object[][]{
                {UserRequest.builder().gender("male").build(), List.of(
                        UserResponse.builder().id(2).name("Bob").age(25).gender("male").build()
                )}
        };
    }

    @DataProvider(name = "invalidAgeData")
    public Object[][] invalidAgeData() {
        return new Object[][]{
                {UserRequest.builder().age(-1).build(), 400}
        };
    }

    @DataProvider(name = "invalidGenderData")
    public Object[][] invalidGenderData() {
        return new Object[][]{
                {UserRequest.builder().gender("unknown").build(), 422}
        };
    }

    @DataProvider(name = "internalServerErrorData")
    public Object[][] internalServerErrorData() {
        return new Object[][]{
                {UserRequest.builder().forceError(true).build(), 500}
        };
    }
}
