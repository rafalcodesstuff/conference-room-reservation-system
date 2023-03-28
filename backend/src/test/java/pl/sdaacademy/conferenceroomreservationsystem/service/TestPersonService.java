package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import pl.sdaacademy.conferenceroomreservationsystem.controller.AuthenticationController;
import pl.sdaacademy.conferenceroomreservationsystem.dto.OrganizationDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.OrganizationEntity;
import pl.sdaacademy.conferenceroomreservationsystem.repository.OrganizationRepository;
import pl.sdaacademy.conferenceroomreservationsystem.user.UserRoles;
import pl.sdaacademy.conferenceroomreservationsystem.dto.PersonDTO;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TestPersonService {

    @Autowired
    public PersonService service;

    @Autowired
    private OrganizationRepository repository;

    @BeforeEach
    void setup() {
        OrganizationEntity organization = new OrganizationEntity();
        organization.setId(1);
        organization.setName("TestOrganization");
        organization.setCreated(LocalDateTime.now());
        organization.setModified(LocalDateTime.now());

        repository.save(organization);
    }

    @Test
    void testPersonCRUDL() {
        PersonDTO dto = new PersonDTO();
        dto.setUsername("Robert_Smith");
        dto.setEmail("asdf@qwerty.com");
        dto.setPassword("P4$$word");
        dto.setRole(UserRoles.MEMBER);
        dto.setOrganization("TestOrganization");

        // verify saving
        final PersonDTO savedPerson = service.save(dto);
        assertThat(savedPerson).isNotNull();
        assertThat(savedPerson.getId()).isNotNull();
        assertThat(savedPerson.getCreated()).isNotNull();
        assertThat(savedPerson.getModified()).isNotNull();
        assertThat(savedPerson.getUsername()).isNotNull();
        assertThat(savedPerson.getEmail()).isNotNull();
        assertThat(savedPerson.getPassword()).isNotNull();
        assertThat(savedPerson.getRole()).isNotNull();
        assertThat(savedPerson.getOrganization()).isNotNull();

        // verify read
        final PersonDTO personById = service.getById(savedPerson.getId());
        assertThat(personById).isNotNull();
        assertThat(personById)
                .extracting(
                    PersonDTO::getId,
                    PersonDTO::getUsername,
                    PersonDTO::getEmail,
                    PersonDTO::getPassword,
                    PersonDTO::getRole

                ).contains(
                    savedPerson.getId(),
                    savedPerson.getUsername(),
                    savedPerson.getEmail(),
                    savedPerson.getPassword(),
                    savedPerson.getRole()

                );

        // verify list
        final List<PersonDTO> people = service.list();
        assertThat(people).isNotNull();
        assertThat(people).hasSize(1);

        // verify update
        savedPerson.setUsername("Mike");
        savedPerson.setEmail("qwerty@asdf.com");
        savedPerson.setPassword("drow$$4P");
        savedPerson.setRole(UserRoles.LEADER);

        final PersonDTO updatedPerson = service.save(savedPerson);
        assertThat(updatedPerson).isNotNull();

        assertThat(updatedPerson.getModified()).isAfter(savedPerson.getModified());

        assertThat(updatedPerson.getUsername()).isEqualTo(savedPerson.getUsername());
        assertThat(updatedPerson.getEmail()).isEqualTo(savedPerson.getEmail());
        assertThat(updatedPerson.getPassword()).isEqualTo(savedPerson.getPassword());
        assertThat(updatedPerson.getRole()).isEqualTo(savedPerson.getRole());


        // verify delete
        final Boolean deleted = service.delete(savedPerson.getId());
        assertThat(deleted).isTrue();
        assertThat(service.getById(savedPerson.getId())).isNull();

        // try to delete something that does not exist
        assertThat(service.delete(123456789)).isFalse();
    }
}
