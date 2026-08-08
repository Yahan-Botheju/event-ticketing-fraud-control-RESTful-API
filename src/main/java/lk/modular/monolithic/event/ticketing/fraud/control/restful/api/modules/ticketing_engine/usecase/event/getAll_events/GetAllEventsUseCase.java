package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.getAll_events;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;

import java.util.List;

public interface GetAllEventsUseCase {

    //get all events
    List<Event> getAllEvents();
}
