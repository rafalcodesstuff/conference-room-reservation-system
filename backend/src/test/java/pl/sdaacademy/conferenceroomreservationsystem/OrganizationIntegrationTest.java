package pl.sdaacademy.conferenceroomreservationsystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ConferenceroomreservationsystemApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class OrganizationIntegrationTest {

    private final String baseUrl = "http://localhost:";
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int randomServerPort;

    @Test
    public void shouldReturnOrganizations() {
        given()
                .when()
                .auth().basic("admin", "admin")
                .get(baseUrl + randomServerPort + "/organizations")
                .then()
                .statusCode(200)
                .body(equalTo("[{\"id\":1,\"name\":\"Google\",\"organizationLeader\":null},{\"id\":2,\"name\":\"Amazon\",\"organizationLeader\":null},{\"id\":3,\"name\":\"Meta\",\"organizationLeader\":null}]"));
    }
    @Test
    public void shouldReturnOrganizationByName() {
        given()
                .when()
                .auth().basic("user", "password")
                .get(baseUrl + randomServerPort + "/organizations/{name}", "Google")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Google"));
    }
}