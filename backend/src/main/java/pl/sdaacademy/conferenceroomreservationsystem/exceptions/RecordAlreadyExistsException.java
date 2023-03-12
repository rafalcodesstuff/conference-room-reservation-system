package pl.sdaacademy.conferenceroomreservationsystem.exceptions;

public class RecordAlreadyExistsException extends RuntimeException {
    public RecordAlreadyExistsException(String message) {
        super(message);
    }
}
