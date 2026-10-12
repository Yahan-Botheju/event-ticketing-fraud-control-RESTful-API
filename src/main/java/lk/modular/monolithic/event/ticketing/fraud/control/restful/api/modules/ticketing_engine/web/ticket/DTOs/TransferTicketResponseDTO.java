package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferTicketResponseDTO {
    private String ticketId;
    private String currentOwnerId;
    private String newOwnerId;
    private String ticketCode;
    private String eventId;
    private String ticketPrice;
    private String ticketStatus;
    private String purchasedAt;
    private String scannedAt;
}
