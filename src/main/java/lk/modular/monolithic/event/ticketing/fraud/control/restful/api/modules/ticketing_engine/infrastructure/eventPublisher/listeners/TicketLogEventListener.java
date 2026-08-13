package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.eventPublisher.listeners;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketTransactionLogRepository;

public class TicketLogEventListener {

    //inject required dependencies
    private final TicketTransactionLogRepository ticketTransactionLogRepository;

    public TicketLogEventListener(TicketTransactionLogRepository ticketTransactionLogRepository) {
        this.ticketTransactionLogRepository = ticketTransactionLogRepository;
    }
}
