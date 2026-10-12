package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.Ticket.records;

public record TransferTicketResult(
        String ticketId,
        String currentOwnerId,
        String newOwnerId,
        String ticketCode,
        String eventId,
        String ticketPrice,
        String ticketStatus,
        String purchasedAt,
        String scannedAt
) {
}
