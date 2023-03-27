package pl.sdaacademy.conferenceroomreservationsystem.converter;

import org.springframework.stereotype.Component;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ReservationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ReservationEntity;

@Component
public class ReservationDTOConverter extends AbstractDTOConverter<ReservationEntity, ReservationDTO> {
    @Override
    public ReservationDTO convert(final ReservationEntity entity) {
        ReservationDTO reservationDTO = new ReservationDTO();
        super.convert(entity, reservationDTO);

        reservationDTO.setName(entity.getName());
        reservationDTO.setAttendees(entity.getAttendees());
        reservationDTO.setAllDay(entity.getAllDay());
        reservationDTO.setStartDateTime(entity.getStartDateTime());
        reservationDTO.setEndDateTime(entity.getEndDateTime());
        reservationDTO.setColor(entity.getColor());
        reservationDTO.setEventOrganizer(entity.getEventOrganizer().getUsername());
        reservationDTO.setAttendees(entity.getAttendees());
        reservationDTO.setConferenceRoom(entity.getConferenceRoom().getName());

        return reservationDTO;
    }
}
