package stubs;

import models.UserResponse;
import utils.JsonUtils;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public final class UserStubs {

    private UserStubs() {}

    public static void load() {
        configureFor("localhost", 8080);
        removeAllMappings();

        stubUsers(MapCase.ALL, List.of(
                UserResponse.builder().id(1).name("Alice").age(30).gender("female").build(),
                UserResponse.builder().id(2).name("Bob").age(25).gender("male").build()
        ));

        stubUsers(MapCase.AGE_30, List.of(
                UserResponse.builder().id(1).name("Alice").age(30).gender("female").build()
        ));

        stubUsers(MapCase.GENDER_MALE, List.of(
                UserResponse.builder().id(2).name("Bob").age(25).gender("male").build()
        ));

        stubFor(get(urlPathEqualTo("/users"))
                .withQueryParam("age", equalTo("-1"))
                .willReturn(badRequest().withBody("{\"error\":\"Invalid age\"}")));

        stubFor(get(urlPathEqualTo("/users"))
                .withQueryParam("gender", equalTo("unknown"))
                .willReturn(jsonResponse("{\"error\":\"Invalid gender\"}", 422)));

        stubFor(get(urlPathEqualTo("/users"))
                .withQueryParam("forceError", equalTo("true"))
                .willReturn(serverError().withBody("{\"error\":\"Internal Server Error\"}")));
    }

    private static void stubUsers(MapCase mapCase, List<UserResponse> users) {
        var mapping = get(urlPathEqualTo("/users"));

        if (mapCase == MapCase.AGE_30) {
            mapping.withQueryParam("age", equalTo("30"));
        }

        if (mapCase == MapCase.GENDER_MALE) {
            mapping.withQueryParam("gender", equalTo("male"));
        }

        stubFor(mapping.willReturn(okJson(toJson(users))));
    }

    private static String toJson(Object body) {
        return JsonUtils.toJson(body);
    }

    private enum MapCase {
        ALL, AGE_30, GENDER_MALE
    }
}
