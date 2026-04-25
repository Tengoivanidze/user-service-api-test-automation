package tests;

import data.TestConfig;
import db.TestResultRepository;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import stubs.UserStubs;

public class BaseTest {

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = TestConfig.BASE_URL;
        TestResultRepository.init();
        UserStubs.load();
    }
}