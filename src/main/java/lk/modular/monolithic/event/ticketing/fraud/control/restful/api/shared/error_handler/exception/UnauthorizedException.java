package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handler.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
