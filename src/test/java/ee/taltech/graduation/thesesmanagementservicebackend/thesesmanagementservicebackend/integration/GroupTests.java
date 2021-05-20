package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.integration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GroupTests {

    private final String CONTEXT_PATH = "/services/graduation/api";

    @BeforeEach
    void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9000;
    }

    @Test
    public void createGroupTest() {

    }


}
