package pl.sdaacademy.conferenceroomreservationsystem.converter;

import org.springframework.stereotype.Component;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ConferenceRoomDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;

@Component
public class ConferenceRoomDTOConverter extends AbstractDTOConverter<ConferenceRoomEntity, ConferenceRoomDTO> {

    @Override
    public ConferenceRoomDTO convert(final ConferenceRoomEntity entity) {
        ConferenceRoomDTO conferenceRoomDTO = new ConferenceRoomDTO();
        super.convert(entity, conferenceRoomDTO);

        conferenceRoomDTO.setName(entity.getName());
        conferenceRoomDTO.setAmountOfSpots(entity.getAmountOfSpots());
        conferenceRoomDTO.setOrganization(entity.getOrganization());
        conferenceRoomDTO.setReservations(entity.getReservations());

        return conferenceRoomDTO;
    }
}
