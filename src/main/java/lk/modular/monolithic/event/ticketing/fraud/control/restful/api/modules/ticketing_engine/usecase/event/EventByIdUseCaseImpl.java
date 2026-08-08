package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;

public class EventByIdUseCaseImpl implements  EventByIdUseCase {

    //inject required dependencies
    private final EventRepository eventRepository;

    public EventByIdUseCaseImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //get specific event by event ID
    @Override
    public Event getByEventId(Long eventId){
        return  eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found" + eventId));
    }

}
