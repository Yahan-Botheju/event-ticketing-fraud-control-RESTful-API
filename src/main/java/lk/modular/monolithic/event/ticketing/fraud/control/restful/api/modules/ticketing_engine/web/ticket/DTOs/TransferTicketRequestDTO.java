package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.ticket.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferTicketRequestDTO {
    @NotNull(message = "Ticket ID is required")
    private Long ticketId;

    @NotNull(message = "New Owner ID is required")
    private Long newOwnerId;
}
