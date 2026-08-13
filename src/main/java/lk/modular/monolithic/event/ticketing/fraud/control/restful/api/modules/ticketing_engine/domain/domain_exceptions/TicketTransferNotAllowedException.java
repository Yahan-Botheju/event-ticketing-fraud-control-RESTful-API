package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions;

public class TicketTransferNotAllowedException extends DomainException {
    public TicketTransferNotAllowedException(String message) {
        super("ERROR_TICKET_TRANSFER_NOT_ALLOWED", message);
    }
}
