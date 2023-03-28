package pl.sdaacademy.conferenceroomreservationsystem.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Repository;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends DistributedRepository<ReservationEntity> {
    List<ReservationEntity> findAllByConferenceRoom(ConferenceRoomEntity conferenceRoom);

    // I wanted to see how much is possible with these queries (most likely overkill)
    void deleteByNameAndStartDateTimeAndEventOrganizerAndConferenceRoom(
            @NotBlank(message = "Reservation name is mandatory") @Size(min = 2, max = 40)
            String name,
            LocalDateTime startDateTime,
            PersonEntity eventOrganizer,
            ConferenceRoomEntity conferenceRoom
    );

    List<ReservationEntity> findAllByEventOrganizer(PersonEntity eventOrganizer);
}
