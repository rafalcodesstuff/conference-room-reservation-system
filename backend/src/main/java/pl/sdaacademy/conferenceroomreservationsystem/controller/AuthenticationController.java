package pl.sdaacademy.conferenceroomreservationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ResponseDTO;
import pl.sdaacademy.conferenceroomreservationsystem.dto.UserDTO;
import pl.sdaacademy.conferenceroomreservationsystem.session.SessionRegistry;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private final AuthenticationManager manager;
    @Autowired
    private final SessionRegistry sessionRegistry;

    @Autowired
    public AuthenticationController(AuthenticationManager manager, SessionRegistry sessionRegistry) {
        this.manager = manager;
        this.sessionRegistry = sessionRegistry;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        final String sessionId = sessionRegistry.registerSession(user.getUsername());
        ResponseDTO response = new ResponseDTO();
        response.setSessionId(sessionId);

        return ResponseEntity.ok(response);
    }

//    @PostMapping("/logout")
//    public ResponseEntity<Object> login() {
//        return
//    }

//    private final PersonService personService;

//    @Autowired
//    public AuthenticationController(PersonService personService) {
//        this.personService = personService;
//    }
//
//    @PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<String> login(@RequestBody PersonDTO user) {
//        // TODO Make a JSON Validator
//        // TODO Check for nulls
//
//        // this will throw error if the user does not exist
//        personService.getPersonByEmail(user.getEmail());
//
//        PersonDTO personDTO = new PersonDTO();
//        return new ResponseEntity<>("It works", HttpStatus.OK);
//    }

//    @PostMapping(value = "/register", consumes = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<PersonDTO> register(@RequestBody PersonDTO user) {
//        // TODO Make a JSON Validator
//        // TODO Check for nulls
//
//        // check if user exists and then register
//        try {
//            PersonEntity person = personService.getPersonByEmail(user.getEmail());
//            throw new PersonAlreadyExistsException();
////            return new ResponseEntity<>(personDTO, HttpStatus.OK);
//        } catch (PersonNotFoundException exception) {
//            user.setPassword(user.getPassword());
//            personService.addPerson(user);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
////        throw new PersonNotFoundException();
////        return new ResponseEntity<>(person, HttpStatus.OK);
//    }
}
