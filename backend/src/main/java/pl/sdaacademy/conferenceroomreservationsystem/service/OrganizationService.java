package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.api.OrganizationApi;
import pl.sdaacademy.conferenceroomreservationsystem.converter.OrganizationDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.dto.OrganizationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;

@Service
public class OrganizationService extends AbstractCRUDLService<OrganizationEntity, OrganizationDTO> implements OrganizationApi {

    @Autowired
    public OrganizationService(final OrganizationRepository repository,
                               final OrganizationDTOConverter converter) {
        super(repository, converter);
    }

    @Override
    protected void updateEntity(OrganizationEntity entity, OrganizationDTO dto) {
        if (dto.isNew()) {
            entity.setName(dto.getName());
        } else {
            if (dto.getName() != null) {
                entity.setName(dto.getName());
            }
        }
    }
}
