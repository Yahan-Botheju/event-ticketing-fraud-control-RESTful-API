package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records;

public record CreateEventResult(
        String eventId,
        String eventTitle,
        String eventDescription,
        String eventLocation,
        String eventDate,
        String eventTotalTickets,
        String eventAvailableTickets,
        String eventTicketPrice,
        String organizerId,
        String createdAt
) {
}
