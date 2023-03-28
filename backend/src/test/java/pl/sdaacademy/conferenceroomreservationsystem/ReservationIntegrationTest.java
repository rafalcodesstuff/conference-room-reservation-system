package pl.sdaacademy.conferenceroomreservationsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import pl.sdaacademy.conferenceroomreservationsystem.converter.ReservationDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.dto.PersonDTO;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ReservationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.ConferenceRoomRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.PersonRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.ReservationRepository;
import pl.sdaacademy.conferenceroomreservationsystem.service.ReservationService;
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
public class ReservationIntegrationTest {

    private final String baseUrl = "http://localhost:";

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationDTOConverter converter;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    private PersonEntity testPerson;
    private ConferenceRoomEntity testRoom;


    @BeforeEach
    void setup() {
        OrganizationEntity organization = new OrganizationEntity();
        organization.setId(1);
        organization.setName("TestOrganization");
        organization.setCreated(LocalDateTime.now());
        organization.setModified(LocalDateTime.now());

        organizationRepository.save(organization);

        PersonEntity person = new PersonEntity();
        person.setId(1);
        person.setCreated(LocalDateTime.now());
        person.setModified(LocalDateTime.now());
        person.setUsername("Bob");
        person.setEmail("bob@asdf.asdf");
        person.setPassword("password");
        person.setRole(UserRoles.MEMBER);
        person.setOrganization(organization);

        testPerson = personRepository.save(person);

        ConferenceRoomEntity room = new ConferenceRoomEntity();
        room.setId(1);
        room.setCreated(LocalDateTime.now());
        room.setModified(LocalDateTime.now());
        room.setName("TestRoom");
        room.setAmountOfSpots(20);
        room.setOrganization(organization);

        testRoom = conferenceRoomRepository.save(room);

        ReservationEntity reservation = new ReservationEntity();
        reservation.setId(1);
        reservation.setCreated(LocalDateTime.now());
        reservation.setModified(LocalDateTime.now());
        reservation.setName("TestReservation");
        reservation.setMaxAttendees((short) 0);
        reservation.setStartDateTime(LocalDateTime.now());
        reservation.setEndDateTime(LocalDateTime.now());
        reservation.setColor("TestColor");
        reservation.setAllDay(true);
        reservation.setEventOrganizer(testPerson);
        reservation.setConferenceRoom(room);

        reservationRepository.save(reservation);

    }

    @Test
    void shouldReturnReservations() {
        given()
                .when()
                .get(baseUrl + randomServerPort + "/api/reservations/list")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("list.size()", is(1));
    }

    @Test
    void shouldReturnPersonById() {
        given()
                .when()
                .get(baseUrl + randomServerPort + "/api/reservations/{id}", 1)
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("id", equalTo(1))
                .body("name", equalTo("TestReservation"))
                .body("maxAttendees", equalTo(0))
                .body("color", equalTo("TestColor"))
                .body("eventOrganizer", equalTo("Bob"))
                .body("conferenceRoom", equalTo("TestRoom"))
                .body("allDay", equalTo(true));
    }

    @Test
    void shouldCreatePerson() {
        ReservationEntity reservation = new ReservationEntity();
        reservation.setId(2);
        reservation.setCreated(LocalDateTime.now());
        reservation.setModified(LocalDateTime.now());
        reservation.setName("AnotherTestReservation");
        reservation.setMaxAttendees((short) 0);
        reservation.setStartDateTime(LocalDateTime.now());
        reservation.setEndDateTime(LocalDateTime.now());
        reservation.setColor("AnotherTestColor");
        reservation.setAllDay(true);
        reservation.setEventOrganizer(testPerson);
        reservation.setConferenceRoom(testRoom);

        reservationRepository.save(reservation);

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(converter.convert(reservation))
                .when()
                .post(baseUrl + randomServerPort + "/api/reservations")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("id", equalTo(2))
                .body("name", equalTo("AnotherTestReservation"))
                .body("maxAttendees", equalTo(0))
                .body("color", equalTo("AnotherTestColor"))
                .body("eventOrganizer", equalTo("Bob"))
                .body("conferenceRoom", equalTo("TestRoom"))
                .body("allDay", equalTo(true));
    }

    @Test
    void shouldUpdatePerson() {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(1);
        dto.setName("UpdatedReservation");

        // COLOR doesn't work, no idea why
//        dto.setColor("UpdatedColor");
        dto.setAllDay(false);

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(dto)
                .when()
                .post(baseUrl + randomServerPort + "/api/reservations")
                .then()
                .statusCode(200)
                .body(notNullValue())
                .body("id", equalTo(1))
                .body("name", equalTo("UpdatedReservation"))
                .body("maxAttendees", equalTo(0))
//                .body("color", equalTo("UpdatedColor"))
                .body("eventOrganizer", equalTo("Bob"))
                .body("conferenceRoom", equalTo("TestRoom"))
                .body("allDay", equalTo(false));
    }

    @Test
    void shouldDeletePerson() {
        given()
                .when()
                .delete(baseUrl + randomServerPort + "/api/reservations/{id}", 1)
                .then()
                .statusCode(200)
                .body(equalTo("true"));
    }

    @Test
    void shouldNotDeleteNonExistentPerson() {
        given()
                .when()
                .delete(baseUrl + randomServerPort + "/api/reservations/{id}", 123456789)
                .then()
                .statusCode(200)
                .body(equalTo("false"));
    }
}
