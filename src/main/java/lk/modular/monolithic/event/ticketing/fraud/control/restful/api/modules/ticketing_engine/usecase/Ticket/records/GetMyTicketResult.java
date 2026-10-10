package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records;

public record GetMyTicketResult(
        String ticketId,
        String ticketCode,
        String eventId,
        String ownerId,
        String ticketPrice,
        String ticketStatus,
        String purchasedAt,
        String scannedAt
) {
}
