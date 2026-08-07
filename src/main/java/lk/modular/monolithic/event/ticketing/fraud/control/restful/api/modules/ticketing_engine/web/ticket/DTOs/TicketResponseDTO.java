package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDTO {
    private Long ticketId;
    private String ticketCode;
    private Long eventId;
    private Long ownerId;
    private BigDecimal ticketPrice;
    private TicketStatus ticketStatus;
    private LocalDateTime purchasedAt;
    private LocalDateTime scannedAt;
}
