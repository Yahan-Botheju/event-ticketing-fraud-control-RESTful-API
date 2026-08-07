package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handler.exception;

public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
