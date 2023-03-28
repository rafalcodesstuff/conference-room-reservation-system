package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import pl.sdaacademy.conferenceroomreservationsystem.converter.OrganizationDTOConverter;
import pl.sdaacademy.conferenceroomreservationsystem.dto.OrganizationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TestOrganizationService {

    @Mock
    private OrganizationRepository repository;

    @Mock
    private OrganizationDTOConverter converter;

    @InjectMocks
    private OrganizationService organizationService;

    @BeforeEach
    void setup() {
        OrganizationEntity org1 = new OrganizationEntity("IBM");
        OrganizationEntity org2 = new OrganizationEntity("Motorola");

        repository.save(org1);
        repository.save(org2);

    }

    @Test
    void createOrganization() {

    }

//    @Test
//    void testGetOrganizationById() {
//        OrganizationEntity org = new OrganizationEntity(1, "IBM");
//
//        when(organisationRepository.findById(1)).thenReturn(Optional.of(org));
//        OrganizationEntity actual = organizationService.getOrganizationById(1);
//
//        assertThat(actual).isNotNull();
//
//        assertThat(actual).isEqualTo(org);
//        assertThat(actual.getName()).isEqualTo("IBM");
//        assertThat(actual.getId()).isEqualTo(1);
//    }

    @Test
    void testGetAllProjects() {
        OrganizationDTOConverter converter = new OrganizationDTOConverter();

        OrganizationEntity org1 = new OrganizationEntity("IBM");
        OrganizationEntity org2 = new OrganizationEntity("Motorola");

        org1.setId(1);
        org2.setId(2);

        OrganizationDTO org1_dto = converter.convert(org1);
        OrganizationDTO org2_dto = converter.convert(org2);

        when(repository.findAll()).thenReturn(List.of(org1, org2));
//        when(organizationService.list()).thenReturn(List.of(org1_dto, org2_dto));
        List<OrganizationDTO> result = organizationService.list();

        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(org1_dto, org2_dto);
        assertThat(result).doesNotContainNull();

        assertThat(result.get(0)).isEqualTo(org1_dto);
        assertThat(result.get(0).getName()).isEqualTo("IBM");
        assertThat(result.get(0).getId()).isEqualTo(1);
    }



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
