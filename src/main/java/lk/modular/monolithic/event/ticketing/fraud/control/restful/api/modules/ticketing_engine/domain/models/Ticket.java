package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ConflictException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.InvalidTicketException;
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
public class Ticket {
    private Long ticketId;
    private String ticketCode;

    private Long eventId;

    private Long ownerId;

    private BigDecimal ticketPrice;
    private TicketStatus ticketStatus;

    private LocalDateTime purchasedAt;
    private LocalDateTime scannedAt;

    /* __DOMAIN_BUSINESS_LOGICS__ */

    //set ticket usage
    public void markAsUsed() {
        if(this.ticketStatus == TicketStatus.USED) {
            throw new InvalidTicketException("Ticket is already used.");
        }
        if(this.ticketStatus == TicketStatus.CANCELLED ||  this.ticketStatus == TicketStatus.REFUNDED) {
            throw new InvalidTicketException("Ticket is already cancelled.");
        }
        this.ticketStatus = TicketStatus.USED;
        this.scannedAt = LocalDateTime.now();
    }

    //transfer ownership
    public void transferOwnerShip(Long newOwnerId) {
        if(this.ticketStatus != TicketStatus.PURCHASED) {
            throw new ConflictException("Only purchased ticket and unused ticket can be transferred.");
        }

        //set new owner id and set ticket status
        this.ownerId = newOwnerId;
        this.ticketStatus = TicketStatus.TRANSFERRED;
    }
}
