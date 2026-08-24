package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions.TicketAlreadyUsedException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions.TicketTransferNotAllowedException;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class Ticket {
    private Long ticketId;
    private String ticketCode;

    private Long eventId;

    private Long ownerId;

    private BigDecimal ticketPrice;
    private TicketStatus ticketStatus;

    private LocalDateTime purchasedAt;
    private LocalDateTime scannedAt;

    public Ticket(
            Long ticketId,
            String ticketCode,
            Long eventId,
            Long ownerId,
            BigDecimal ticketPrice,
            TicketStatus ticketStatus,
            LocalDateTime purchasedAt,
            LocalDateTime scannedAt
    ) {
        this.ticketId = ticketId;
        this.ticketCode = ticketCode;
        this.eventId = eventId;
        this.ownerId = ownerId;
        this.ticketPrice = ticketPrice;
        this.ticketStatus = ticketStatus;
        this.purchasedAt = purchasedAt;
        this.scannedAt = scannedAt;
    }


    /* __DOMAIN_BUSINESS_LOGICS__ */

    //set ticket usage
    public void markAsUsed() {
        if(this.ticketStatus == TicketStatus.USED) {
            throw new TicketAlreadyUsedException("Ticket is already used.");
        }
        if(this.ticketStatus == TicketStatus.CANCELLED ||  this.ticketStatus == TicketStatus.REFUNDED) {
            throw new TicketAlreadyUsedException("Ticket is already cancelled.");
        }
        this.ticketStatus = TicketStatus.USED;
        this.scannedAt = LocalDateTime.now();
    }

    //transfer ownership
    public void transferOwnerShip(Long newOwnerId) {
        if(this.ticketStatus != TicketStatus.PURCHASED) {
            throw new TicketTransferNotAllowedException("Only purchased ticket and unused ticket can be transferred.");
        }

        //set new owner id and set ticket status
        this.ownerId = newOwnerId;
        this.ticketStatus = TicketStatus.TRANSFERRED;
    }
}
