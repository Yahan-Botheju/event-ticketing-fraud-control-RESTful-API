package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase._records;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateEventRequestCommand(
        String eventTitle,
        String eventDescription,
        String eventLocation,
        LocalDateTime eventDate,
        Integer eventTotalTickets,
        BigDecimal eventTicketPrice
) {
}
