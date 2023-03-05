package pl.sdaacademy.conferenceroomreservationsystem.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    List<Organization> getOrganizations(String name) {
        if (name != null) {
            return organizationRepository.findByName(name)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        }
        return organizationRepository.findAll();
    }

    Organization getOrganizationByID(Integer id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Organization Not Found, ID:  " + id));
    }

//    Organization getOrganizationByName(String name) {
//        return organizationRepository.findByName(name)
//                .orElseThrow(() -> new NoSuchElementException("Organization Not Found, Name:  " + name));
//    }

    void addOrganization(@NonNull Organization organization) throws RuntimeException {
        organizationRepository.findByName(organization.getName()).ifPresent(org -> {
            throw new RuntimeException("Element Already Exists");
        });
        organizationRepository.save(new Organization(organization.getName()));
    }

    void removeOrganization(@NonNull String name) throws NoSuchElementException {
        Organization organization = organizationRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Organization Not Found: " + name));
        organizationRepository.delete(organization);
    }

    void updateOrganization(@NonNull String old_org, @NonNull String new_org) throws NoSuchElementException {
        // check if in database
        organizationRepository.findByName(old_org)
                .ifPresentOrElse(org -> {
                    org.setName(new_org);
                    organizationRepository.save(org);
                }, () -> {
                    throw new NoSuchElementException("Organization Not Found");
                });
    }


    @PostConstruct
    void init() {
        organizationRepository.save(new Organization("Google"));
        organizationRepository.save(new Organization("Amazon"));
        organizationRepository.save(new Organization("Meta"));
    }
}
