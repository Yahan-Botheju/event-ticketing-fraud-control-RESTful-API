package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handler.exception;

public class ResourceNotFoundException extends  RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
