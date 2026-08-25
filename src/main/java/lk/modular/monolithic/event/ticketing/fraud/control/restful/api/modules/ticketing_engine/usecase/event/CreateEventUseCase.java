package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.CreateEventRequestCommand;

public interface CreateEventUseCase {

    //create event
    Event execute(CreateEventRequestCommand requestCommand, Long organizerId);
}
