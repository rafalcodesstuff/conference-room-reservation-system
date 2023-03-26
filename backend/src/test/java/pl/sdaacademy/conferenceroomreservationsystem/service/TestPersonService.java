package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sdaacademy.conferenceroomreservationsystem.user.UserRoles;
import pl.sdaacademy.conferenceroomreservationsystem.dto.PersonDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestPersonService {

    @Autowired
    public PersonService service;

    @Test
    void testPersonCRUDL() {
        PersonDTO dto = new PersonDTO();
        dto.setUsername("Robert_Smith");
        dto.setEmail("asdf@qwerty.com");
        dto.setPassword("P4$$word");
        dto.setRole(UserRoles.MEMBER);

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

        // TODO need to combine them with database
//        assertThat(savedPerson.getOrganization()).isNotNull();
//        assertThat(savedPerson.getReservations()).isNotNull();

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

                    // TODO this needs further work
//                  PersonDTO::getOrganization,
//                  PersonDTO::getReservations,
                ).contains(
                    savedPerson.getId(),
                    savedPerson.getUsername(),
                    savedPerson.getEmail(),
                    savedPerson.getPassword(),
                    savedPerson.getRole()

                    // TODO this needs further work
//                  savedPerson.getOrganization(),
//                  savedPerson.getReservations()
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
        // TODO needs to verify reservations and organization

        final PersonDTO updatedPerson = service.save(savedPerson);
        assertThat(updatedPerson).isNotNull();

        assertThat(updatedPerson.getModified()).isAfter(savedPerson.getModified());

        assertThat(updatedPerson.getUsername()).isEqualTo(savedPerson.getUsername());
        assertThat(updatedPerson.getEmail()).isEqualTo(savedPerson.getEmail());
        assertThat(updatedPerson.getPassword()).isEqualTo(savedPerson.getPassword());
        assertThat(updatedPerson.getRole()).isEqualTo(savedPerson.getRole());

        // TODO needs to verify reservations and organization

        // verify delete
        final Boolean deleted = service.delete(savedPerson.getId());
        assertThat(deleted).isTrue();
        assertThat(service.getById(savedPerson.getId())).isNull();

        // try to delete something that does not exist
        assertThat(service.delete(123456789)).isFalse();
    }
}
