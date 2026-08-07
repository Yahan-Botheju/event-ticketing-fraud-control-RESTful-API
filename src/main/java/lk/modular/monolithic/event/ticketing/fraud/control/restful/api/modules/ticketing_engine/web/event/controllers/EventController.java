package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.controllers;

import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.CreateEventUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.webMappers.EventWebMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    //inject required dependencies
    private final CreateEventUseCase createEventUseCase;
    private final EventWebMapper eventWebMapper;

    public EventController(
            CreateEventUseCase createEventUseCase,
            EventWebMapper eventWebMapper
    ) {
        this.createEventUseCase = createEventUseCase;
        this.eventWebMapper = eventWebMapper;
    }
}
