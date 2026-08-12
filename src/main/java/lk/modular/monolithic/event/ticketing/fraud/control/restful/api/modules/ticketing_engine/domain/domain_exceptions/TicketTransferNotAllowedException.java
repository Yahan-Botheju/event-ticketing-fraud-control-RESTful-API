package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions;

public class TicketTransferNotAllowedException extends RuntimeException {
    public TicketTransferNotAllowedException(String message) {
        super(message);
    }
}
