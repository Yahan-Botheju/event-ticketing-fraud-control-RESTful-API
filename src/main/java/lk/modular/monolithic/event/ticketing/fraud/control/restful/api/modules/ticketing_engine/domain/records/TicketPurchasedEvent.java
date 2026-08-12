package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.records;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketPurchasedEvent(
        Long ticketId,
        Long userId,
        Long eventId,
        BigDecimal ticketPrice,
        LocalDateTime timeStamp
) {
    public TicketPurchasedEvent(Long ticketId, Long userId, Long eventId, BigDecimal ticketPrice) {
        this(ticketId, userId, eventId, ticketPrice, LocalDateTime.now());
    }
}
