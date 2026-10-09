package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateEventCommand(
        Long organizerId,
        String eventTitle,
        String eventDescription,
        String eventLocation,
        LocalDateTime eventDate,
        Integer eventTotalTickets,
        BigDecimal eventTicketPrice
) {
    public CreateEventCommand {
        if (organizerId == null) {
            throw new IllegalArgumentException("Organizer ID is required");
        }
        if (eventTitle == null || eventTitle.isBlank()) {
            throw new IllegalArgumentException("Event title is required");
        }
        if (eventDescription == null || eventDescription.isBlank()) {
            throw new IllegalArgumentException("Event description is required");
        }
        if (eventLocation == null || eventLocation.isBlank()) {
            throw new IllegalArgumentException("Event location is required");
        }
        if (eventDate == null || eventDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Event date must be in the future");
        }
        if (eventTotalTickets == null || eventTotalTickets <= 0) {
            throw new IllegalArgumentException("Total tickets must be greater than zero");
        }
        if (eventTicketPrice == null || eventTicketPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Event ticket price must be greater than zero");
        }
    }
}
