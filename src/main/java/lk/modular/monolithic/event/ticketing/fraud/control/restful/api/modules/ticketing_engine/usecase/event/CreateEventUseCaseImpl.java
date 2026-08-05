package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.repositories.EventRepository;

public class CreateEventUseCaseImpl implements CreateEventUseCase {

    //inject required dependencies
    private EventRepository eventRepository;

    public CreateEventUseCaseImpl(
            EventRepository eventRepository
    ) {
        this.eventRepository = eventRepository;
    }
}
