package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.controllers;

import jakarta.validation.Valid;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.events_records.*;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.EventByIdUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.GetAllEventsUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.DTOs.*;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.web_resolver.annotation.CurrentUserId;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.usecase.event.CreateEventUseCase;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.ticketing_engine.web.event.webMappers.EventWebMapper;
import lk.modular.monolithic.event.ticketing.fraud.control.restful.api.shared.error_handling.DTOs.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    //inject required dependencies
    private final CreateEventUseCase createEventUseCase;
    private final GetAllEventsUseCase getAllEventsUseCase;
    private final EventWebMapper eventWebMapper;
    private final EventByIdUseCase eventByIdUseCase;

    public EventController(
            CreateEventUseCase createEventUseCase,
            EventWebMapper eventWebMapper,
            GetAllEventsUseCase getAllEventsUseCase,
            EventByIdUseCase eventByIdUseCase
    ) {
        this.createEventUseCase = createEventUseCase;
        this.eventWebMapper = eventWebMapper;
        this.getAllEventsUseCase = getAllEventsUseCase;
        this.eventByIdUseCase = eventByIdUseCase;
    }

    //create event
    @PostMapping
    public ResponseEntity<ApiResponse<CreateEventResponseDTO>> createEvent(
            @Valid @RequestBody CreateEventRequestDTO createEventRequestDTO,
            @CurrentUserId Long organizerId
    ) {

        CreateEventCommand toCommand = eventWebMapper.toCreateEventCommand(organizerId, createEventRequestDTO);
        CreateEventResult toResult = createEventUseCase.execute(toCommand);
        CreateEventResponseDTO responseDTO = eventWebMapper.toCreateEventResponseDTO(toResult);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(responseDTO));
    }

    //get all events
    @GetMapping
    public ResponseEntity<ApiResponse<List<GetAllEventsResponseDTO>>> getAllEvents(){

        List<GetAllEventsResult> getAllEvents = getAllEventsUseCase.getAllEvents();
        List<GetAllEventsResponseDTO> responseDTOS = getAllEvents.stream()
                .map(eventWebMapper::toGetAllEventsResponse).toList();

        return ResponseEntity.ok(ApiResponse.success(responseDTOS));
    }

    //get event by id
    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse<GetEventByIdResponseDTO>> getEventById(
            @PathVariable("eventId") Long eventId
    ){
        GetEventByIdCommand toCommand = eventWebMapper.toGetEventByIdCommand(eventId);
        GetEventByIdResult toResult = eventByIdUseCase.getByEventId(toCommand);
        GetEventByIdResponseDTO responseDTO = eventWebMapper.getEventByIdResponse(toResult);

        return ResponseEntity.ok(ApiResponse.success(responseDTO));
    }

}
