package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.GetEventByIdCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.GetEventByIdResult;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.exception.ResourceNotFoundException;

public class EventByIdUseCaseImpl implements  EventByIdUseCase {

    //inject required dependencies
    private final EventRepository eventRepository;

    public EventByIdUseCaseImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //get specific event by event ID
    @Override
    public GetEventByIdResult getByEventId(GetEventByIdCommand getEventByIdCommand){

        if(getEventByIdCommand.eventId() == null){
            throw new IllegalArgumentException("Event id cannot be empty");
        }

        Event event = eventRepository.findById(getEventByIdCommand.eventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        return new GetEventByIdResult(
                event.getEventId().toString(),
                event.getEventTitle(),
                event.getEventDescription(),
                event.getEventLocation(),
                event.getEventDate().toString(),
                event.getEventAvailableTickets().toString(),
                event.getEventTicketPrice().toString()
        );
    }

}
