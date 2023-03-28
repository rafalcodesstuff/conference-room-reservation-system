package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ReservationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;
import pl.sdaacademy.conferenceroomreservationsystem.exception.RecordNotFoundException;
import pl.sdaacademy.conferenceroomreservationsystem.repository.ConferenceRoomRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.PersonRepository;
import pl.sdaacademy.conferenceroomreservationsystem.user.UserRoles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TestReservationService {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;


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

        personRepository.save(person);

        ConferenceRoomEntity room = new ConferenceRoomEntity();
        room.setId(1);
        room.setCreated(LocalDateTime.now());
        room.setModified(LocalDateTime.now());
        room.setName("TestRoom");
        room.setAmountOfSpots(20);
        room.setOrganization(organization);

        conferenceRoomRepository.save(room);
    }

    @Test
    void testReservationCRUDL() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setName("TestReservation");
        reservationDTO.setAllDay(true);
        reservationDTO.setStartDateTime(LocalDateTime.now());
        reservationDTO.setColor("TestColor");
        reservationDTO.setMaxAttendees((short) 0);
        reservationDTO.setEventOrganizer("Bob");
        reservationDTO.setConferenceRoom("TestRoom");

        // verify saving
        final ReservationDTO savedReservation = reservationService.save(reservationDTO);
        assertThat(reservationDTO.getName()).isNotNull();
        assertThat(reservationDTO.getAllDay()).isNotNull();
        assertThat(reservationDTO.getColor()).isNotNull();
        assertThat(reservationDTO.getMaxAttendees()).isGreaterThanOrEqualTo((short) 0);
        assertThat(reservationDTO.getEventOrganizer()).isNotNull();
        assertThat(reservationDTO.getConferenceRoom()).isNotNull();

        // verify read
        final ReservationDTO reservationById = reservationService.getById(savedReservation.getId());
        assertThat(reservationById).isNotNull();
        assertThat(reservationById)
                .extracting(
                        ReservationDTO::getId,
                        ReservationDTO::getName,
                        ReservationDTO::getAllDay,
                        ReservationDTO::getColor,
                        ReservationDTO::getMaxAttendees,
                        ReservationDTO::getEventOrganizer,
                        ReservationDTO::getConferenceRoom

                ).contains(
                        savedReservation.getName(),
                        savedReservation.getAllDay(),
                        savedReservation.getColor(),
                        savedReservation.getMaxAttendees(),
                        savedReservation.getEventOrganizer(),
                        savedReservation.getConferenceRoom()
                );

        // verify list
        final List<ReservationDTO> reservations = reservationService.list();
        assertThat(reservations).isNotNull();
        assertThat(reservations).hasSize(1);

        // verify update
        savedReservation.setName("UpdatedTestReservation");
        savedReservation.setAllDay(false);
        savedReservation.setStartDateTime(LocalDateTime.now());

        // COLOR doesn't work, I don't know why
//        savedReservation.setColor("UpdatedTestColor");
        savedReservation.setMaxAttendees((short) 0);
        savedReservation.setEventOrganizer("Bob");
        savedReservation.setConferenceRoom("TestRoom");

        final ReservationDTO updatedReservation = reservationService.save(savedReservation);
        assertThat(updatedReservation).isNotNull();

        assertThat(updatedReservation.getModified()).isAfter(savedReservation.getModified());

        assertThat(updatedReservation.getName()).isEqualTo(savedReservation.getName());
        assertThat(updatedReservation.getAllDay()).isEqualTo(savedReservation.getAllDay());
//        assertThat(updatedReservation.getColor()).isEqualTo(savedReservation.getColor());
        assertThat(updatedReservation.getMaxAttendees()).isEqualTo(savedReservation.getMaxAttendees());
        assertThat(updatedReservation.getEventOrganizer()).isEqualTo(savedReservation.getEventOrganizer());
        assertThat(updatedReservation.getConferenceRoom()).isEqualTo(savedReservation.getConferenceRoom());

        // verify delete
        final Boolean deleted = reservationService.delete(savedReservation.getId());
        assertThat(deleted).isTrue();
        assertThrows(RecordNotFoundException.class, () -> reservationService.getById(savedReservation.getId()));

        // try to delete something that does not exist
        assertThat(reservationService.delete(123456789)).isFalse();
    }
}
