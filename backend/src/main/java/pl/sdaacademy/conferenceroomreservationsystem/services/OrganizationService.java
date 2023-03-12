package pl.sdaacademy.conferenceroomreservationsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.exceptions.RecordAlreadyExistsException;
import pl.sdaacademy.conferenceroomreservationsystem.exceptions.RecordNotFoundException;
import pl.sdaacademy.conferenceroomreservationsystem.models.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;

import javax.annotation.PostConstruct;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    // get all organization, left here in case admin profile will be created
//    public List<OrganizationEntity> getOrganizations(@NonNull String name) {
//        return organizationRepository.findByName(name)
//                .map(Collections::singletonList)
//                .orElse(Collections.emptyList());
//    }

    public OrganizationEntity getOrganizationByName(@NonNull String name) {
        return organizationRepository.findByName(name)
                .orElseThrow(() -> new RecordNotFoundException("Organization Not Found:  " + name));
    }

    public void addOrganization(@NonNull OrganizationEntity organization) {
        organizationRepository.findByName(organization.getName()).ifPresent(org -> {
            throw new RecordAlreadyExistsException("Organization already exists: " + organization.getName());
        });
        organizationRepository.save(organization);
    }

    public void removeOrganization(@NonNull String name) {
        OrganizationEntity organization = organizationRepository.findByName(name)
                .orElseThrow(() -> new RecordNotFoundException("Organization Not Found: " + name));
        organizationRepository.delete(organization);
    }

    public void updateOrganization(@NonNull String old_org, @NonNull String new_org) {
        // check if in database
        organizationRepository.findByName(old_org)
                .ifPresentOrElse(org -> {
                    org.setName(new_org);
                    organizationRepository.save(org);
                }, () -> {
                    throw new RecordNotFoundException("Organization Not Found");
                });
    }


    @PostConstruct
    void init() {
        organizationRepository.save(new OrganizationEntity("Google"));
        organizationRepository.save(new OrganizationEntity("Amazon"));
        organizationRepository.save(new OrganizationEntity("Meta"));
    }
}
