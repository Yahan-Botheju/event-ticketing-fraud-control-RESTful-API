package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records;

public record GetEventByIdResult(
        String eventId,
        String eventTitle,
        String eventDescription,
        String eventLocation,
        String eventDate,
        String eventAvailableTickets,
        String eventTicketPrice
) {
}
