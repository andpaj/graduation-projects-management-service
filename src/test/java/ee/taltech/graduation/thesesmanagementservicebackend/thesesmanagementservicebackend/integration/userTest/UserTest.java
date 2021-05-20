package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.integration.userTest;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private final String CONTEXT_PATH = "/services/graduation/api";

    @BeforeEach
    void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;
    }

    @Test
    final void testCreateUserSuccess(){

        List<String> groups = new ArrayList<>();
        groups.add("IVSM");

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_STUDENT");

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("firstName", "Andres");
        userDetails.put("lastName", "Pajuste");
        userDetails.put("email", "testAndresTest1@test.com");
        userDetails.put("password", "test");
        userDetails.put("graduationLevel", "bachelor");
        userDetails.put("groups", groups);
        userDetails.put("roles", roles);

        Response response = given().
                contentType("application/json").
                accept("application/json").
                body(userDetails).
                when().
                post(CONTEXT_PATH + "/users").
                then().
                statusCode(200).
                contentType("application/json").
                extract().
                response();

        String userId = response.jsonPath().getString("userId");
        String email = response.jsonPath().getString("email");
        String firstName = response.jsonPath().getString("firstName");

        assertNotNull(userId);
        assertEquals(userId.length(), 30);
        assertEquals(firstName, "Andres");
        assertEquals(email, "testAndresTest1@test.com");


       String bodyString = response.body().asString();

       try {
           JSONObject responseBody = new JSONObject(bodyString);
           JSONArray groupsArray = responseBody.getJSONArray("groupEntities");
           assertNotNull(groupsArray);
           assertTrue(groupsArray.length() == 1);

           String groupName = groupsArray.getJSONObject(0).getString("groupName");
           assertEquals(groupName, "Software Engineering");
       } catch (JSONException e) {
            e.printStackTrace();
       }
    }


    @Test
    final void testCreateUserFailed(){
    //the email already exists

        List<String> groups = new ArrayList<>();
        groups.add("IVSM");

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_STUDENT");

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("firstName", "Andres");
        userDetails.put("lastName", "Pajuste");
        userDetails.put("email", "testAndresTest1@test.com");
        userDetails.put("password", "test");
        userDetails.put("graduationLevel", "Bachelor");
        userDetails.put("groups", groups);
        userDetails.put("roles", roles);

        Response response = given().
                contentType("application/json").
                accept("application/json").
                body(userDetails).
                when().
                post(CONTEXT_PATH + "/users").
                then().
                statusCode(500).
                contentType("application/json").
                extract().
                response();

    }



}
