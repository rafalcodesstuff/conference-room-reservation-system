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


    // get all organization, left here in case admin profile will be created
//    public List<OrganizationEntity> getOrganizations(@NonNull String name) {
//        return organizationRepository.findByName(name)
//                .map(Collections::singletonList)
//                .orElse(Collections.emptyList());
//    }

    // nazwy są UNIQUE więc można szukać by name
//    public List<OrganizationEntity> getOrganizations() {
//        return organizationRepository.findAll();
//    }
//
//    public OrganizationEntity getOrganizationById(@NonNull Integer id) {
//        return organizationRepository.findById(id)
//                .orElseThrow(() -> new RecordNotFoundException("Organization Not Found:  " + id));
//    }
//
//    public OrganizationEntity getOrganizationByName(@NonNull String name) {
//        return organizationRepository.findByName(name)
//                .orElseThrow(() -> new RecordNotFoundException("Organization Not Found:  " + name));
//    }
//
//    public OrganizationEntity addOrganization(OrganizationEntity organization) {
//        OrganizationDTO organizationDTO = new OrganizationDTO();
//        Optional<OrganizationEntity> byName = organizationRepository.findByName(organization.getName());
//        byName.ifPresent(org -> {
//            throw new RecordAlreadyExistsException("Organization already exists: " + organization.getName());
//        });
//        return organizationRepository.save(organization);
//    }
//
//    public void removeOrganization(@NonNull String name) {
//        OrganizationEntity organization = organizationRepository.findByName(name)
//                .orElseThrow(() -> new RecordNotFoundException("Organization Not Found: " + name));
//        organizationRepository.delete(organization);
//    }

//    public OrganizationEntity updateOrganization(@NonNull String old_org, @NonNull String new_org) {
//        OrganizationEntity organization = organizationRepository.findByName(old_org)
//                .orElseThrow(() -> new RecordNotFoundException("Organization Not Found"));
//        organization.setName(new_org);
//        organizationRepository.save(organization);
//        return organization;
//    }
}
