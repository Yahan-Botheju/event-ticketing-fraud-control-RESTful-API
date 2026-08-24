package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions.TicketReservedException;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
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

    public Event(
            Long eventId,
            String eventTitle,
            String eventDescription,
            String eventLocation,
            LocalDateTime eventDate,
            Integer eventTotalTickets,
            Integer eventAvailableTickets,
            BigDecimal eventTicketPrice,
            Long organizerId,
            LocalDateTime createdAt
    ) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTotalTickets = eventTotalTickets;
        this.eventAvailableTickets = eventAvailableTickets;
        this.eventTicketPrice = eventTicketPrice;
        this.organizerId = organizerId;
        this.createdAt = createdAt;
    }


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
