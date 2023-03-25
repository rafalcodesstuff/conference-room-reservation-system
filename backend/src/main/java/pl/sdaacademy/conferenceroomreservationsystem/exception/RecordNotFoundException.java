package pl.sdaacademy.conferenceroomreservationsystem.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) {
        super(message);
    }
}
