package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {
    private Long eventId;
    private String eventTitle;
    private String eventDescription;
    private String eventLocation;
    private LocalDateTime eventDate;
    private Integer eventTotalTickets;
    private Integer eventAvailableTickets;
    private BigDecimal eventTicketPrice;
    private Long organizerId;
    private LocalDateTime createdAt;
}
