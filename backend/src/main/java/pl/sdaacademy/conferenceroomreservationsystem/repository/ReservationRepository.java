package pl.sdaacademy.conferenceroomreservationsystem.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Repository;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends DistributedRepository<ReservationEntity> {
//    @Query("SELECT reservation FROM ReservationEntity reservation WHERE reservation.reservationName = :name")
//    Optional<ReservationEntity> findReservationByName(String name);
//
//    @Query("SELECT reservation FROM ReservationEntity reservation INNER JOIN PersonEntity person WHERE person.username = :name")
//    List<ReservationEntity> findAllReservationFromUser(String name);
//
//    @Query("SELECT reservation FROM ReservationEntity reservation WHERE reservation.date BETWEEN :firstDate AND :secondDate")
//    List<ReservationEntity> findAllReservationsBetweenDates(LocalDate firstDate, LocalDate secondDate);
//
//    @Query("SELECT reservation FROM ReservationEntity reservation INNER JOIN PersonEntity person WHERE person.username = :username AND reservation.date = :date AND reservation.time BETWEEN :firstTime AND :secondTime")
//    List<ReservationEntity> findAllReservationsBetweenTimesInDateFromUser(String username, LocalDate date, LocalTime firstTime, LocalTime secondTime);

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
