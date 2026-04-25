package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Integer age;
    private String gender;
    private Boolean forceError;

    public Map<String, Object> toQueryParams() {
        Map<String, Object> queryParams = new HashMap<>();

        if (age != null) {
            queryParams.put("age", age);
        }

        if (gender != null) {
            queryParams.put("gender", gender);
        }

        if (forceError != null) {
            queryParams.put("forceError", forceError);
        }

        return queryParams;
    }
}