package pl.sdaacademy.conferenceroomreservationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdaacademy.conferenceroomreservationsystem.api.AbstractCRUDLApi;
import pl.sdaacademy.conferenceroomreservationsystem.dto.PersonDTO;
import pl.sdaacademy.conferenceroomreservationsystem.entity.PersonEntity;

@RestController
@RequestMapping("/api/users")
public class PersonController extends AbstractCRUDLController<PersonEntity, PersonDTO> {

    @Autowired
    public PersonController(AbstractCRUDLApi<PersonEntity, PersonDTO> api) {
        super(api);
    }
}
//    @GetMapping
//    public ResponseEntity<List<PersonDTO>> getPeople() {
//        List<PersonDTO> personDTO = personService.getPeople()
//                .stream()
//                .map(person -> new PersonDTO(
//                        person.getUsername(),
//                        person.getEmail()
//                ))
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(personDTO, HttpStatus.OK);
//    }

//    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<PersonDTO> getUser(@RequestBody PersonDTO user) {
//        PersonEntity personEntity = personService.getPersonByEmail(user.getEmail());
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setUsername(personEntity.getUsername());
//        personDTO.setEmail(personEntity.getEmail());
//        personDTO.setRole(personEntity.getRole());
//        personDTO.setOrganization(personEntity.getOrganization());
//        return new ResponseEntity<>(personDTO, HttpStatus.OK);
//    }
//    @PostMapping(value="/add", consumes = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<PersonDTO> addUser(@RequestBody PersonDTO user) {
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setUsername(user.getUsername());
//        personDTO.setEmail(user.getEmail());
//        personDTO.setPassword(user.getPassword());
////        personService.addPerson(personDTO);
//        return new ResponseEntity<>(personDTO, HttpStatus.OK);
//    }
//
//    @GetMapping("/register")
//    public ResponseEntity<String> register() {
//        return new ResponseEntity<>("It works", HttpStatus.OK);
//    }
//}
