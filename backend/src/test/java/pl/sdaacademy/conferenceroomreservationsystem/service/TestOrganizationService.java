package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.sdaacademy.conferenceroomreservationsystem.dto.OrganizationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.exception.RecordNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TestOrganizationService {

    @Autowired
    private OrganizationService organizationService;

    @Test
    void testOrganizationCRUDL() {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setName("TestOrganization");

        // verify saving
        final OrganizationDTO savedOrganization = organizationService.save(dto);
        assertThat(savedOrganization).isNotNull();

        // verify read
        final OrganizationDTO organizationById = organizationService.getById(savedOrganization.getId());
        assertThat(organizationById).isNotNull();
        assertThat(organizationById.getName()).contains(savedOrganization.getName());

        // verify list
        final List<OrganizationDTO> organizations = organizationService.list();
        assertThat(organizations).isNotNull();
        assertThat(organizations).hasSize(1);

        // verify update
        savedOrganization.setName("UpdatedOrganization");

        final OrganizationDTO updatedOrganization = organizationService.save(savedOrganization);
        assertThat(updatedOrganization).isNotNull();
        assertThat(updatedOrganization.getModified()).isAfter(savedOrganization.getModified());
        assertThat(updatedOrganization.getName()).isEqualTo(savedOrganization.getName());

        // verify delete
        final Boolean deleted = organizationService.delete(savedOrganization.getId());
        assertThat(deleted).isTrue();
        assertThrows(RecordNotFoundException.class, () -> organizationService.getById(savedOrganization.getId()));

        // try to delete something that does not exist
        assertThat(organizationService.delete(123456789)).isFalse();
    }
}
