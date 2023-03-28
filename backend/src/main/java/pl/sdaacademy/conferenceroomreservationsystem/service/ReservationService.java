package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.api.ReservationApi;
import pl.sdaacademy.conferenceroomreservationsystem.converter.ReservationDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ReservationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.exception.PersonNotFoundException;
import pl.sdaacademy.conferenceroomreservationsystem.exception.RecordNotFoundException;
import pl.sdaacademy.conferenceroomreservationsystem.repository.ConferenceRoomRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.PersonRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.ReservationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService extends AbstractCRUDLService<ReservationEntity, ReservationDTO> implements ReservationApi {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final PersonRepository personRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationDTOConverter reservationDTOConverter;

    @Autowired
    public ReservationService(ReservationRepository repository,
                              ReservationDTOConverter converter,
                              ConferenceRoomRepository conferenceRoomRepository,
                              PersonRepository personRepository, ReservationDTOConverter reservationDTOConverter) {
        super(repository, converter);
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.personRepository = personRepository;
        this.reservationRepository = repository;
        this.reservationDTOConverter = converter;
    }

    @Override
    protected void updateEntity(ReservationEntity entity, ReservationDTO dto) {
        if (dto.isNew()) {
            entity.setName(dto.getName());
            entity.setMaxAttendees(dto.getMaxAttendees());
            entity.setAllDay(dto.getAllDay());
            entity.setStartDateTime(dto.getStartDateTime());
            entity.setEndDateTime(dto.getEndDateTime());
            entity.setColor(dto.getColor());

            PersonEntity person = personRepository.findPersonByUsername(dto.getEventOrganizer())
                    .orElseThrow(() -> new RecordNotFoundException(
                            "Failed to find user with username: " + dto.getEventOrganizer()
                    ));

            entity.setEventOrganizer(person);

            // ran out of time to implement
            // entity.setAttendees(dto.getAttendees());

            ConferenceRoomEntity conferenceRoom = conferenceRoomRepository
                    .findByNameAndOrganization(dto.getConferenceRoom(), person.getOrganization())
                    .orElseThrow(() -> new RecordNotFoundException("Failed to find room from user's organization"));

            entity.setConferenceRoom(conferenceRoom);

        } else {
            if (dto.getName() != null) {
                entity.setName(dto.getName());
            }
            if (dto.getMaxAttendees() != 0) {
                entity.setMaxAttendees(dto.getMaxAttendees());
            }
            if (dto.getAllDay() != null) {
                entity.setAllDay(dto.getAllDay());
            }
            if (dto.getStartDateTime() != null) {
                entity.setStartDateTime(dto.getStartDateTime());
            }
            if (dto.getEndDateTime() != null) {
                entity.setEndDateTime(dto.getEndDateTime());
            }
            if (dto.getColor() != null) {
                entity.setColor(entity.getColor());
            }
            if (dto.getEventOrganizer() != null || dto.getConferenceRoom() != null) {
                PersonEntity person = personRepository.findPersonByUsername(dto.getEventOrganizer())
                        .orElseThrow(() -> new RecordNotFoundException(
                                "Failed to find user with username: " + dto.getEventOrganizer()
                        ));

                if (dto.getEventOrganizer() != null) {
                    entity.setEventOrganizer(person);
                }
                if (dto.getConferenceRoom() != null) {
                    ConferenceRoomEntity conferenceRoom = conferenceRoomRepository
                            .findByNameAndOrganization(dto.getConferenceRoom(), person.getOrganization())
                            .orElseThrow(() -> new RecordNotFoundException("Failed to find room from user's organization"));

                    entity.setConferenceRoom(conferenceRoom);
                }
            }
        }
    }

    public List<ReservationDTO> getUserReservations(String username) {
        PersonEntity person = personRepository.findPersonByUsername(username)
                .orElseThrow(PersonNotFoundException::new);

        return reservationRepository.findAllByEventOrganizer(person).stream()
                .map(reservationDTOConverter::convert)
                .collect(Collectors.toList());

    }
}
