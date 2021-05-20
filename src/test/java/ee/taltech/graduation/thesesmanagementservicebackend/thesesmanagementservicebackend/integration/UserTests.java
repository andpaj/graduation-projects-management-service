package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.integration;


import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;
import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTests {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    private final String CONTEXT_PATH = "/services/graduation/api";
    private final String JSON = "application/json";

    private static String authorizationHeader;
    private static String userId;

    private static String randomEmailValue = "";

    @BeforeEach
    void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;
    }

    public String getRandomValue(int length){
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    @Test
    @Order(1)
    final void testCreateUser(){

        String generatedString = getRandomValue(8);

        randomEmailValue = generatedString + "@test.com";

        List<String> groups = new ArrayList<>();
        groups.add("IVSM");

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_STUDENT");

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("firstName", "Andres");
        userDetails.put("lastName", "Pajuste");
        userDetails.put("email", randomEmailValue);
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
        String firstName = response.jsonPath().getString("firstName");

        assertNotNull(userId);
        assertEquals(userId.length(), 30);
        assertEquals(firstName, "Andres");


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
    @Order(2)
    final void testCreateUserFailed(){
    //the email already exists

        List<String> groups = new ArrayList<>();
        groups.add("IVSM");

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_STUDENT");

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("firstName", "Andres");
        userDetails.put("lastName", "Pajuste");
        userDetails.put("email", randomEmailValue);
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

    @Test
    @Order(3)
    public void testLoginUser(){

        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("email", randomEmailValue);
        loginDetails.put("password", "test");

        Response response = given()
                .contentType(JSON)
                .accept(JSON)
                .body(loginDetails)
                .when()
                .post(CONTEXT_PATH + "/login")
                .then()
                .statusCode(200).extract().response();


        authorizationHeader = response.header("Authorization");
        userId = response.header("UserId");

        assertNotNull(authorizationHeader);
        assertNotNull(userId);
    }

    @Test
    @Order(4)
    public void testGetUserDetails(){

        Response response = given()
                .header("Authorization", authorizationHeader)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get(CONTEXT_PATH + "/users/" + userId)
                .then()
                .statusCode(200).extract().response();

        String userPublicId = response.jsonPath().getString("userId");
        String firstName = response.jsonPath().getString("firstName");
        String email = response.jsonPath().getString("email");
        List<Map<String, String>> groups = response.jsonPath().getList("groupEntities");
        String groupClass = groups.get(0).get("groupClass");

        assertNotNull(userPublicId);
        assertNotNull(firstName);

        assertEquals(firstName, "Andres");
        assertEquals(randomEmailValue, email);
        assertTrue(groups.size() == 1);
        assertTrue(groupClass.equals("Department"));
    }

    @Test
    @Order(5)
    public void testUpdateUser(){

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("firstName", "testUpdateFirstName");
        userDetails.put("lastName", "testUpdateLastName");

        Response response = given()
                .contentType(JSON)
                .accept(JSON)
                .header("Authorization", authorizationHeader)
                .pathParam("userId", userId)
                .body(userDetails)
                .when()
                .put(CONTEXT_PATH + "/users/{userId}")
                .then()
                .statusCode(200).extract().response();

        String firstName = response.jsonPath().getString("firstName");
        String lastName = response.jsonPath().getString("lastName");

        assertEquals(firstName, "testUpdateFirstName");
        assertEquals(lastName, "testUpdateLastName");

    }

//    @Test
//    @Order(6)
//    public void testDeleteUser(){
//        Response response = given()
//                .accept(JSON)
//                .header("Authorization", authorizationHeader)
//                .pathParam("userId", userId)
//                .when()
//                .delete(CONTEXT_PATH + "/users/{userId}")
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();
//
//    }



}
