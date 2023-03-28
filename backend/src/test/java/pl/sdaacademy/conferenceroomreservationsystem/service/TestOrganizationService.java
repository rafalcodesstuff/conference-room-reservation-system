package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import pl.sdaacademy.conferenceroomreservationsystem.converter.OrganizationDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.dto.OrganizationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;
import pl.sdaacademy.conferenceroomreservationsystem.service.OrganizationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TestOrganizationService {

    @Autowired
    private OrganizationService organizationService;

    @BeforeEach
    void setup() {
//        OrganizationEntity org1 = new OrganizationEntity("IBM");
//        org1.setId(1);
//        org1.setCreated(LocalDateTime.now());
//        org1.setModified(LocalDateTime.now());
//
//        OrganizationEntity org2 = new OrganizationEntity("Motorola");
//        org2.setId(2);
//        org2.setCreated(LocalDateTime.now());
//        org2.setModified(LocalDateTime.now());
//
//        repository.save(org1);
//        repository.save(org2);
    }

    @Test
    void OrganizationCRUDL() {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setName("TestOrganization");

        // verify saving
        final OrganizationDTO savedOrganization = organizationService.save(dto);
        assertThat(savedOrganization).isNotNull();

        // verify read
        final OrganizationDTO organizationById = organizationService.getById(savedOrganization.getId());
        assertThat(organizationById).isNotNull();
        assertThat(organizationById.getName()).contains(savedOrganization.getName());

    }
}
