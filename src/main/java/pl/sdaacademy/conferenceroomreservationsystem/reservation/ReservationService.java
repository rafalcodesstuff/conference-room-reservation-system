package pl.sdaacademy.conferenceroomreservationsystem.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.organization.OrganizationRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, OrganizationRepository organizationRepository) {
        this.reservationRepository = reservationRepository;
        this.organizationRepository = organizationRepository;
    }

    Reservation getReservation(String reservationName) {
        return reservationRepository.findReservationByName(reservationName)
                .orElseThrow(() -> new NoSuchElementException("Reservation not found:" + reservationName));
    }

    List<Reservation> getAllReservationsFromUser(String username) {
        return reservationRepository.findAllReservationFromUser(username);
    }

    void addReservation(Reservation reservation) {
        reservationRepository.findById(reservation.getId()).ifPresent(r -> {
            throw new RuntimeException("Element Already Exists");
        });
    }

    void deleteReservation(Reservation reservation) {
        Reservation result = reservationRepository.findById(reservation.getId())
                .orElseThrow(() -> new NoSuchElementException("Reservation not found: " + reservation.getReservationName()));
        reservationRepository.delete(reservation);
    }

    void updateReservationName(Reservation reservation, String name) {
        reservationRepository.findById(reservation.getId()).ifPresentOrElse(
                result -> {
                    reservation.setReservationName(name);
                    reservationRepository.save(reservation);
                },
                () -> {
                    throw new NoSuchElementException("Reservation not found: " + reservation.getReservationName());
                }
        );
    }

    // NIEBOSKIE STWORZENIE - LEPIEJ NIE DOTYKAĆ

//    private void updateReservation(Reservation reservation, List args, String methodName) throws NoSuchMethodException {
//        Class<?>[] types = reservation.getClass().getDeclaredMethod(methodName).getParameterTypes();
//        Method reservationSetterMethod = reservation.getClass().getDeclaredMethod(methodName, types);
//
//        reservationRepository.findById(reservation.getId()).ifPresentOrElse(
//                result -> {
//                    try {
//                        reservationSetterMethod.invoke(reservation, args);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        throw new RuntimeException(e);
//                    }
//                    reservationRepository.save(reservation);
//                },
//                () -> { throw new NoSuchElementException("Reservation not found: " + reservation.getReservationName()); }
//        );
//    }
//
//    void updateReservationMaxAttendees(Reservation reservation, Short attendees) {
//        updateReservation(reservation, new ArrayList(attendees), "setMaxAttendees");
//    }
//
//    void updateReservationOrganization(Reservation reservation, Organization organization) {
//        organizationRepository.findById(organization.getId()).ifPresent(
//                o -> { updateReservation(reservation, organization, "setOrganization"); }
//        );
//    }
}
