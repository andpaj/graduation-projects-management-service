package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.integration;

import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity.GroupEntity;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.repository.GroupRepository;
import ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.shared.dto.GroupDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.SecureRandom;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupTest {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final String CONTEXT_PATH = "/services/graduation/api";
    private final String JSON = "application/json";

    private static String authorizationHeader;
    private static String parentGroupId;
    private static String subGroupId;


    private static String randomEmailValue = "";


    @BeforeEach
    void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;

    }

    public String getRandomStringValue(int length){
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    @Test
    @Order(1)
    public void createLoginAdmin(){


        String generatedString = getRandomStringValue(8);

        randomEmailValue = generatedString + "@test.com";

        List<String> groups = new ArrayList<>();
        groups.add("IAIB");

        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");

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
                statusCode(200).
                contentType("application/json").
                extract().
                response();



        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("email", randomEmailValue);
        loginDetails.put("password", "test");

        Response loginResponse = given()
                .contentType(JSON)
                .accept(JSON)
                .body(loginDetails)
                .when()
                .post(CONTEXT_PATH + "/login")
                .then()
                .statusCode(200).extract().response();


        authorizationHeader = loginResponse.header("Authorization");
    }


    @Test
    @Order(2)
    public void testCreateParentGroup() {


        Map<String, String> groupDetails = new HashMap<>();
        groupDetails.put("groupClass", "School");
        groupDetails.put("groupName", "testSchoolName");

        Response response = given().
                contentType("application/json")
                .header("Authorization", authorizationHeader)
                .accept("application/json").
                body(groupDetails).
                when().
                post(CONTEXT_PATH + "/group/create").
                then().
                statusCode(200).
                contentType("application/json").
                extract().
                response();


        parentGroupId = response.jsonPath().getString("groupId");
        String groupName = response.jsonPath().getString("groupName");

        assertNotNull(parentGroupId);
        assertEquals(groupName, "testSchoolName");

    }

    @Test
    @Order(3)
    public void testCreateSubGroup(){
        Map<String, String> groupDetails = new HashMap<>();
        groupDetails.put("groupClass", "Department");
        groupDetails.put("groupName", "testDepartmentName");
        groupDetails.put("parentTest", parentGroupId);

        Response response = given().
                contentType("application/json")
                .header("Authorization", authorizationHeader)
                .accept("application/json").
                        body(groupDetails).
                        when().
                        post(CONTEXT_PATH + "/group/create").
                        then().
                        statusCode(200).
                        contentType("application/json").
                        extract().
                        response();


        subGroupId = response.jsonPath().getString("groupId");
        String groupName = response.jsonPath().getString("groupName");

        assertNotNull(subGroupId);
        assertEquals(groupName, "testDepartmentName");
    }


    @Test
    @Order(4)
    public void testGetGroup(){

        //get parent group
        Response parentGroupResponse = given()
                .header("Authorization", authorizationHeader)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get(CONTEXT_PATH + "/group/" + parentGroupId)
                .then()
                .statusCode(200).extract().response();

        String parentGroupName = parentGroupResponse.jsonPath().getString("groupName");
        List<Map<String, String>> subGroups = parentGroupResponse.jsonPath().getList("subGroups");

        assertEquals(parentGroupName, "testSchoolName");
        assertTrue(subGroups.size() == 1);

        //get sub group
        Response subGroupResponse = given()
                .header("Authorization", authorizationHeader)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .get(CONTEXT_PATH + "/group/" + subGroupId)
                .then()
                .statusCode(200).extract().response();


        String subGroupName = subGroupResponse.jsonPath().getString("groupName");
        Map<String, String> parentGroup = subGroupResponse.jsonPath().getMap("groupParent");
        String parentName = parentGroup.get("groupName");

        assertTrue(parentGroup != null);
        assertEquals(parentName, "testSchoolName" );
        assertEquals(subGroupName, "testDepartmentName");

    }


}
