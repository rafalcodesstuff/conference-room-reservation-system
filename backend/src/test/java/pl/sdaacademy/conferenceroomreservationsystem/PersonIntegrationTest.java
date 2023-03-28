package pl.sdaacademy.conferenceroomreservationsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import pl.sdaacademy.conferenceroomreservationsystem.converter.PersonDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.dto.OrganizationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.dto.PersonDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.PersonRepository;
import pl.sdaacademy.conferenceroomreservationsystem.user.UserRoles;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ConferenceroomreservationsystemApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
@ActiveProfiles("test")
public class PersonIntegrationTest {

    private final String baseUrl = "http://localhost:";

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private PersonDTOConverter converter;

    private OrganizationEntity testOrganization;


    @BeforeEach
    void setup() {
        OrganizationEntity organization = new OrganizationEntity();
        organization.setId(1);
        organization.setName("TestOrganization");
        organization.setCreated(LocalDateTime.now());
        organization.setModified(LocalDateTime.now());

        testOrganization = organizationRepository.save(organization);

        PersonEntity person = new PersonEntity();
        person.setId(1);
        person.setCreated(LocalDateTime.now());
        person.setModified(LocalDateTime.now());
        person.setUsername("Bob");
        person.setEmail("bob@asdf.asdf");
        person.setPassword("password");
        person.setRole(UserRoles.MEMBER);
        person.setOrganization(testOrganization);

        personRepository.save(person);

    }

    @Test
    void shouldReturnPeople() {
        given()
                .when()
                .get(baseUrl + randomServerPort + "/api/users/list")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("list.size()", is(1));
    }
    @Test
    void shouldReturnPersonById() {
        given()
                .when()
                .get(baseUrl + randomServerPort + "/api/users/{id}", 1)
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("id", equalTo(1))
                .body("username", equalTo("Bob"))
                .body("email", equalTo("bob@asdf.asdf"))
                .body("password", equalTo("password"))
                .body("role", equalTo("MEMBER"));
    }

    @Test
    void shouldCreatePerson() {
        PersonEntity person = new PersonEntity();
        person.setId(2);
        person.setCreated(LocalDateTime.now());
        person.setModified(LocalDateTime.now());
        person.setUsername("Jerry");
        person.setEmail("jerry@asdf.asdf");
        person.setPassword("password");
        person.setRole(UserRoles.MEMBER);
        person.setOrganization(testOrganization);

        personRepository.save(person);

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(converter.convert(person))
                .when()
                .post(baseUrl + randomServerPort + "/api/users")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("id", equalTo(2))
                .body("username", equalTo("Jerry"))
                .body("email", equalTo("jerry@asdf.asdf"))
                .body("password", equalTo("password"))
                .body("role", equalTo("MEMBER"));
    }

    @Test
    void shouldUpdatePerson() {
        PersonDTO dto = new PersonDTO();
        dto.setId(1);
        dto.setUsername("Robert");
        dto.setEmail("robert@asdf.asdf");
        dto.setPassword("password1234");

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(dto)
                .when()
                .post(baseUrl + randomServerPort + "/api/users")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("id", equalTo(1))
                .body("username", equalTo("Robert"))
                .body("email", equalTo("robert@asdf.asdf"))
                .body("password", equalTo("password1234"))
                .body("role", equalTo("MEMBER"));
    }

    @Test
    void shouldDeletePerson() {
        given()
                .when()
                .delete(baseUrl + randomServerPort + "/api/users/{id}", 1)
                .then()
                .statusCode(200)
                .body(equalTo("true"));
    }

    @Test
    void shouldNotDeleteNonExistentPerson() {
        given()
                .when()
                .delete(baseUrl + randomServerPort + "/api/users/{id}", 123456789)
                .then()
                .statusCode(200)
                .body(equalTo("false"));
    }
}
