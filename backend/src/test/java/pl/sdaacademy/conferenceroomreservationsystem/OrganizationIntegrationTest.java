package pl.sdaacademy.conferenceroomreservationsystem;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.sdaacademy.conferenceroomreservationsystem.dto.OrganizationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;
import pl.sdaacademy.conferenceroomreservationsystem.service.OrganizationService;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


//@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ConferenceroomreservationsystemApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
@ActiveProfiles("test")
public class OrganizationIntegrationTest {

    private final String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrganizationRepository repository;

    @LocalServerPort
    private int randomServerPort;

    @BeforeEach
    void setup() {
        OrganizationEntity org1 = new OrganizationEntity("Amazon");
        org1.setId(1);
        org1.setCreated(LocalDateTime.now());
        org1.setModified(LocalDateTime.now());

        OrganizationEntity org2 = new OrganizationEntity("Google");
        org2.setId(2);
        org2.setCreated(LocalDateTime.now());
        org2.setModified(LocalDateTime.now());

        repository.save(org1);
        repository.save(org2);
    }

    @Test
    void shouldReturnOrganizations() {
        given()
                .when()
                .get(baseUrl + randomServerPort + "/api/organizations/list")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("list.size()", is(2));
    }
    @Test
    void shouldReturnOrganizationById() {
        given()
                .when()
                .get(baseUrl + randomServerPort + "/api/organizations/{id}", 1)
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("id", equalTo(1))
                .body("name", equalTo("Amazon"));
    }

    @Test
    void shouldCreateOrganization() {
        OrganizationEntity organizationEntity = new OrganizationEntity("Facebook");
        organizationEntity.setId(3);
        organizationEntity.setCreated(LocalDateTime.now());
        organizationEntity.setModified(LocalDateTime.now());

        repository.save(organizationEntity);

        OrganizationDTO organization = new OrganizationDTO();
        organization.setId(3);
        organization.setName("Facebook");
        organization.setCreated(LocalDateTime.now());
        organization.setModified(LocalDateTime.now());

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(organization)
                .when()
                .post(baseUrl + randomServerPort + "/api/organizations")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("id", equalTo(3))
                .body("name", equalTo("Facebook"));
    }

    @Test
    void shouldUpdateOrganization() {
        OrganizationDTO organization = new OrganizationDTO();
        organization.setName("Google");
        organization.setId(2);
        organization.setCreated(LocalDateTime.now());
        organization.setModified(LocalDateTime.now());

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(organization)
                .when()
                .post(baseUrl + randomServerPort + "/api/organizations")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("id", equalTo(2))
                .body("name", equalTo("Google"));
    }

    @Test
    void shouldDeleteOrganization() {
        given()
                .when()
                .delete(baseUrl + randomServerPort + "/api/organizations/{id}", 1)
                .then()
                .statusCode(200)
                .body(equalTo("true"));
    }

    @Test
    void shouldNotDeleteNonExistentOrganization() {
        given()
                .when()
                .delete(baseUrl + randomServerPort + "/api/organizations/{id}", 123456789)
                .then()
                .statusCode(200)
                .body(equalTo("false"));
    }
}