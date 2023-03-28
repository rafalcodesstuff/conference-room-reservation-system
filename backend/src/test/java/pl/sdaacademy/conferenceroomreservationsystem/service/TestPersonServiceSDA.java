package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sdaacademy.conferenceroomreservationsystem.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class TestPersonServiceSDA {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

//    @Test
//    void shouldHaveUserInRepository() {
//        PersonEntity personEntity = new PersonEntity("Bob", "asdf@gmail.com", "password");
//        personEntity.setId(1);
////        when(personService.getPersonByEmail("asdf@gmail.com")).thenReturn(personEntity);
//        when(personService.getPersonByName("Bob")).thenReturn(personEntity);
//        PersonEntity actual = personService.getPersonByName("Bob");
//
//        assertThat(actual).isNotNull();
//
//        assertThat(actual).isEqualTo(personEntity);
//        assertThat(actual.getUsername()).isEqualTo("Bob");
//        assertThat(actual.getEmail()).isEqualTo("asdf@gmail.com");
//
//    }
}
