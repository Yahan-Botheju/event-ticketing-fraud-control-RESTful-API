package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.InvalidTicketException;

import java.math.BigDecimal;

public class CreateEventUseCaseImpl implements CreateEventUseCase {

    //inject required dependencies
    private final EventRepository eventRepository;

    public CreateEventUseCaseImpl(
            EventRepository eventRepository
    ) {
        this.eventRepository = eventRepository;
    }

    /* __PUBLIC_METHODS__ */

    //create event
    @Override
    public Event execute(Event event){
        //check ticket availability
        if(event.getEventTotalTickets() == null || event.getEventTicketPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidTicketException("Total tickets must be greater than zero");
        }
        //set event available tickets to total tickets
        Event newEvent = Event.createNewEvent(
                event.getEventTitle(),
                event.getEventDescription(),
                event.getEventLocation(),
                event.getEventDate(),
                event.getEventTotalTickets(),
                event.getEventTicketPrice(),
                event.getOrganizerId()
        );

       return eventRepository.save(newEvent);
    }
}
