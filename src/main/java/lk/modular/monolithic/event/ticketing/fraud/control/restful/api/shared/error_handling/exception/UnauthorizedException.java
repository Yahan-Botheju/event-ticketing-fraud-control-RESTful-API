package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
