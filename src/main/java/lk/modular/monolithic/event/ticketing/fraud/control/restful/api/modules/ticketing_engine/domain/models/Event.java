package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
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
