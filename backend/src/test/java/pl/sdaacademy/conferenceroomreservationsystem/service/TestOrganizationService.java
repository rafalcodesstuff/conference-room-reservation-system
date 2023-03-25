package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TestOrganizationService {
    @Mock
    private OrganizationRepository organisationRepository;
    @InjectMocks
    private OrganizationService organizationService;

//    @Test
//    void testGetAllProjects() {
//        OrganizationEntity org1 = new OrganizationEntity(1, "IBM");
//        OrganizationEntity org2 = new OrganizationEntity(2, "Motorola");
//
//        when(organisationRepository.findAll()).thenReturn(List.of(org1, org2));
//        List<OrganizationEntity> actual = organizationService.getOrganizations();
//
//        assertThat(actual).hasSize(2);
//        assertThat(actual).containsExactly(org1, org2);
//        assertThat(actual).doesNotContainNull();
//
//        assertThat(actual.get(0)).isEqualTo(org1);
//        assertThat(actual.get(0).getName()).isEqualTo("IBM");
//        assertThat(actual.get(0).getId()).isEqualTo(1);
//    }

//    @Test
//    void testGetOrganizationById() {
//        OrganizationEntity org = new OrganizationEntity(1, "IBM");

//        when(organisationRepository.findById(1)).thenReturn(Optional.of(org));
//        OrganizationEntity actual = organizationService.getOrganizationById(1);
//
//        assertThat(actual).isNotNull();
//
//        assertThat(actual).isEqualTo(org);
//        assertThat(actual.getName()).isEqualTo("IBM");
//        assertThat(actual.getId()).isEqualTo(1);
//    }

    // TODO fix this test so that organization serverice recieves an entity and not a string
//    @Test
//    void testAddOrganization() {
//        OrganizationEntity output_org = new OrganizationEntity(1, "IBM");
//
//        when(organisationRepository.findByName("IBM")).thenReturn(Optional.empty());
//        when(organisationRepository.save(any())).thenReturn(output_org);
//        OrganizationEntity actual = organizationService.addOrganization("IBM");

//        assertThat(actual).isNotNull();
//
//        assertThat(actual.getName()).isEqualTo("IBM");
//        assertThat(actual.getId()).isEqualTo(1);
//    }

    // TODO fix this test so that organization serverice recieves an entity and not a string
//    @Test
//    void testAddOrganization_ShouldThrow_RecordAlreadyExistsException() {
//        OrganizationEntity org = new OrganizationEntity(1, "IBM");
//
//        when(organisationRepository.findByName("IBM")).thenReturn(Optional.of(org));
        // using unused mocks throws error
//        when(organisationRepository.save(any())).thenReturn(org);

//        RecordAlreadyExistsException thrown = assertThrows(
//                RecordAlreadyExistsException.class,
//                () -> organizationService.addOrganization("IBM")
//        );
//
//        assertEquals("Organization already exists: IBM", thrown.getMessage());
//    }


}
