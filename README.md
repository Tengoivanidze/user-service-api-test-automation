# User API Test Automation

## Overview
This project contains automated tests for a mocked REST API (`GET /users`) with filtering functionality.

The API is simulated using WireMock, and tests validate both positive and negative scenarios. Test execution results are stored locally in SQLite.

## Tech Stack
- Java 17
- Maven
- TestNG
- RestAssured
- WireMock
- Lombok
- SQLite (JDBC)

## How to Run
## Step 1

```bash
docker run --rm -d \
--name wiremock \
-p 8080:8080 \
wiremock/wiremock:2.35.0
```

### Run Tests
## Step 2

```bash
mvn clean test
```

The tests load WireMock stubs before the suite starts and upsert results into `test-results.db`.

## Test Cases

### Positive Tests
- `testGetAllUsers_Positive` - Get all users without filters
- `testFilterByAge_Positive` - Filter users by age
- `testFilterByGender_Positive` - Filter users by gender

### Negative Tests
- `testInvalidAge_Negative` - Invalid age parameter (400)
- `testInvalidGender_Negative` - Invalid gender parameter (422)
- `testInternalServerError_Negative` - Internal server error (500)

## Project Structure

```
src/
├── main/
│   └── java/
│       ├── data/                 ← TestConfig + UsersDataProvider
│       ├── db/                   ← TestResultRepository
│       ├── listeners/            ← TestResultListener 
│       ├── models/               ← UserRequest + UserResponse
│       ├── steps/                ← UsersSteps
│       ├── stubs/                ← UserStubs
│       └── utils/                ← JsonUtils
└── test/
    ├── java/
    │   └── tests/                ← BaseTest + UsersApiTest
    └── resources/
        └── testng.xml
```
