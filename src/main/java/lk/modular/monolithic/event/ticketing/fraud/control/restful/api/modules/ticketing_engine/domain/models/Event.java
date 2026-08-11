package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions.TicketReservedException;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ConflictException;
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
public class Event {
    private Long eventId;
    private String eventTitle;
    private String eventDescription;
    private String eventLocation;
    private LocalDateTime eventDate;
    private Integer eventTotalTickets;
    private Integer eventAvailableTickets;
    private BigDecimal eventTicketPrice;
    private Long organizerId;
    private LocalDateTime createdAt;

    /* __DOMAIN_BUSINESS_LOGIC__ */

    //check ticket availability
    public boolean isSoldOut(){
        return this.eventAvailableTickets != null && this.eventAvailableTickets <= 0;
    }

    //reserve a ticket
    public void reserveTicket(int ticketCount){
        if(isSoldOut() || this.eventAvailableTickets < ticketCount){
            throw new TicketReservedException("All tickets have been reserved");
        }
        this.eventAvailableTickets -= ticketCount;
    }
}
