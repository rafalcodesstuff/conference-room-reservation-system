package pl.sdaacademy.conferenceroomreservationsystem.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) {
        super(message);
    }
}
