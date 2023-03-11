package pl.sdaacademy.conferenceroomreservationsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.models.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<OrganizationEntity> getOrganizations(String name) {
        if (name != null) {
            return organizationRepository.findByName(name)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        }
        return organizationRepository.findAll();
    }

    public OrganizationEntity getOrganizationByID(Integer id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Organization Not Found, ID:  " + id));
    }

    public void addOrganization(@NonNull OrganizationEntity organization) throws RuntimeException {
        organizationRepository.findByName(organization.getName()).ifPresent(org -> {
            throw new RuntimeException("Element Already Exists");
        });
        organizationRepository.save(new OrganizationEntity(organization.getName()));
    }

    public void removeOrganization(@NonNull String name) throws NoSuchElementException {
        OrganizationEntity organization = organizationRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Organization Not Found: " + name));
        organizationRepository.delete(organization);
    }

    public void updateOrganization(@NonNull String old_org, @NonNull String new_org) throws NoSuchElementException {
        // check if in database
        organizationRepository.findByName(old_org)
                .ifPresentOrElse(org -> {
                    org.setName(new_org);
                    organizationRepository.save(org);
                }, () -> {
                    throw new NoSuchElementException("Organization Not Found");
                });
    }


//    @PostConstruct
//    void init() {
//        organizationRepository.save(new OrganizationEntity("Google"));
//        organizationRepository.save(new OrganizationEntity("Amazon"));
//        organizationRepository.save(new OrganizationEntity("Meta"));
//    }
}
