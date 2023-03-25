package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.api.ReservationApi;
import pl.sdaacademy.conferenceroomreservationsystem.converter.ReservationDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ReservationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.ReservationRepository;

@Service
public class ReservationService extends AbstractCRUDLService<ReservationEntity, ReservationDTO> implements ReservationApi {

    @Autowired
    public ReservationService(final ReservationRepository repository,
                              final ReservationDTOConverter converter) {
        super(repository, converter);
    }

    @Override
    protected void updateEntity(ReservationEntity entity, ReservationDTO dto) {
        if (dto.isNew()) {
            entity.setName(dto.getName());
            entity.setMaxAttendees(dto.getMaxAttendees());
            entity.setAllDay(dto.getAllDay());
            entity.setStartDateTime(dto.getStartDateTime());
            entity.setEndDateTime(dto.getEndDateTime());
            entity.setEventOrganizer(dto.getEventOrganizer());
            entity.setAttendees(dto.getAttendees());
            entity.setConferenceRoom(dto.getConferenceRoom());
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
            if (dto.getEventOrganizer() != null) {
                entity.setEventOrganizer(dto.getEventOrganizer());
            }
            if (dto.getAttendees() != null) {
                entity.setAttendees(dto.getAttendees());
            }
            if (dto.getConferenceRoom() != null) {
                entity.setConferenceRoom(dto.getConferenceRoom());
            }
        }
    }

//    private final ReservationRepository reservationRepository;
//    private final OrganizationRepository organizationRepository;
//
//    @Autowired
//    public ReservationService(ReservationRepository reservationRepository, OrganizationRepository organizationRepository) {
//        this.reservationRepository = reservationRepository;
//        this.organizationRepository = organizationRepository;
//    }
//
//    ReservationEntity getReservation(String reservationName) {
//        return reservationRepository.findReservationByName(reservationName)
//                .orElseThrow(() -> new NoSuchElementException("Reservation not found:" + reservationName));
//    }
//
//    List<ReservationEntity> getAllReservationsFromUser(String username) {
//        return reservationRepository.findAllReservationFromUser(username);
//    }
//
//    void addReservation(ReservationEntity reservation) {
//        reservationRepository.findById(reservation.getId()).ifPresent(r -> {
//            throw new RuntimeException("Element Already Exists");
//        });
//    }
//
//    void deleteReservation(ReservationEntity reservation) {
//        ReservationEntity result = reservationRepository.findById(reservation.getId())
//                .orElseThrow(() -> new NoSuchElementException("Reservation not found: " + reservation.getReservationName()));
//        reservationRepository.delete(reservation);
//    }
//
//    void updateReservationName(ReservationEntity reservation, String name) {
//        reservationRepository.findById(reservation.getId()).ifPresentOrElse(
//                result -> {
//                    reservation.setReservationName(name);
//                    reservationRepository.save(reservation);
//                },
//                () -> {
//                    throw new NoSuchElementException("Reservation not found: " + reservation.getReservationName());
//                }
//        );
//    }

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
