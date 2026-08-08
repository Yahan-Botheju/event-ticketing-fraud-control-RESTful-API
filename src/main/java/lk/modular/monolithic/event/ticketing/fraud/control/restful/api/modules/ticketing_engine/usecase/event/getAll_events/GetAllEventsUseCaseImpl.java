package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.getAll_events;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;

import java.util.List;

public class GetAllEventsUseCaseImpl implements GetAllEventsUseCase {

    //inject required dependencies
    private final EventRepository eventRepository;

    public GetAllEventsUseCaseImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //get all events
    @Override
    public List<Event> getAllEvents(){
        return eventRepository.getAllEvents();
    }
}
