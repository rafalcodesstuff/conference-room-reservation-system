package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.converter.ConferenceRoomDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ConferenceRoomDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.ConferenceRoomRepository;

@Service
public class ConferenceRoomService extends AbstractCRUDLService<ConferenceRoomEntity, ConferenceRoomDTO> {
    public ConferenceRoomService(final ConferenceRoomRepository repository,
                                 final ConferenceRoomDTOConverter converter) {
        super(repository, converter);
    }

    @Override
    protected void updateEntity(ConferenceRoomEntity entity, ConferenceRoomDTO dto) {
        if (dto.isNew()) {
            entity.setName(dto.getName());
            entity.setAmountOfSpots(dto.getAmountOfSpots());
            entity.setOrganization(dto.getOrganization());
            entity.setReservations(dto.getReservations());
        } else {
            if (dto.getName() != null) {
                entity.setName(dto.getName());
            }
            if (dto.getAmountOfSpots() != null) {
                entity.setAmountOfSpots(dto.getAmountOfSpots());
            }
            if (dto.getOrganization() != null) {
                entity.setOrganization(dto.getOrganization());
            }
            if (dto.getReservations() != null) {
                entity.setReservations(dto.getReservations());
            }
        }
    }
}
