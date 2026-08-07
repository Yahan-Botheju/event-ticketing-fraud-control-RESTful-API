package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception;

public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
