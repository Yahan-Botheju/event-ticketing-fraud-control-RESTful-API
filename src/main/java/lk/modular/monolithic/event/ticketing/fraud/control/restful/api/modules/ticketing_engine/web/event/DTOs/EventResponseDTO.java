package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventResponseDTO(
         Long eventId,
         String eventTitle,
         String eventDescription,
         String eventLocation,
         LocalDateTime eventDate,
         Integer eventTotalTickets,
         Integer eventAvailableTickets,
         BigDecimal eventTicketPrice,
         Long organizerId,
         LocalDateTime createdAt
) {
}
