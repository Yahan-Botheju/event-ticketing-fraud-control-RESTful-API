package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.CreateEventRequestCommand;
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
    public Event execute(CreateEventRequestCommand requestCommand, Long organizerId) {
        //check ticket availability
        if(requestCommand.eventTotalTickets() == null || requestCommand.eventTicketPrice().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidTicketException("Total tickets must be greater than zero");
        }
        //set event available tickets to total tickets
        Event newEvent = Event.createNewEvent(
                requestCommand.eventTitle(),
                requestCommand.eventDescription(),
                requestCommand.eventLocation(),
                requestCommand.eventDate(),
                requestCommand.eventTotalTickets(),
                requestCommand.eventTicketPrice(),
                organizerId
        );

       return eventRepository.save(newEvent);
    }
}
