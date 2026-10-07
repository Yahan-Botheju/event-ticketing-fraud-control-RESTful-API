package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception;

public class MethodArgumentNotValidException extends RuntimeException {
    public MethodArgumentNotValidException(String message) {
        super(message);
    }
}
