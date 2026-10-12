package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.records.CreateEventCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.records.CreateEventResult;

public interface CreateEventUseCase {

    //create event
    CreateEventResult execute(CreateEventCommand createEventCommand);
}
