package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception;

public class ForbiddenException extends  RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
