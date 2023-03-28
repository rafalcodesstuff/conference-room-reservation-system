package pl.sdaacademy.conferenceroomreservationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sdaacademy.conferenceroomreservationsystem.dto.PersonDTO;
import pl.sdaacademy.conferenceroomreservationsystem.dto.ResponseDTO;
import pl.sdaacademy.conferenceroomreservationsystem.dto.UserSessionDTO;
import pl.sdaacademy.conferenceroomreservationsystem.service.AuthenticationService;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDTO> login(@RequestBody UserSessionDTO user) {
        ResponseDTO response = authenticationService.loginUser(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDTO> register(@RequestBody PersonDTO user) {
        ResponseDTO response = authenticationService.registerUser(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseDTO> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        ResponseDTO response = authenticationService.logoutUser(token);
        return ResponseEntity.ok(response);
    }

}
