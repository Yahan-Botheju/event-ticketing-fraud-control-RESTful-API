package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions;

public class TicketReservedException extends DomainException {
    public TicketReservedException(String message) {
        super("ERROR_TICKET_ALREADY_RESERVED", message);
    }
}
