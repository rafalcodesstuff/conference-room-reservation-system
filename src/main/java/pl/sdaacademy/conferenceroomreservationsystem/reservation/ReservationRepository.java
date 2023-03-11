package pl.sdaacademy.conferenceroomreservationsystem.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT reservation FROM Reservation reservation WHERE reservation.reservationName = :name")
    Optional<Reservation> findReservationByName(String name);

    @Query("SELECT reservation FROM Reservation reservation INNER JOIN Person person WHERE person.personName = :name")
    List<Reservation> findAllReservationFromUser(String name);
}
