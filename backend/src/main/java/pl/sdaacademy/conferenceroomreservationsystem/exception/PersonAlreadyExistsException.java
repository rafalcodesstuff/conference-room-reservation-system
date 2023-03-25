package pl.sdaacademy.conferenceroomreservationsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User already exists")
public class PersonAlreadyExistsException extends RuntimeException {
}
