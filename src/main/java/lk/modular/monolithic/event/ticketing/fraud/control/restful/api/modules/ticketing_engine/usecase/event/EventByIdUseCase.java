package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.GetEventByIdCommand;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.GetEventByIdResult;

public interface EventByIdUseCase {

    //get specific event by event ID
    GetEventByIdResult getByEventId(GetEventByIdCommand getEventByIdCommand);
}
