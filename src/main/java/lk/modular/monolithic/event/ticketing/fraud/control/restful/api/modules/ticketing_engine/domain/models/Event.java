package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.domain_exceptions.IllegalStateException;
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

    //create factory method fot create new event
    public static Event createNewEvent(
            String title,
            String description,
            String location,
            LocalDateTime eventDate,
            Integer totalTickets,
            BigDecimal ticketPrice,
            Long organizerId
    ) {
        if (totalTickets == null || totalTickets <= 0) {
            throw new IllegalStateException("Total tickets must be greater than zero");
        }
        if (ticketPrice == null || ticketPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Ticket price must be greater than zero");
        }

        return new Event(
                null,
                title,
                description,
                location,
                eventDate,
                totalTickets,
                totalTickets,
                ticketPrice,
                organizerId,
                LocalDateTime.now()
        );
    }


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
