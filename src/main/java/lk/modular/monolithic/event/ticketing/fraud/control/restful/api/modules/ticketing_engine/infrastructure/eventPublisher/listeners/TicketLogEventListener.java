package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.infrastructure.eventPublisher.listeners;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.TicketStatus;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.ticket_log_records.TicketPurchasedEvent;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.ticket_log_records.TicketTransactionLog;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.TicketTransactionLogRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

public class TicketLogEventListener {

    //inject required dependencies
    private final TicketTransactionLogRepository ticketTransactionLogRepository;

    public TicketLogEventListener(TicketTransactionLogRepository ticketTransactionLogRepository) {
        this.ticketTransactionLogRepository = ticketTransactionLogRepository;
    }

    @Async
    @EventListener
    public void handleTicketPurchasedEvent(TicketPurchasedEvent ticketPurchasedEvent) {
        TicketTransactionLog transactionLog = new TicketTransactionLog(
                ticketPurchasedEvent.ticketId(),
                ticketPurchasedEvent.userId(),
                ticketPurchasedEvent.eventId(),
                ticketPurchasedEvent.ticketPrice(),
                ticketPurchasedEvent.timeStamp(),
                TicketStatus.PURCHASED_SUCCESSFULLY

        );
        ticketTransactionLogRepository.save(transactionLog);
    }
}
