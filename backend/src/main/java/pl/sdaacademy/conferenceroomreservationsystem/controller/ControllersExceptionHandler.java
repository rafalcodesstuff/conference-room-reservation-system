package pl.sdaacademy.conferenceroomreservationsystem.controller;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pl.sdaacademy.conferenceroomreservationsystem.exception.PersonAlreadyExistsException;
import pl.sdaacademy.conferenceroomreservationsystem.exception.PersonNotFoundException;
import pl.sdaacademy.conferenceroomreservationsystem.exception.RecordAlreadyExistsException;
import pl.sdaacademy.conferenceroomreservationsystem.exception.RecordNotFoundException;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class ControllersExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Incorrect Input Json Format", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>("Incorrect Input Type", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    ResponseEntity<String> handlePersonAlreadyExistsException(PersonAlreadyExistsException ex) {
        return new ResponseEntity<>("Person Already Exists", new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException ex) {
        return new ResponseEntity<>("Person Doesn't Exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecordAlreadyExistsException.class)
    ResponseEntity<String> handleRecordAlreadyExistsException(RecordAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    ResponseEntity<String> handleRecordNotFoundException(RecordNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
