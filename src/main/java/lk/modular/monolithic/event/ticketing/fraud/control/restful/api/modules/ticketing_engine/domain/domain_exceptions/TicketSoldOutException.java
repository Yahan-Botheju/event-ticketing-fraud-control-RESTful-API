package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions;

public class TicketSoldOutException extends RuntimeException {
    public TicketSoldOutException(String message) {
        super(message);
    }
}
