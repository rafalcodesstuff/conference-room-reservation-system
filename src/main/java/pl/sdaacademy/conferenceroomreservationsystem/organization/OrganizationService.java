package pl.sdaacademy.conferenceroomreservationsystem.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    @PostConstruct
    void init() {
        organizationRepository.save(new Organization("Google"));
        organizationRepository.save(new Organization("Amazon"));
        organizationRepository.save(new Organization("Meta"));
    }
}
