package pl.sdaacademy.conferenceroomreservationsystem.converter;

import org.springframework.stereotype.Component;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ReservationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;

@Component
public class ReservationDTOConverter extends AbstractDTOConverter<ReservationEntity, ReservationDTO> {
    @Override
    public ReservationDTO convert(final ReservationEntity entity) {
        final ReservationDTO reservationDTO = new ReservationDTO();
        super.convert(entity, reservationDTO);

        reservationDTO.setName(entity.getName());
        reservationDTO.setAttendees(entity.getAttendees());
        reservationDTO.setAllDay(entity.getAllDay());
        reservationDTO.setStartDateTime(entity.getStartDateTime());
        reservationDTO.setEndDateTime(entity.getEndDateTime());
        reservationDTO.setEventOrganizer(entity.getEventOrganizer());
        reservationDTO.setAttendees(entity.getAttendees());
        reservationDTO.setConferenceRoom(entity.getConferenceRoom());

        return reservationDTO;
    }
}
