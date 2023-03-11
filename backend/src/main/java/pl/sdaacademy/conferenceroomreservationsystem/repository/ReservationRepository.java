package pl.sdaacademy.conferenceroomreservationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sdaacademy.conferenceroomreservationsystem.models.ReservationEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
    @Query("SELECT reservation FROM ReservationEntity reservation WHERE reservation.reservationName = :name")
    Optional<ReservationEntity> findReservationByName(String name);

    @Query("SELECT reservation FROM ReservationEntity reservation INNER JOIN PersonEntity person WHERE person.personName = :name")
    List<ReservationEntity> findAllReservationFromUser(String name);

    @Query("SELECT reservation FROM ReservationEntity reservation WHERE reservation.date BETWEEN :firstDate AND :secondDate")
    List<ReservationEntity> findAllReservationsBetweenDates(LocalDate firstDate, LocalDate secondDate);

    @Query("SELECT reservation FROM ReservationEntity reservation INNER JOIN PersonEntity person WHERE person.personName = :username AND reservation.date = :date AND reservation.time BETWEEN :firstTime AND :secondTime")
    List<ReservationEntity> findAllReservationsBetweenTimesInDateFromUser(String username, LocalDate date, LocalTime firstTime, LocalTime secondTime);
}
