package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.GetAllEventsResult;

import java.util.List;

public class GetAllEventsUseCaseImpl implements GetAllEventsUseCase {

    //inject required dependencies
    private final EventRepository eventRepository;

    public GetAllEventsUseCaseImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //get all events
    @Override
    public List<GetAllEventsResult> getAllEvents(){
        return eventRepository.getAllEvents().stream()
                .map(event -> new GetAllEventsResult(
                        event.getEventId(),
                        event.getEventTitle(),
                        event.getEventDescription(),
                        event.getEventLocation(),
                        event.getEventDate().toString(),
                        event.getEventTicketPrice().toString()
                )).toList();
    }
}
