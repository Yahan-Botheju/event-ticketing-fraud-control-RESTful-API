package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions;

public class TicketTransferNotAllowedExecption extends RuntimeException {
    public TicketTransferNotAllowedExecption(String message) {
        super(message);
    }
}
