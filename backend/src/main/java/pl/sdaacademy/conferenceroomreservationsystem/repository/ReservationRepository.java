package pl.sdaacademy.conferenceroomreservationsystem.repository;

import org.springframework.stereotype.Repository;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;

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
}
