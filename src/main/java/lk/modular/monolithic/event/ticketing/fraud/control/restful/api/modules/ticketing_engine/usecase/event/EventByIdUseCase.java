package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;

public interface EventByIdUseCase {

    //get specific event by event ID
    Event getByEventId(Long eventId);
}
