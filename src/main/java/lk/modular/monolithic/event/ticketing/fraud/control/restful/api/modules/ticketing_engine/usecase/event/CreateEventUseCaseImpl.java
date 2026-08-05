package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.exception.InvalidTicketException;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public class CreateEventUseCaseImpl implements CreateEventUseCase {

    //inject required dependencies
    private EventRepository eventRepository;

    public CreateEventUseCaseImpl(
            EventRepository eventRepository
    ) {
        this.eventRepository = eventRepository;
    }

    /* __PUBLIC_METHODS__ */

    //create event
    @Override
    @Transactional
    public Event execute(Event event){
        //check ticket availability
        if(event.getEventTotalTickets() == null || event.getEventTicketPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidTicketException("Total tickets must be greater than zero");
        }
        //set event available tickets to total tickets
        event.setEventAvailableTickets(event.getEventTotalTickets());
    }
}
