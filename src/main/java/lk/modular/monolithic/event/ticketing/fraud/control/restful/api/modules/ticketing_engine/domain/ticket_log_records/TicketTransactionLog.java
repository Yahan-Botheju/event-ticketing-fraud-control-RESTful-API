package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.ticket_log_records;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.TicketStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketTransactionLog(
        String transactionLogId,
        Long ticketId,
        Long userId,
        Long eventId,
        BigDecimal ticketPrice,
        LocalDateTime timestamp,
        TicketStatus ticketStatus
) {
    public TicketTransactionLog(
            Long ticketId,
            Long userId,
            Long eventId,
            BigDecimal ticketPrice,
            LocalDateTime timestamp,
            TicketStatus ticketStatus
    ) {
        this(
                null,
                ticketId,
                userId,
                eventId,
                ticketPrice,
                timestamp,
                ticketStatus
        );
    }

}

