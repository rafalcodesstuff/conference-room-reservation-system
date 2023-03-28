package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.converter.ConferenceRoomDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ConferenceRoomDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.ConferenceRoomEntity;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.exception.RecordAlreadyExistsException;
import pl.sdaacademy.conferenceroomreservationsystem.exception.RecordNotFoundException;
import pl.sdaacademy.conferenceroomreservationsystem.repository.ConferenceRoomRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;
import pl.sdaacademy.conferenceroomreservationsystem.repository.ReservationRepository;

@Service
public class ConferenceRoomService extends AbstractCRUDLService<ConferenceRoomEntity, ConferenceRoomDTO> {

    private final OrganizationRepository organizationRepository;
    private final ReservationRepository reservationRepository;
    private final ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    public ConferenceRoomService(ConferenceRoomRepository repository,
                                 ConferenceRoomDTOConverter converter, OrganizationRepository organizationRepository, ReservationRepository reservationRepository, ConferenceRoomRepository conferenceRoomRepository) {
        super(repository, converter);
        this.organizationRepository = organizationRepository;
        this.reservationRepository = reservationRepository;
        this.conferenceRoomRepository = conferenceRoomRepository;
    }

    @Override
    protected void updateEntity(ConferenceRoomEntity entity, ConferenceRoomDTO dto) {
        if (dto.isNew()) {
            OrganizationEntity organization = organizationRepository.findByName(dto.getOrganization())
                    .orElseThrow(() -> new RecordNotFoundException("Failed to find Organization: " + dto.getOrganization()));

            // checks if conference room exists by the username and organization name
            // needs extra validation, because the dto doesn't have this data
            conferenceRoomRepository.findByNameAndOrganization(dto.getName(), organization).ifPresent(
                    (arg) -> {
                        throw new RecordAlreadyExistsException("Conference room already exists for this organization");
                    }
            );

            entity.setName(dto.getName());
            entity.setAmountOfSpots(dto.getAmountOfSpots());
            entity.setOrganization(organization);

        } else {
            if (dto.getName() != null) {
                entity.setName(dto.getName());
            }
            if (dto.getAmountOfSpots() != null) {
                entity.setAmountOfSpots(dto.getAmountOfSpots());
            }
            if (dto.getOrganization() != null) {
                OrganizationEntity organization = organizationRepository.findByName(dto.getOrganization())
                        .orElseThrow(() -> new RecordNotFoundException("Failed to find Organization: " + dto.getOrganization()));
                entity.setOrganization(organization);
            }
        }
    }
}
