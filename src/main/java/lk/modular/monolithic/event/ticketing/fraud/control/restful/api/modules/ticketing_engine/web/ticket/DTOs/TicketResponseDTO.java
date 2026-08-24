package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.TicketStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketResponseDTO(
        Long ticketId,
        String ticketCode,
        Long eventId,
        Long ownerId,
        BigDecimal ticketPrice,
        TicketStatus ticketStatus,
        LocalDateTime purchasedAt,
        LocalDateTime scannedAt
) {
}
