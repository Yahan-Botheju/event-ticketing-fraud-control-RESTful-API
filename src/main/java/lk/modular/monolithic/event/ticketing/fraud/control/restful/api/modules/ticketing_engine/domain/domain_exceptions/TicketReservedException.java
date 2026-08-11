package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions;

public class TicketReservedException extends RuntimeException {
    public TicketReservedException(String message) {
        super(message);
    }
}
