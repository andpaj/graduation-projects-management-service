package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.integration;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCreateUser {

    private final String CONTEXT_PATH = "/services/graduation/api";

    @BeforeEach
    void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;
    }

    @Test
    final void testCreateUser(){

//        List<String> groups = new ArrayList<>();
//        groups.add("IVSM");
//
//
//        Map<String, Object> userDetails = new HashMap<>();
//        userDetails.put("firstName", "Andres");
//        userDetails.put("lastName", "Pajuste");
//        userDetails.put("email", "testAndres@test.com");
//        userDetails.put("password", "test");
//        userDetails.put("graduationLevel", "Bachelor");
//        userDetails.put("groups", groups);
//
//        Response response = given().
//                contentType("application/json").
//                accept("application/json").
//                body(userDetails).
//                when().
//                post(CONTEXT_PATH + "/users").
//                then().
//                statusCode(200).
//                contentType("application/json").
//                extract().
//                response();
//
//
//        String userId = response.jsonPath().getString("userId");
//        assertNotNull(userId);

    }



}
