package pl.sdaacademy.conferenceroomreservationsystem.converter;

import org.springframework.stereotype.Component;
import pl.sdaacademy.conferenceroomreservationsystem.dto.OrganizationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;

@Component
public class OrganizationDTOConverter extends AbstractDTOConverter<OrganizationEntity, OrganizationDTO> {

    @Override
    public OrganizationDTO convert(final OrganizationEntity entity) {
        final OrganizationDTO organizationDTO = new OrganizationDTO();
        super.convert(entity, organizationDTO);

        organizationDTO.setName(entity.getName());
        organizationDTO.setOrganizationMembers(entity.getOrganizationMembers());
        organizationDTO.setConferenceRooms(entity.getConferenceRooms());

        return organizationDTO;
    }
}
