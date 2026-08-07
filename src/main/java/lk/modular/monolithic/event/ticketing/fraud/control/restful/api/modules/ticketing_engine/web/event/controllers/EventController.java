package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.controllers;

import jakarta.validation.Valid;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.web_resolver.annotation.CurrentUserId;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.domain.models.Event;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.CreateEventUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs.CreateEventRequestDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs.EventResponseDTO;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.webMappers.EventWebMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handler.DTOs.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    //create event
    @PostMapping
    public ResponseEntity<ApiResponse<EventResponseDTO>> createEvent(
            @Valid @RequestBody CreateEventRequestDTO createEventRequestDTO,
            @CurrentUserId Long organizerId
    ) {

        //create domain model
        Event toDomainModel = eventWebMapper.toDomainModel(createEventRequestDTO);
        //set organizer id to event
        toDomainModel.setOrganizerId(organizerId);
        //set to usecase for create event
        Event setToUseCase = createEventUseCase.execute(toDomainModel);
        //create response
        EventResponseDTO responseDTO = eventWebMapper.toResponseDTO(setToUseCase);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(responseDTO));
    }

}
