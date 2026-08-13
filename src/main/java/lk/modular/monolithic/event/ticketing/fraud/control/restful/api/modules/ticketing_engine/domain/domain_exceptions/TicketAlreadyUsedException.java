package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions;

public class TicketAlreadyUsedException extends DomainException {
    public TicketAlreadyUsedException(String message) {
        super("ERROR_TICKET_ALREADY_USED", message);
    }
}
