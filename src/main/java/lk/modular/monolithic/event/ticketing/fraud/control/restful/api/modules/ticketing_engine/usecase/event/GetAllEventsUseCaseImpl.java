package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;

public class GetAllEventsUseCaseImpl implements GetAllEventsUseCase {

    //inject required dependencies
    private final EventRepository eventRepository;

    public GetAllEventsUseCaseImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
