package pl.sdaacademy.conferenceroomreservationsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.dto.PersonDTO;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ResponseDTO;
import pl.sdaacademy.conferenceroomreservationsystem.dto.UserDTO;
import pl.sdaacademy.conferenceroomreservationsystem.session.SessionRegistry;
import pl.sdaacademy.conferenceroomreservationsystem.exception.PersonAlreadyExistsException;

@Service
public class AuthenticationService {

    private final AuthenticationManager manager;
    private final SessionRegistry sessionRegistry;
    private final PersonService personService;

    @Autowired
    public AuthenticationService(AuthenticationManager manager, SessionRegistry sessionRegistry, PersonService personService) {
        this.manager = manager;
        this.sessionRegistry = sessionRegistry;
        this.personService = personService;
    }

    public ResponseDTO loginUser(UserDTO user) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        String sessionId = sessionRegistry.registerSession(user.getUsername());
        ResponseDTO response = new ResponseDTO();
        response.setSessionId(sessionId);
        return response;
    }

    public ResponseDTO registerUser(PersonDTO user) {
        personService.getPersonByUsername(user.getUsername()).ifPresent(
                (arg) -> { throw new PersonAlreadyExistsException(); }
        );

        String encryptedPassword = new BCryptPasswordEncoder(10).encode(user.getPassword());
        user.setPassword(encryptedPassword);
        personService.save(user);

        String sessionId = sessionRegistry.registerSession(user.getUsername());
        ResponseDTO response = new ResponseDTO();
        response.setSessionId(sessionId);
        return response;
    }

    public ResponseDTO logoutUser(String token) {
        sessionRegistry.removeSession(token);
        ResponseDTO response = new ResponseDTO();
        response.setSessionId("");
        return response;
    }
}
